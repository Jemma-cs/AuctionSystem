package AuctionSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class AuctionList {
	private static ArrayList<Auction> list;
	private static ArrayList<Auction> unauditedList;
	private static AuctionList instance=new AuctionList();
	
	private AuctionList() {
		list=new ArrayList<Auction>();
		unauditedList=new ArrayList<Auction>();
	}
	public static AuctionList getInstance() {
		return instance;
	}
	
	/// Browse auctions
	public void displayUnauditedList() {
		System.out.println("==========================");
		System.out.println("List of Unaudited Auctions");
		System.out.println("==========================");
		int i=1;
		for(Auction a:unauditedList) {
			System.out.println(i+". "+a);
			i++;
		}
		System.out.println("==========================");
	}
	public void displayActivatedList() {
		System.out.println("==========================");
		System.out.println("List of activated Auctions");
		System.out.println("==========================");
		int i=1;
		for(Auction a:list) {
			System.out.println(i+". "+a);
			i++;
		}
		System.out.println("==========================");
	}
	public int searchAuction(int auctID, boolean isAdmin) {
		Auction auct;
		if(isAdmin)
			auct=findAuct(auctID);
		else
			auct=findAuct1(auctID);
		if(auct!=null) {
			System.out.println("Requested auction found:");
			System.out.println(auct+"\n\n");
			return auct.getID();
		}
		else {
			System.out.println("Auction with ID: "+auctID+" not found.");
			return -1;
		}
	}
	public int searchAuctionByTag(String tagName) {//Search in activatedList by tagCode
		System.out.println("==========================");
		System.out.println("List of requested Auctions");
		System.out.println("==========================");
		int resultNum=0;
		int i=1;
		for(Auction a:list) {
			if(a.isHasTag(tagName)) {
				System.out.println(i+". "+a);
				i++;
				resultNum++;
			}
		}
		System.out.println("Number of results: "+resultNum);
		System.out.println("==========================");
		return resultNum;
	}
	
	///Add a new auction to the unaudited list.
	public Auction newAuction(Auction auct) {
		unauditedList.add(auct);
		return auct;
	}
	
	///Approve, decline, delete an auction by ID
	public boolean approveAuction(int auctID) {
		Auction auct=findAuct(auctID);
		if(!unauditedList.remove(auct)){
			System.out.println("Auction to be approved does not exist in the unaudited list.");
			return false;
		}
		auct.setState(new OnSaleState());
		auct.alertOwnApprove();
		list.add(auct);
		return true;//Target auction is removed successfully
	}
	public boolean declineAuction(int auctID) {
		Auction auct=findAuct(auctID);
		//If target auction does not exist in unAuditedList
		if(!unauditedList.remove(auct)) {
			System.out.println("Auction to be declined does not exist or has already been approved.");
			return false;
		}
		auct.setState(new UnpublishedState());
		auct.alertOwnDecline();
		return true;
	}
	public boolean deleteAuction(int auctID) {
		Auction auct=findAuct(auctID);
		if(list.remove(auct)) {
			auct.alertOwnDelete();
			auct.setState(new UnpublishedState());
			System.out.println("Auction deleted from the list.");
			return true;
		}
		else if(unauditedList.remove(auct)) {
			auct.alertOwnDelete();
			auct.setState(new UnpublishedState());
			System.out.println("Auction deleted from the unaudited list.");
			return true;
		}
		System.out.println("Auction not found in the record!");
		return false;//Indicates the auction to be deleted does not exists in the lists
	}
	
	///search for an auction by ID.
	public Auction findAuct(int auctID) {//Search in both lists, invoke two search methods
		Auction a;
		a=findAuct0(auctID);
		if(a!=null) {
			System.out.println("Unaudited auction found");
			return a; 
		}
		else {
			a=findAuct1(auctID);
			if(a!=null) {
				System.out.println("Auction found.");
				return a;
			}
		}
		return null;
	}
	public Auction findAuct0(int auctID) {//Search in unaudited list
		for(Auction a:unauditedList) {
			if(a.getID()==auctID)	
				return a;
		}
		return null;
	}
	public Auction findAuct1(int auctID) {//Search in activated list
		for(Auction a:list) {
			if(a.getID()==auctID)
				return a;
		}
		return null;
	}
	
	///For testing
	public void clearList() {
		list.clear();
		unauditedList.clear();
	}
}
