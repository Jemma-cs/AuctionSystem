package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.FixMethodOrder;
import AuctionSystem.User;
import AuctionSystem.UserList;
import AuctionSystem.Auction;
import AuctionSystem.AuctionList;
import AuctionSystem.CreditCard;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestUser1 {
	@BeforeEach
	public void setUp() throws Exception {
		UserList.getInstance().clearList();
		AuctionList.getInstance().clearList();
		User.init();
		Auction.init();
	}
	
	//test basic
	@Test
	public void testBasic1() { //getName() 
		User user;
		CreditCard card;
		card = new CreditCard(55661, 8000);
		user = new User("Altair Yin", card);
		String result = user.getName();
		assertEquals("Altair Yin", result);
	}
	
	@Test
	public void testBasic2() {//getID()
		User user;
		CreditCard card;
		card = new CreditCard(55662, 9000);
		user = new User("William Wu", card);
		int result = user.getID();
		assertEquals(1, result);
	}
	
	
	@Test
	public void testBasic3() {  //changeCreditCard(int aCardNum, double aBalance)
		User user;
		CreditCard card;
		CreditCard newCard;
		newCard = new CreditCard(55664, 20000.00);
		String newCardInfo = newCard.toString();
		card = new CreditCard(55663, 12000);
		user = new User("Oracle Wu", card);
		CreditCard result = user.changeCreditCard(55664, 20000.00);
		String resultInfo = result.toString();
		assertEquals(newCardInfo, resultInfo);
	} 

	@Test
	public void testBasic4() { // toString()
		User user;
		CreditCard card;
		card = new CreditCard(55662, 10000);
		user = new User("Jiangyue Che", card);
		String result = user.toString();
		assertEquals("Name: Jiangyue Che    UserID: 1", result);
	}
	
	@Test
	public void testBasic5() throws Exception {  //displayMyInfo() 
		setOutput();
		User user;
		CreditCard card;
		card = new CreditCard(55662, 10000);
		user = new User("Jade Zhang", card);
		user.displayMyInfo();
		assertEquals("Name: Jade Zhang    UserID: 1\r\nNumber of created items: 0\r\nCard number: 55662    Balance: 10000.00\r\n",  getOutput());
	}
	
	//test message management
	@Test
	public void testMsg1() throws Exception {
		setOutput();
		User user;
		CreditCard card;
		card = new CreditCard(55661, 9000);
		user = new User("Altair Yin", card);
		for(int i=0;i<31;i++) {
			user.receiveMsg("Hello");
		}
		user.displayAllMsgs();
		assertEquals("1.\nHello\r\n", getOutput());
	}
	
	@Test
	public void testMsg2() throws Exception {
		setOutput();
		User user;
		CreditCard card;
		card = new CreditCard(55661, 9000);
		user = new User("Altair Yin", card);
		user.receiveMsg("Hello");
		user.receiveMsg("How are you?");
		user.displayOneMsg(1);
		assertEquals("How are you?\r\n", getOutput());
	}
	
	@Test
	public void testMsg3() throws Exception {
		setOutput();
		User user;
		CreditCard card;
		card = new CreditCard(55661, 9000);
		user = new User("Altair Yin", card);
		user.receiveMsg("Hello");
		user.receiveMsg("How are you?");
		user.displayMsgs(2);
		assertEquals("1.\nHello\r\n2.\nHow are you?\r\n", getOutput());
	}
	
	//test payment invoking creditcard ( transaction(double amount) )
	@Test
	public void testPayment1() {  
		User user;
		CreditCard card;
		card = new CreditCard(55662, 9000);
		user = new User("Jemma", card);
		int result = user.transaction(9999990);
		assertEquals(0, result);
	}
	
	@Test
	public void testPayment2() { 
		User user;
		CreditCard card;
		card = new CreditCard(55662, 9000);
		user = new User("Jemma", card);
		int result = user.transaction(1000);
		assertEquals(1, result);
	}
	
	@Test
	public void testPayment3() { 
		User user;
		CreditCard card;
		card = new CreditCard(55662, 9000);
		user = new User("Jemma", card);
		int result = user.transaction(-10000);
		assertEquals(0, result);
	}
	
	@Test
	public void testPayment4() {
		User user;
		CreditCard card;
		card = new CreditCard(55662, 9000);
		user = new User("Jemma", card);
		int result = user.transaction(-2000);
		assertEquals(1, result);
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
