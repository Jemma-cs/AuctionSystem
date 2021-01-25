package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import AuctionSystem.*;

public class testItem1 {
	//Test basic methods (AuctionState & Tag) and change state
	private CreditCard card;
	private User william;
	private Item item;

	
	@Before
	public void setUp() throws Exception { 
		UserList.getInstance().clearList();
		AuctionList.getInstance().clearList();
		User.init();
		Auction.init();
		card = new CreditCard(5566, 10000); 
		william = new User("William", card);
		item = new Item("apple", "food", 1, william);
	}
	 
	  
	@Test
	public void testID() {
		assertEquals(1, item.getID());
	}
	
	@Test
	public void testOwner() {
		assertEquals(william, item.getOwner());
	}
	
	@Test
	public void testName() {
		assertEquals("apple", item.getName());
	}
	
	@Test
	public void testState() {
		assertEquals("Unpublished", item.getState().toString());
	}
	
	@Test
	public void testToString() {
		assertEquals("ItemName: apple\nOwner:\nName: William    UserID: 1\nDescription: \nfood\nTags:\n", item.toString());
	}
	
	@Test
	public void testChangeState() {
		AuctionState aState = new OnSaleState();
		AuctionState newState = item.setState(aState) ;
		assertEquals(aState, newState);
	}
}
