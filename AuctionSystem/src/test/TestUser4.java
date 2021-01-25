package test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import AuctionSystem.UserList;
import AuctionSystem.User;
import AuctionSystem.Auction;
import AuctionSystem.AuctionList;
import AuctionSystem.CreditCard;

public class TestUser4 {
	@BeforeEach
	public void setUp() throws Exception {
		UserList.getInstance().clearList();
		AuctionList.getInstance().clearList();
		User.init();
		Auction.init();
	}
	
	@Test
	public void test1() throws Exception {
		setOutput();
		UserList list = UserList.getInstance();
		
		User Altair;
		CreditCard card1;
		card1 = new CreditCard(114, 8000);
		Altair = new User("Altair", card1);
		
		User Oracle;
		CreditCard card2;
		card2 = new CreditCard(666, 15000);
		Oracle = new User("Oracle", card2);
		
		User Jemma;
		CreditCard card3;
		card3 = new CreditCard(5566, 10000);
		Jemma = new User("Jemma", card3);
		
		User William;
		CreditCard card4;
		card4 = new CreditCard(5567, 10000);
		William = new User("William", card4);
		
		Altair.displayAllUsers();
		assertEquals("==========================\r\n"
				+ "List of all users created\r\n"
				+ "==========================\r\n"
				+ "1. Name: Altair    UserID: 1\r\n"
				+ "2. Name: Oracle    UserID: 2\r\n"
				+ "3. Name: Jemma    UserID: 3\r\n"
				+ "4. Name: William    UserID: 4\r\n"
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
