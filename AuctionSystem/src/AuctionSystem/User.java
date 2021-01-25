package AuctionSystem;

import java.util.ArrayList;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class User {
	private static int latestID=0;
	private String name;
	private int userID;
	private CreditCard myCard;
	private int latestItemID=0;
	private ArrayList<String> myMsg;
	final private int MSG_LIMIT=30;
	private ArrayList<Item> myItems;
	
	AuctionList al=AuctionList.getInstance();
	
	///1. Basic methods (Class CreditCard)
	public User(String aName, CreditCard aCard){
		name=aName;
		myCard=aCard;
		userID=++latestID;
		myItems=new ArrayList<Item>();
		myMsg=new ArrayList<String>();
		UserList ul=UserList.getInstance();
		ul.addUser(this);
	}
	public String getName() {
		return name;
	}
	public int getID() {
		return userID;
	}
	public String toString() {
		String temp="";
		// Content
		temp+="Name: "+this.name+"    UserID: "+this.userID;
		return temp;
	}
	public CreditCard changeCreditCard(int aCardNum, double aBalance){
		myCard = new CreditCard(aCardNum, aBalance);
		return myCard;
	}
	public void displayMyInfo() {//Can only be accessed by self and will display credit card info
		System.out.println(this);
		System.out.println("Number of created items: "+latestItemID);
		System.out.println(myCard);
	}
	///Manage messages
	public void receiveMsg(String msg) {//if size of myMsg exceeds the limit (MSG_LIMIT), clear arrayList before insertion
		if(this.isMsgNumReachLimit()) {
			myMsg.clear();
		}
		myMsg.add(msg);
	}
	public void displayOneMsg(int i){
		System.out.println(myMsg.get(i));
	}
	public void displayMsgs(int number) {
		for(int i=0;i<number;i++) {
			System.out.println(i+1+".\n"+myMsg.get(i));
		}
	}
	public void displayAllMsgs() {
		int i=0;
		for(String s:myMsg) {
			System.out.println(i+1+".\n"+s);
			i++;
		}
	}
	private boolean isMsgNumReachLimit() {
		if(myMsg.size()==MSG_LIMIT)
			return true;
		return false;
	}
	///Payment invoking creditcard
	public int transaction(double amount) {//amount can be positive or negative
		int result;
		if(amount>=0)
			result=this.myCard.increaseBal(amount);
		else
			result=this.myCard.decreaseBal(-1*amount);
		return result;//1 indicates success, 0 indicates failure
	}
		
	
	///2. Manage items (Class Item)
	private Item findItem(int aItemID) {
		for(Item i:myItems) {
			if(i.getID()==aItemID)
				return i;
		}
		return null;
	}
	public Item addItem(String aName, String aDesc) {
		int aID=++latestItemID;
		Item i = new Item(aName, aDesc, aID, this);
		myItems.add(i);
		System.out.println("Item "+aID+" "+aName+" is added successfully.");
		return i;
	}
	public Item deleteItem(int aItemID) {//Delete an item if it exists in myList and not yet be published
		Item temp=this.findItem(aItemID);
		if(temp!=null) {
			if(temp.getState().getClass().equals(UnpublishedState.class)){
				myItems.remove(temp);
				System.out.println("Item "+aItemID+" is deleted successfully.");
				return temp;
			}
			System.out.println("Deletion failed. Item "+aItemID+" has been published! You may access the auction instead.");
			return null;
		}
		System.out.println("Deletion failed. Item with ID "+aItemID+" is not found.");
		return null;
	}
	public void displayMyItems() {
		if(!myItems.isEmpty()) {
			System.out.println("===============================");
			System.out.println("List of "+this.name+"\'s items");
			System.out.println("===============================");
			int i=0;
			for(Item it:myItems){
				System.out.println(i+1+". ItemName: "+it.getName()+"    ItemID: "+it.getID());
				i++;
			}
			System.out.println("===============================");
		}
		else
			System.out.println("No item in list.");
	}
	///Manage Tags of item
	public void displayAllTags() {
		System.out.println("1. "+TagNew.getInstance().getName());
		System.out.println("2. "+TagVirtual.getInstance().getName());
	}
	
	public boolean addTagToItem(int aItemID, String tagName) {
		Item it=this.findItem(aItemID);
		if(it==null) {
			System.out.println("Failed. Item with ID "+aItemID+" is not found.");
			return false;
		}
		if(!it.getState().toString().equals("Unpublished")) {
			System.out.println("Failed. This item has already been published as an auction!");
			return false;
		}
		int result=it.addTag(tagName);
		if(result>0) {
			System.out.println("Succeeded. Tag "+tagName+" is added to item "+aItemID+" successfully.");
			return true;
		}
		else if(result==0) {
			System.out.println("Failed. Tag "+tagName+" already exists in item "+aItemID+".");
			return false;
		}
		else {
			System.out.println("Failed. Tag "+tagName+" does not exist.");
			return false;
		}
	}
	public boolean deleteTagFromItem(int aItemID, String tagName) {
		Item it=this.findItem(aItemID);
		if(it==null) {
			System.out.println("Failed. Item with ID "+aItemID+" is not found.");
			return false;
		}
		int result=it.deleteTag(tagName);
		if(result>0) {
			System.out.println("Succeeded. Tag "+tagName+" is deleted from item "+aItemID+" successfully.");
			return true;
		}
		else if(result==0) {
			System.out.println("Failed. Tag "+tagName+" is not included in item "+aItemID+".");
			return false;
		}
		else {
			System.out.println("Failed. Tag "+tagName+" does not exist.");
			return false;
		}
	}
	
	
	///3. Access & manage auctions (Class Auction & AuctionList)
	//As seller
	public int applyForAuction(int aItemID, double floorP, double noBargainP){//published auction added to unaudited list in AuctionList singleton class
		if(floorP<=0||noBargainP<=0||floorP>noBargainP) {//floorP & noBargainP must be larger than 0, but they can be equal (Is this OK?)
			System.out.println("Application denied. Input iformation is illegal.");
			return -1;
		}
		Item temp=this.findItem(aItemID);
		if(temp==null) {
			System.out.println("Application denied! Item not found in the list.");
			return -2;
		}
		if(temp.getState() instanceof UnpublishedState) {
			Auction newAuct=new Auction(temp, floorP, noBargainP);
			//al.newAuction(newAuct);
			System.out.println("New auction is created and pending for audition.");
			return 1;
		}
		else {
			System.out.println("Applcation denied. This item has already been published!");
			return 0;
		}
	}
	public int withdrawAuction(int auctID) {
		Auction auct=al.findAuct(auctID);
		if(auct==null) {
			System.out.println("Deletion failed! Auction with ID "+auctID+" is not found!");
			return -1;//indicate auction not found
		}
		else {
			if(auct.isOwner(this)) {
				al.deleteAuction(auctID);//System.out is done in deleteAuction()
				//System.out.println("Your auction (ID: "+auctID+") is deleted from the list.");
				return 1;//Deletion succeed
			}
			else {
				System.out.println("Deletion failed! Auction (ID: "+auctID+") is owned by another user.");
				return 0;//indicates auction owned by another user
			}
		}
	}
	public int confirmTransaction(int auctID) {//?Do we need a method to reopen it?
		int result;
		Auction auct=al.findAuct(auctID);
		if(auct==null) {
			System.out.println("Confirmation failed! Auction with ID "+auctID+" is not found!");
			return -1;//indicate auction not found
		}
		else {
			if(auct.isOwner(this)) {
				result=auct.confirmTransaction();//System.out is done in finishPayment()
				return result;//1: success. 0: can't be sold.
			}
			else {
				System.out.println("Confirmation failed! Auction (ID: "+auctID+") is owned by another user.");
				return -2;//indicates auction owned by another user
			}
		}
	}
	//As buyer
	public void browseActivatedAuctions() {//Normal user can only access the list of audited auctions
		al.displayActivatedList();
	}
	public int bidForAuction(int auctID, double bidPrice) {
		int result;
		Auction auct=al.findAuct1(auctID);
		if(auct==null) {
			result=-3;//auctID not found in the activatedList
			System.out.println("Bidding failed. This auctID does not exist or is unaudited.");
			return result;
		}
		else if(auct.isOwner(this)) {
			result=-2;//This auction is owned by yourself!
			System.out.println("Bidding failed. This auction is owned by yourself!");
			return result;
		}
		else {
			result=auct.bid(bidPrice, this);//Result from Auctoin.bid(double, User)
			return result;//1: Bidding succeeds. -1: Auction unavailable. 0: Bidding invalid
		}
	}
	public int searchAuction(int auctID) {
		return al.searchAuction(auctID, false);//return auctID if found, otherwise -1
	}
	public int searchAuctionByTag(String tagName) {
		return al.searchAuctionByTag(tagName);//0: no auction found. else return number of results
	}
	public int finishPayment(int auctID) {
		int result;
		Auction auct=al.findAuct(auctID);
		if(auct==null) {
			System.out.println("Confirmation failed! Auction with ID "+auctID+" is not found!");
			return -1;//indicate auction not found
		}
		else {
			if(!auct.isOwner(this)) {
				result=auct.finishPayment();//System.out is done in finishPayment()
				return result;//1: success. 0: can't be sold. -2: buyer failed. -3: seller failed.
			}
			else {
				System.out.println("Confirmation failed! Auction (ID: "+auctID+") is owned by yourself.");
				return -4;//indicates auction owned by yourself
			}
		}
	}
	
	

	///4. Request for all user information (Class UserList)
	public void displayAllUsers() {
		UserList ul=UserList.getInstance();
		ul.displayAllUsers();
	}
	
	
	///for testing
	public static void init() {
		latestID=0;
	}
}
