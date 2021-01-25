package test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import AuctionSystem.Administrator;
import AuctionSystem.Auction;
import AuctionSystem.AuctionList;
import AuctionSystem.CreditCard;
import AuctionSystem.User;
import AuctionSystem.UserList;


public class TestAdmin {
	@BeforeEach
	public void setUp() throws Exception {
		UserList.getInstance().clearList();
		AuctionList.getInstance().clearList();
		User.init();
		Auction.init();
	}
	
	@Test
	public void testBrowseUnauditedAuctions() throws Exception {
		setOutput();
		Administrator admin = Administrator.getInstance();
		admin.browseUnauditedAuctions();
		assertEquals("==========================\r\n"
				+ "List of Unaudited Auctions\r\n"
				+ "==========================\r\n"
				+ "==========================\r\n", getOutput());
	}
	
	@Test
	public void testBrowseActivatedAuctions() throws Exception {
		setOutput();
		Administrator admin = Administrator.getInstance();
		admin.browseActivatedAuctions();
		assertEquals("==========================\r\n"
				+ "List of activated Auctions\r\n"
				+ "==========================\r\n"
				+ "==========================\r\n", getOutput());
	}
	
	@Test
	public void testSearchAuction() {
		Administrator admin = Administrator.getInstance();
		int result = admin.searchAuction(1);
		assertEquals(-1, result);
	}
	
	@Test
	public void testAapproveAuction() {
		Administrator admin = Administrator.getInstance();
		boolean result = admin.approveAuction(1);
		assertEquals(false, result);
	}
	
	@Test
	public void testDeclineAuction() {
		Administrator admin = Administrator.getInstance();
		boolean result = admin.declineAuction(1);
		assertEquals(false, result);
	}
	
	
	
	@Test
	public void testSendMsgToUser1() {
		Administrator admin = Administrator.getInstance();
		boolean result = admin.sendMsgToUser(1, "Hello!");
		assertEquals(false, result);
	}
	
	@Test
	public void testSendMsgToUser2() {
		Administrator admin = Administrator.getInstance();
		User user;
		CreditCard card;
		card = new CreditCard(5566, 8000);
		user = new User("Altair", card);
		boolean result = admin.sendMsgToUser(1, "Hello!");
		assertEquals(true, result);
	}
	
	@Test
	public void testDisplayAllUsers() throws Exception {
		setOutput();
		Administrator admin = Administrator.getInstance();
		User user;
		CreditCard card;
		card = new CreditCard(5566, 8000);
		user = new User("Altair", card);
		admin.displayAllUsers();
		assertEquals("==========================\r\n"
				+ "List of all users created\r\n"
				+ "==========================\r\n"
				+ "1. Name: Altair    UserID: 1\r\n"
				+ "==========================\r\n", getOutput());
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
