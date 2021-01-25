package test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;

import AuctionSystem.Auction;
import AuctionSystem.AuctionList;
import AuctionSystem.CreditCard;
import AuctionSystem.Item;
import AuctionSystem.User;
import AuctionSystem.UserList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUser3 {
	@BeforeEach
	public void setUp() throws Exception {
		UserList.getInstance().clearList();
		AuctionList.getInstance().clearList();
		User.init();
		Auction.init();
	}
	
	//seller
	
	//applyForAuction
	@Test
	public void testApplyForAuction1() {
		User seller;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		seller = new User("Altair Yin", card);
		
		seller.addItem("Apple", "From Japan");
		int result = seller.applyForAuction(1, -10, 10);
		assertEquals(-1, result);
	}
	
	@Test
	public void testApplyForAuction2() {
		User seller;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		seller = new User("Altair Yin", card);
		
		seller.addItem("Apple", "From Japan");
		int result = seller.applyForAuction(1, 10, -10);
		assertEquals(-1, result);
	}
	
	@Test
	public void testApplyForAuction3() {
		User seller;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		seller = new User("Altair Yin", card);
		
		seller.addItem("Apple", "From Japan");
		int result = seller.applyForAuction(1, 10, 5);
		assertEquals(-1, result);
	}
	
	@Test
	public void testApplyForAuction4() {
		User seller;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		seller = new User("Altair Yin", card);
		
		seller.addItem("Apple", "From Japan");
		int result = seller.applyForAuction(1, 5, 10);
		assertEquals(1, result);
	}
	
	@Test
	public void testApplyForAuction5() {
		User seller;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		seller = new User("Altair Yin", card);
		
		seller.addItem("Apple", "From Japan");
		seller.applyForAuction(1, 5, 10);
		int result = seller.applyForAuction(1, 5, 10);
		assertEquals(0, result);
	}
	
	@Test
	public void testApplyForAuction6() {
		User seller;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		seller = new User("Altair Yin", card);
		
		int result = seller.applyForAuction(0, 5, 10);
		assertEquals(-2, result);
	}
	
	//withdrawAuction
	@Test
	public void testWithdraw1() {
		User seller;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		seller = new User("Altair Yin", card);
		
		int result = seller.withdrawAuction(10);
		assertEquals(-1, result);
	}
	
	@Test
	public void testWithdraw2() {
		User seller;
		CreditCard card;
		card = new CreditCard(55662, 12000);
		seller = new User("Oracle Wu", card);
		AuctionList al=AuctionList.getInstance();
		
		User buyer2;
		CreditCard card2;
		card2 = new CreditCard(55663, 8000);
		buyer2 = new User("William Wu", card2);
		
		Item nswitch = buyer2.addItem("NS", "From Japan");
		new Auction(nswitch, 2000, 2200);
		al.approveAuction(1);
		
		int result = seller.withdrawAuction(1);
		assertEquals(0, result);
	}
	
	@Test
	public void testWithdraw3() {
		User seller;
		CreditCard card;
		card = new CreditCard(55663, 8000);
		seller = new User("William Wu", card);
		AuctionList al=AuctionList.getInstance();

		seller.addItem("NS", "From Japan");
		seller.applyForAuction(1, 2000, 2200);
		al.approveAuction(1);

		
		int result = seller.withdrawAuction(1);
		assertEquals(1, result);
	}
	
	//confirmTransaction
	@Test
	public void testConfirmTransaction1() {
		User seller;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		seller = new User("Altair Yin", card);
		
		int result = seller.confirmTransaction(10);
		assertEquals(-1, result);
	}
	
	@Test
	public void testConfirmTransaction2() {
		User seller;
		CreditCard card;
		card = new CreditCard(55662, 12000);
		seller = new User("Oracle Wu", card);
		AuctionList al=AuctionList.getInstance();
		
		User buyer2;
		CreditCard card2;
		card2 = new CreditCard(55663, 8000);
		buyer2 = new User("William Wu", card2);
		
		Item nswitch = buyer2.addItem("NS", "From Japan");
		new Auction(nswitch, 2000, 2200);
		al.approveAuction(1);
		
		int result = seller.confirmTransaction(1);
		assertEquals(-2, result);
	}
	
	@Test
	public void testConfirmTransaction3() {
		User seller;
		CreditCard card;
		card = new CreditCard(55663, 8000);
		seller = new User("William Wu", card);
		
		seller.addItem("NS", "From Japan");
		seller.applyForAuction(1, 2000, 2200);
		AuctionList al=AuctionList.getInstance();
		al.approveAuction(1);
		int result = seller.confirmTransaction(1);
		assertEquals(0, result);
	}
	
	
	//buyer
	//browseActivatedAuctions
	@Test
	public void test() throws Exception {
		setOutput();
		User buyer;
		CreditCard card;
		card = new CreditCard(55663, 8000);
		buyer = new User("William Wu", card);
		
		buyer.browseActivatedAuctions();
		assertEquals("==========================\r\n"
				+ "List of activated Auctions\r\n"
				+ "==========================\r\n"
				+ "==========================\r\n", getOutput());
	}
	//bidForAuction(int auctID, double bidPrice)
	@Test
	public void testBidForAuction1() {
		User buyer;
		CreditCard card;
		card = new CreditCard(55663, 8000);
		buyer = new User("William Wu", card);
		
		int result = buyer.bidForAuction(10, 1000);
		assertEquals(-3, result);
	}
	
	@Test
	public void testBidForAuction2() {
		User buyer;
		CreditCard card;
		card = new CreditCard(55663, 8000);
		buyer = new User("William Wu", card);
		AuctionList al=AuctionList.getInstance();
		
		Item nswitch = buyer.addItem("NS", "From Japan");
		new Auction(nswitch, 2000, 2200);
		al.approveAuction(1);
		
		
		int result = buyer.bidForAuction(1, 1000);
		assertEquals(-2, result);
	}
	
	@Test
	public void testBidForAuction3() {
		User buyer;
		CreditCard card;
		card = new CreditCard(55663, 8000);
		buyer = new User("Alice", card);
		AuctionList al=AuctionList.getInstance();
		
		User buyer2;
		CreditCard card2;
		card2 = new CreditCard(55663, 8000);
		buyer2 = new User("William Wu", card2);
		
		Item nswitch = buyer2.addItem("NS", "From Japan");
		new Auction(nswitch, 2000, 2200);
		al.approveAuction(1);
		
		int result = buyer.bidForAuction(1, 1);
		assertEquals(0, result);
	}
	
	@Test
	public void testSearchAuction() {
		User buyer;
		CreditCard card;
		card = new CreditCard(55663, 8000);
		buyer = new User("Alice", card);
		AuctionList al=AuctionList.getInstance();

		User buyer2;
		CreditCard card2;
		card2 = new CreditCard(55663, 8000);
		buyer2 = new User("William Wu", card2);
		
		Item nswitch = buyer2.addItem("NS", "From Japan");
		new Auction(nswitch, 2000, 2200);
		al.approveAuction(1);
		
		int result = buyer.searchAuction(1);
		assertEquals(1, result);
	}
	
	@Test
	public void testSearchAuctionByTag() {
		User buyer;
		CreditCard card;
		card = new CreditCard(55663, 8000);
		buyer = new User("Alice", card);
		
		int result = buyer.searchAuctionByTag("New");
		assertEquals(0, result);
	}
	//finishPayment
	@Test
	public void testFinishPayment1() {
		User buyer;
		CreditCard card;
		card = new CreditCard(55663, 8000);
		buyer = new User("Alice", card);
		
		int result = buyer.finishPayment(10);
		assertEquals(-1, result);
	}
	
	@Test
	public void testFinishPayment2() {
		User buyer;
		CreditCard card;
		card = new CreditCard(55663, 8000);
		buyer = new User("Alice", card);
		AuctionList al=AuctionList.getInstance();

		User buyer2;
		CreditCard card2;
		card2 = new CreditCard(55663, 8000);
		buyer2 = new User("William Wu", card2);
		
		Item nswitch = buyer2.addItem("NS", "From Japan");
		new Auction(nswitch, 2000, 2200);
		al.approveAuction(1);
		
		int result = buyer.finishPayment(1);
		assertEquals(0, result);
	}
	
	@Test
	public void testFinishPayment3() {
		User buyer;
		CreditCard card;
		card = new CreditCard(55663, 8000);
		buyer = new User("Alice", card);
		Item nswitch = buyer.addItem("NS", "From Japan");
		new Auction(nswitch, 2000, 2200);
		AuctionList al=AuctionList.getInstance();
		al.approveAuction(1);
		
		int result = buyer.finishPayment(1);
		assertEquals(-4, result);
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
