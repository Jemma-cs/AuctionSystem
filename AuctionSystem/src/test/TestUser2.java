package test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import AuctionSystem.Auction;
import AuctionSystem.AuctionList;
import AuctionSystem.AuctionState;
import AuctionSystem.CreditCard;
import AuctionSystem.Item;
import AuctionSystem.OnSaleState;
import AuctionSystem.User;
import AuctionSystem.UserList;

public class TestUser2 {
	@BeforeEach
	public void setUp() throws Exception {
		UserList.getInstance().clearList();
		AuctionList.getInstance().clearList();
		User.init();
		Auction.init();
	}
	
	
	//addItem(String aName, String aDesc)
	@Test
	public void testAddItem() {
		User user;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		user = new User("Altair Yin", card);
		
		Item result = user.addItem("Apple", "From Japan");
		Item apple = new Item("Apple", "From Japan", 1, user);
		String resultInfo = result.toString();
		String appleInfo = apple.toString();
		assertEquals(appleInfo, resultInfo);
	}
	
	//deleteItem(int aItemID) & findItem(int aItemID)
	
	@Test
	public void testDeleteItem1() {
		User user;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		user = new User("Altair", card);
		
		Item apple = user.addItem("Apple", "From Japan");
		Item result = user.deleteItem(2);
		
		assertEquals(null, result);
	}
	
	@Test
	public void testDeleteItem2() {
		User user;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		user = new User("Altair", card);
		
		Item apple = user.addItem("Apple", "From Japan");
		Item result = user.deleteItem(1);
		
		assertEquals(apple, result);
	}
	
	@Test
	public void testDeleteItem3() {
		User user;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		user = new User("Altair", card);
		
		Item apple = user.addItem("Apple", "From Japan");
		AuctionState appleState = apple.setState(new OnSaleState());
		Item result = user.deleteItem(1);
		
		assertEquals(null, result);
	}
	
	//displayMyItems()
	
	@Test
	public void testDisplayMyItems1() throws Exception {
		setOutput();
		User user;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		user = new User("Altair", card);
		
		Item apple = user.addItem("Apple", "From Japan");
		Item laptop = user.addItem("Laptop", "lenovo");
		Item account = user.addItem("Steam account", "With many games.");
		
		user.displayMyItems();
		
		assertEquals("Item 1 Apple is added successfully.\r\n"
				+ "Item 2 Laptop is added successfully.\r\n"
				+ "Item 3 Steam account is added successfully.\r\n"
				+ "===============================\r\n"
				+ "List of Altair's items\r\n"
				+ "===============================\r\n"
				+ "1. ItemName: Apple    ItemID: 1\r\n"
				+ "2. ItemName: Laptop    ItemID: 2\r\n"
				+ "3. ItemName: Steam account    ItemID: 3\r\n"
				+ "===============================\r\n", getOutput());
	}
	
	@Test
	public void testDisplayMyItems2() throws Exception {
		setOutput();
		User user;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		user = new User("Altair", card);
		
		user.displayMyItems();
		
		assertEquals("No item in list.\r\n", getOutput());
	}
	
	//Tag management
	//displayAllTags()
	@Test
	public void testDisplayAllTags() throws Exception {
		setOutput();
		User user;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		user = new User("Altair", card);
		
		user.displayAllTags();
		
		assertEquals("1. New\r\n"
				+ "2. Virtual\r\n", getOutput());
	}
	
	//addTagToItem(int aItemID, String tagName)
	@Test
	public void testAddTagToItem1() {
		User user;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		user = new User("Altair", card);
		
		Item apple = user.addItem("Apple", "From Japan");
		boolean result = user.addTagToItem(2, "New");
		
		assertEquals(false, result);
	}
	
	@Test
	public void testAddTagToItem2() {
		User user;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		user = new User("Altair", card);
		
		Item apple = user.addItem("Apple", "From Japan");
		boolean result = user.addTagToItem(1, "New");
		
		assertEquals(true, result);
	}
	
	@Test
	public void testAddTagToItem3() {
		User user;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		user = new User("Altair", card);
		
		Item apple = user.addItem("Apple", "From Japan");
		apple.addTag("New");
		boolean result = user.addTagToItem(1, "New");
		
		assertEquals(false, result);
	}
	
	@Test
	public void testAddTagToItem4() {
		User user;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		user = new User("Altair", card);
		
		Item apple = user.addItem("Apple", "From Japan");
		boolean result = user.addTagToItem(1, "Fruit");
		
		assertEquals(false, result);
	}
	
	//deleteTagFromItem(int aItemID, String tagName)
	
	@Test
	public void testDeleteTagFromItem1() {
		User user;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		user = new User("Altair", card);
		
		Item apple = user.addItem("Apple", "From Japan");
		apple.addTag("New");
		boolean result = user.deleteTagFromItem(2, "New");
		
		assertEquals(false, result);
	}
	
	@Test
	public void testDeleteTagFromItem2() {
		User user;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		user = new User("Altair", card);
		
		Item apple = user.addItem("Apple", "From Japan");
		apple.addTag("New");
		boolean result = user.deleteTagFromItem(1, "New");
		
		assertEquals(true, result);
	}
	
	@Test
	public void testDeleteTagFromItem3() {
		User user;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		user = new User("Altair", card);
		
		Item apple = user.addItem("Apple", "From Japan");
		apple.addTag("New");
		boolean result = user.deleteTagFromItem(1, "Virtual");
		
		assertEquals(false, result);
	}
	
	@Test
	public void testDeleteTagFromItem4() {
		User user;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		user = new User("Altair", card);
		
		Item apple = user.addItem("Apple", "From Japan");
		apple.addTag("New");
		boolean result = user.deleteTagFromItem(1, "Fruit");
		
		assertEquals(false, result);
	}
	
	

	PrintStream oldPrintStream;
	ByteArrayOutputStream bos;

	private void setOutput() throws Exception {
		oldPrintStream = System.out;
		bos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bos));
	}

	private String getOutput() { // throws Exception
		System.setOut(oldPrintStream);
		return bos.toString();
	}
}
