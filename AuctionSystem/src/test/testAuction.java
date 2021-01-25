package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import AuctionSystem.*;

public class testAuction {
	private CreditCard card;
	private User william;
	private Item item;
	private Auction auction;

	@Before
	public void setUp() throws Exception {
		UserList.getInstance().clearList();
		AuctionList.getInstance().clearList();
		User.init();
		Auction.init();
		
		card = new CreditCard(5566, 10000);
		william = new User("William", card);
		item = new Item("apple", "food", 1, william);
		auction = new Auction(item, 10000, 20000);
	}

	// test basic methods
	@Test
	public void test2ID() {
		assertEquals(1, auction.getID());
	}

	@Test
	public void testName() {
		assertEquals("apple", auction.getName());
	}

	@Test
	public void testState() {
		assertEquals("Unaudited", auction.getState().toString());
	}

	@Test
	public void test1ToString() {
		item.setState(new OnSaleState());
		auction.bid(15000, william);
		assertEquals("Auction ID: 1    State: On Sale\n"
				+ "Item information:\nItemName: apple\nOwner:\nName: William    UserID: 1\nDescription: \nfood\nTags:\n\n"
				+ "Floor price: 10000.0    No-bargain price: 20000.0\n"
				+ "Current Price: 15000.0    Hold User: Name: William    UserID: 1\n", auction.toString());
	}

	@Test
	public void testIsOwner1() {
		assertEquals(true, auction.isOwner(william));
	}

	@Test
	public void testIsOwner2() {
		User Amy = new User("Amy", card);
		assertEquals(false, auction.isOwner(Amy));
	}

	@Test
	public void testIsHasTag() {
		assertEquals(false, auction.isHasTag("New"));
	}

	// test bidding
	@Test
	public void testbid1() {
		assertEquals(-1, auction.bid(15000, william));
	}

	@Test
	public void testbid2() {
		item.setState(new OnSaleState());
		assertEquals(0, auction.bid(5000, william));
	}

	@Test
	public void testbid3() {
		item.setState(new OnSaleState());
		auction.bid(15000, william);
		assertEquals(0, auction.bid(12000, william));
	}

	@Test
	public void testbid4() {
		item.setState(new OnSaleState());
		auction.bid(15000, william);
		assertEquals(1, auction.bid(21000, william));
	}

}
