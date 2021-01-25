package test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import AuctionSystem.*;

public class testItem2 {
	//Test manage tags
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
	public void testAddTag1() {
		int result = item.addTag("New");
		assertEquals(1, result);
	}
	
	@Test
	public void testAddTag2() {	  
		item.addTag("New");
		int result = item.addTag("New");
		assertEquals(0, result);
	}
	
	@Test
	public void testAddTag3() {	  
		int result = item.addTag("Virtual");
		assertEquals(1, result);
	}
	
	@Test
	public void testAddTag4() {	  
		item.addTag("Virtual");
		int result = item.addTag("Virtual");
		assertEquals(0, result);
	}
	
	@Test
	public void testAddTag5() {	  
		int result = item.addTag("others");
		assertEquals(-1, result);
	}
	
	@Test
	public void testSearchNew() {	  
		item.addTag("New");
		boolean result = item.searchTagNew();
		assertEquals(true, result);
	}
	
	@Test
	public void testSearchVirtual() {	  
		assertEquals(false, item.searchTagVirtual());
	}
	
	@Test
	public void testdeleteTag1() {	  
		item.addTag("New");
		int result = item.deleteTag("New");
		assertEquals(1, result);
	}
	
	@Test
	public void testdeleteTag2() {	  
		item.addTag("Virtual");
		int result = item.deleteTag("Virtual");
		assertEquals(1, result);
	}
	
	@Test
	public void testdeleteTag3() {	  
		int result = item.deleteTag("New");
		assertEquals(0, result);
	}
	
	@Test
	public void testdeleteTag4() {
		int result = item.deleteTag("Virtual");
		assertEquals(0, result);
	}
	
	@Test
	public void testdeleteTag5() {	  
		int result = item.deleteTag("others");
		assertEquals(-1, result);
	}
	
	@Test
	public void testfind1() {	  
		boolean result = item.findTagByName("New");
		assertEquals(false, result);
	}
	
	@Test
	public void testfind2() {	 
		item.addTag("Virtual");
		boolean result = item.findTagByName("Virtual");
		assertEquals(true, result);
	}
	
	@Test
	public void testfind3() {	  
		boolean result = item.findTagByName("others");
		assertEquals(false, result);
	}
	
	@Test
	public void testGetTags() {	  
		item.addTag("New");
		item.addTag("Virtual");
		assertEquals("Tags: New Virtual", item.getStrTags());
	}
}
