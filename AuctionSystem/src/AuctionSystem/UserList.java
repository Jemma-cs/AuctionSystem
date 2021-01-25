package AuctionSystem;

import java.util.ArrayList;

public class UserList {
	private ArrayList<User> list;
	private static UserList instance=new UserList();
	private UserList() {
		list=new ArrayList<User>();
	}
	public static UserList getInstance() {
		return instance;
	}
	
	public void displayAllUsers() {
		System.out.println("==========================");
		System.out.println("List of all users created");
		System.out.println("==========================");
		int i=0;
		for(User u:list) {
			System.out.println(i+1+". "+u);
			i++;
		}
		System.out.println("==========================");
	}
	public User findUser(int loginID) {
		if(!list.isEmpty()) {
			for(User u:list) {
				if(u.getID()==loginID)
					return u;
			}
		}
		
		return null;
	}
	public User addUser(User u) {
		list.add(u);
		return u;
	}
	
	///For testing
	public void clearList() {
		list.clear();
	}
}
