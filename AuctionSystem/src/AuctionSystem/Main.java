package AuctionSystem;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("Auction System");
		Scanner scannerObj = new Scanner(System.in);
		User u=null;
		while (scannerObj.hasNext()) {//!!We assume that format of input command lines are correct!!
			String cmdLine=scannerObj.nextLine();
			if (cmdLine.equals("")) continue; 
			System.out.println("> "+cmdLine);
			String[] cmdParts = cmdLine.split("\\|");
			boolean isValidCommand = false;
			if(cmdParts[0].equals("admin")) {//administrator operations
				Administrator adm=Administrator.getInstance();
				isValidCommand=adminOperates(cmdParts, adm);
				}
			else if(cmdParts[0].equals("create account")) {//Create new account and login
				String userName=cmdParts[1];
				int cardNum=Integer.parseInt(cmdParts[2]);
				double cardBal=Double.parseDouble(cmdParts[3]);
				User newU=new User(userName, new CreditCard(cardNum,cardBal));
				System.out.println("Hello "+newU.getName()+"! Your account is created. UserID: "+newU.getID());
				isValidCommand=true;
			}
			else if(cmdParts[0].equals("login")) {//Login as || Switch to an existing account
				int loginId=Integer.parseInt(cmdParts[1]);
				UserList ul=UserList.getInstance();
				u=ul.findUser(loginId);
				if(u!=null) {
					System.out.println("Welcome, "+u.getName()+"!");
					isValidCommand=true;
				}
			}
			else if(cmdParts[0].equals("logout")) {
				if(u!=null) {
					System.out.println("User "+u.getName()+" log out successfully.");
					u=null;
					isValidCommand=true;
				}
			}
			else if (u != null) {//ordinary users operations
				if(cmdParts.length==1) {
					isValidCommand=userOperates1(cmdParts, u);
					}
				else if(cmdParts.length==2) {
					isValidCommand=userOperates2(cmdParts, u);
				}
				else if(cmdParts.length==3) {
					isValidCommand=userOperates3(cmdParts, u);
				}
				else if(cmdParts.length==4) {
					isValidCommand=userOperates4(cmdParts, u);
				}
			 }
			
			
			if (isValidCommand==false){//report Error information
			//else {
				reportError(cmdParts, u);
			}
		}
		
		scannerObj.close();
	}
	public static boolean adminOperates(String[] cmdParts, Administrator adm) {
		if(cmdParts.length==2) {
			if(cmdParts[1].equals("browse activated auctions")) {
				adm.browseActivatedAuctions();
				return true;
			}
			else if(cmdParts[1].equals("browse unaudited auctions")) {
				adm.browseUnauditedAuctions();
				return true;
			}
			else if(cmdParts[1].equals("display all users")) {
				adm.displayAllUsers();
				return true;
			}
		}
		else if(cmdParts.length==3) {
			int auctID=Integer.parseInt(cmdParts[2]);
			if(cmdParts[1].equals("approve auction")) {
				adm.approveAuction(auctID);
				return true;
			}
			else if(cmdParts[1].equals("decline auction")) {
				adm.declineAuction(auctID);
				return true;
			}
			else if(cmdParts[1].equals("search auction")) {
				adm.searchAuction(auctID);
				return true;
			}
		}
		else if(cmdParts.length==4) {
			if(cmdParts[1].equals("send msg to user")) {
				int userID=Integer.parseInt(cmdParts[2]);
				String msg=cmdParts[3];
				adm.sendMsgToUser(userID, msg);
				return true;
			}
		}	
		return false;
	}
	public static boolean userOperates1(String[] cmdParts, User u) {
			if(cmdParts[0].equals("display my info")) {
				u.displayMyInfo();
				return true;
			}
			else if(cmdParts[0].equals("display my items")) {
				u.displayMyItems();
				return true;
			}
			else if(cmdParts[0].equals("display all tags")) {
				u.displayAllTags();
				return true;
			}
			else if(cmdParts[0].equals("display my msgs")) {
				u.displayAllMsgs();
				return true;
			}
			else if(cmdParts[0].equals("browse activated auctions")) {
				u.browseActivatedAuctions();
				return true;
			}
			else if(cmdParts[0].equals("display all users")) {
				u.displayAllUsers();
				return true;
			}
		
		return false;
	}
	public static boolean userOperates2(String[] cmdParts, User u) {
		
			if(cmdParts[0].equals("delete item")) {
				int itemNum=Integer.parseInt(cmdParts[1]);
				u.deleteItem(itemNum);
				return true;
			}
			else if(cmdParts[0].equals("withdraw auction")) {
				int auctID=Integer.parseInt(cmdParts[1]);
				u.withdrawAuction(auctID);
				return true;
			}
			else if(cmdParts[0].equals("confirm transaction")) {
				int auctID=Integer.parseInt(cmdParts[1]);
				u.confirmTransaction(auctID);
				return true;
			}
			else if(cmdParts[0].equals("search auction by id")) {
				int auctID=Integer.parseInt(cmdParts[1]);
				u.searchAuction(auctID);
				return true;
			}
			else if(cmdParts[0].equals("search auction by tag")) {
				String tagName=cmdParts[1];
				u.searchAuctionByTag(tagName);
				return true;
			}
			else if(cmdParts[0].equals("finish payment")) {
				int auctID=Integer.parseInt(cmdParts[1]);
				u.finishPayment(auctID);
				return true;
			}
		return false;
		}
	public static boolean userOperates3(String[] cmdParts, User u) {
		
			if(cmdParts[0].equals("change credit card")) {
				int cardNum=Integer.parseInt(cmdParts[1]);
				double bal=Double.parseDouble(cmdParts[2]);
				u.changeCreditCard(cardNum, bal);
				return true;
			}
			else if(cmdParts[0].equals("add item")) {
				String name=cmdParts[1];
				String description=cmdParts[2];
				u.addItem(name, description);
				return true;
			}
			else if(cmdParts[0].equals("add tag to item")) {
				int itemId=Integer.parseInt(cmdParts[1]);
				String tag=cmdParts[2];
				u.addTagToItem(itemId,tag);
				return true;
			}
			else if(cmdParts[0].equals("delete tag from item")) {
				int itemId=Integer.parseInt(cmdParts[1]);
				String tag=cmdParts[2];
				u.deleteTagFromItem(itemId,tag);
				return true;
			}
			else if(cmdParts[0].equals("bid for auction")) {
				int auctID=Integer.parseInt(cmdParts[1]);
				double bidPrice=Double.parseDouble(cmdParts[2]);
				u.bidForAuction(auctID, bidPrice);
				return true;
			}
			return false;
		}
	public static boolean userOperates4(String[] cmdParts, User u) {
		if(cmdParts[0].equals("apply for auction")) {
			int itemId=Integer.parseInt(cmdParts[1]);
			double floorPrice=Double.parseDouble(cmdParts[2]);
			double noBargainPrice=Double.parseDouble(cmdParts[3]);
			u.applyForAuction(itemId, floorPrice, noBargainPrice);
			return true;
		}
		return false;
	}
	
	public static void reportError(String[] cmdParts, User u) {
		if(u==null) {
			if (cmdParts[0].equals("login")){
				System.out.println("User does not exist. Please create an account.");}
			else if (cmdParts[0].equals("logout")){
				System.out.println("No user is signed in now.");}
			else {
				System.out.println("Invalid command! Please retry.");
				System.out.println("(You may need to login as a user.)");}
		}
		else System.out.println("Invalid command! Please retry.");
	}
}