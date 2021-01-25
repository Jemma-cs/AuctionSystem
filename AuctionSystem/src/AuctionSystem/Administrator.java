package AuctionSystem;

public class Administrator {
	private static Administrator instance=new Administrator();
	AuctionList al;
	UserList ul;

	private Administrator() {
		al=AuctionList.getInstance();
		ul=UserList.getInstance();
	}
	public static Administrator getInstance() {
		return instance;
	}
	
	///Manage auctions
	public void browseUnauditedAuctions() {
		al.displayUnauditedList();
	}
	public void browseActivatedAuctions() {
		al.displayActivatedList();
	}
	public int searchAuction(int auctID) {
		return al.searchAuction(auctID, true);//return auctID if found, otherwise -1
	}
	public boolean approveAuction(int auctID) {
		return al.approveAuction(auctID);
	}
	public boolean declineAuction(int auctID) {
		return al.declineAuction(auctID);
	}
	
	///Manege user
	public void displayAllUsers() {
		ul.displayAllUsers();
	}
	public boolean sendMsgToUser(int userID, String msg) {
		User u=ul.findUser(userID);
		if(u!=null) {
			u.receiveMsg(msg);
			System.out.println("Message sent to user (ID: "+u.getID()+") successfully.");
			return true;
		}
		else
			return false;
	}
}