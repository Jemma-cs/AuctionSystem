package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import AuctionSystem.*;

public class testMain {
	@BeforeEach
	public void setUp() throws Exception {
		UserList.getInstance().clearList();
		AuctionList.getInstance().clearList();
		User.init();
		Auction.init();
	}

	///Invalid
	@Test
	public void test_invalid() throws Exception {
		setOutput();
		String inStr="\r\n" + 
				"invalid command\r\n";
		System.setIn(new ByteArrayInputStream(inStr.getBytes()));

		String[] args = null;
		Main.main(args);
		String expectedOut="Auction System\r\n" + 
				"> invalid command\r\n" + 
				"Invalid command! Please retry.\r\n" + 
				"(You may need to login as a user.)\r\n";
		assertEquals(expectedOut, getOutput());
	}
	
	///Admin
	@Test
	public void test_admin1() throws Exception {
		setOutput();
		String inStr="admin|browse activated auctions\r\n" + 
				"admin|browse unaudited auctions\r\n" + 
				"admin|display all users\r\n" + 
				"admin|invalid commands\r\n";
		System.setIn(new ByteArrayInputStream(inStr.getBytes()));

		String[] args = null;
		Main.main(args);
		String expectedOut="Auction System\r\n" + 
				"> admin|browse activated auctions\r\n" + 
				"==========================\r\n" + 
				"List of activated Auctions\r\n" + 
				"==========================\r\n" + 
				"==========================\r\n" + 
				"> admin|browse unaudited auctions\r\n" + 
				"==========================\r\n" + 
				"List of Unaudited Auctions\r\n" + 
				"==========================\r\n" + 
				"==========================\r\n" + 
				"> admin|display all users\r\n" + 
				"==========================\r\n" + 
				"List of all users created\r\n" + 
				"==========================\r\n" + 
				"==========================\r\n" + 
				"> admin|invalid commands\r\n" + 
				"Invalid command! Please retry.\r\n" + 
				"(You may need to login as a user.)\r\n";
		assertEquals(expectedOut, getOutput());
	}
	@Test
	public void test_admin2() throws Exception {
		setOutput();
		String inStr="admin|approve auction|1\r\n" + 
				"admin|decline auction|1\r\n" + 
				"admin|search auction|1\r\n" + 
				"admin|invalid command|0\r\n";
		System.setIn(new ByteArrayInputStream(inStr.getBytes()));

		String[] args = null;
		Main.main(args);
		String expectedOut="Auction System\r\n" + 
				"> admin|approve auction|1\r\n" + 
				"Auction to be approved does not exist in the unaudited list.\r\n" + 
				"> admin|decline auction|1\r\n" + 
				"Auction to be declined does not exist or has already been approved.\r\n" + 
				"> admin|search auction|1\r\n" + 
				"Auction with ID: 1 not found.\r\n" + 
				"> admin|invalid command|0\r\n" + 
				"Invalid command! Please retry.\r\n" + 
				"(You may need to login as a user.)\r\n";
		assertEquals(expectedOut, getOutput());
	}
	@Test
	public void test_admin3() throws Exception {
		setOutput();
		String inStr="admin|send msg to user|0|msg\r\n" + 
				"admin|invalid command|0|msg\r\n" + 
				"admin|a|very|long|command\r\n";
		System.setIn(new ByteArrayInputStream(inStr.getBytes()));

		String[] args = null;
		Main.main(args);
		String expectedOut="Auction System\r\n" + 
				"> admin|send msg to user|0|msg\r\n" +
				"> admin|invalid command|0|msg\r\n" + 
				"Invalid command! Please retry.\r\n" + 
				"(You may need to login as a user.)\r\n" + 
				"> admin|a|very|long|command\r\n" + 
				"Invalid command! Please retry.\r\n" + 
				"(You may need to login as a user.)\r\n";
		assertEquals(expectedOut, getOutput());
	}
	
	///Special
	@Test
	public void test_account() throws Exception {
		setOutput();
		String inStr="create account|Esther CHE|001|1000.05\r\n" + 
				"create account|Claire Sponser|007|13000.00\r\n" + 
				"login|3\r\n" + 
				"login|1\r\n" + 
				"logout\r\n" + 
				"logout\r\n";
		System.setIn(new ByteArrayInputStream(inStr.getBytes()));

		String[] args = null;
		Main.main(args);
		String expectedOut="Auction System\r\n" + 
				"> create account|Esther CHE|001|1000.05\r\n" + 
				"Hello Esther CHE! Your account is created. UserID: 1\r\n" + 
				"> create account|Claire Sponser|007|13000.00\r\n" + 
				"Hello Claire Sponser! Your account is created. UserID: 2\r\n" + 
				"> login|3\r\n" + 
				"User does not exist. Please create an account.\r\n" + 
				"> login|1\r\n" + 
				"Welcome, Esther CHE!\r\n" + 
				"> logout\r\n" + 
				"User Esther CHE log out successfully.\r\n" + 
				"> logout\r\n" + 
				"No user is signed in now.\r\n";
		assertEquals(expectedOut, getOutput());
	}
	
	///User
	@Test
	public void test_user1() throws Exception {
		setOutput();
		String inStr="create account|Esther CHE|001|1000.05\r\n" + 
				"login|1\r\n" + 
				"display my info\r\n" + 
				"display my items\r\n" + 
				"display all tags\r\n" + 
				"display my msgs\r\n" + 
				"browse activated auctions\r\n" + 
				"display all users\r\n" + 
				"invalid command\r\n";
		System.setIn(new ByteArrayInputStream(inStr.getBytes()));

		String[] args = null;
		Main.main(args);
		String expectedOut="Auction System\r\n" + 
				"> create account|Esther CHE|001|1000.05\r\n" + 
				"Hello Esther CHE! Your account is created. UserID: 1\r\n" + 
				"> login|1\r\n" + 
				"Welcome, Esther CHE!\r\n" + 
				"> display my info\r\n" + 
				"Name: Esther CHE    UserID: 1\r\n" + 
				"Number of created items: 0\r\n" + 
				"Card number: 1    Balance: 1000.05\r\n" + 
				"> display my items\r\n" + 
				"No item in list.\r\n" + 
				"> display all tags\r\n" + 
				"1. New\r\n" + 
				"2. Virtual\r\n" + 
				"> display my msgs\r\n" + 
				"> browse activated auctions\r\n" + 
				"==========================\r\n" + 
				"List of activated Auctions\r\n" + 
				"==========================\r\n" + 
				"==========================\r\n" + 
				"> display all users\r\n" + 
				"==========================\r\n" + 
				"List of all users created\r\n" + 
				"==========================\r\n" + 
				"1. Name: Esther CHE    UserID: 1\r\n" + 
				"==========================\r\n" + 
				"> invalid command\r\n" + 
				"Invalid command! Please retry.\r\n";
		assertEquals(expectedOut, getOutput());
	}
	@Test
	public void test_user2() throws Exception {
		setOutput();
		String inStr="create account|Esther CHE|001|1000.05\r\n" + 
				"login|1\r\n" + 
				"delete item|1\r\n" + 
				"withdraw auction|1\r\n" + 
				"confirm transaction|1\r\n" + 
				"search auction by id|1\r\n" + 
				"search auction by tag|New\r\n" + 
				"finish payment|1\r\n" + 
				"invalid command|0\r\n";
		System.setIn(new ByteArrayInputStream(inStr.getBytes()));

		String[] args = null;
		Main.main(args);
		String expectedOut="Auction System\r\n" + 
				"> create account|Esther CHE|001|1000.05\r\n" + 
				"Hello Esther CHE! Your account is created. UserID: 1\r\n" + 
				"> login|1\r\n" + 
				"Welcome, Esther CHE!\r\n" + 
				"> delete item|1\r\n" + 
				"Deletion failed. Item with ID 1 is not found.\r\n" + 
				"> withdraw auction|1\r\n" + 
				"Deletion failed! Auction with ID 1 is not found!\r\n" + 
				"> confirm transaction|1\r\n" + 
				"Confirmation failed! Auction with ID 1 is not found!\r\n" + 
				"> search auction by id|1\r\n" + 
				"Auction with ID: 1 not found.\r\n" + 
				"> search auction by tag|New\r\n" + 
				"==========================\r\n" + 
				"List of requested Auctions\r\n" + 
				"==========================\r\n" + 
				"Number of results: 0\r\n" + 
				"==========================\r\n" + 
				"> finish payment|1\r\n" + 
				"Confirmation failed! Auction with ID 1 is not found!\r\n" + 
				"> invalid command|0\r\n" + 
				"Invalid command! Please retry.\r\n";
		assertEquals(expectedOut, getOutput());
	}
	@Test
	public void test_user3() throws Exception {
		setOutput();
		String inStr="create account|Esther CHE|001|1000.05\r\n" + 
				"login|1\r\n" + 
				"change credit card|000|5000\r\n" + 
				"display my info\r\n" + 
				"add item|Note book|A new note book\r\n" + 
				"add tag to item|1|New\r\n" + 
				"delete tag from item|1|New\r\n" + 
				"bid for auction|1|100\r\n" + 
				"invalid command|0|0\r\n";
		System.setIn(new ByteArrayInputStream(inStr.getBytes()));

		String[] args = null;
		Main.main(args);
		String expectedOut="Auction System\r\n" + 
				"> create account|Esther CHE|001|1000.05\r\n" + 
				"Hello Esther CHE! Your account is created. UserID: 1\r\n" + 
				"> login|1\r\n" + 
				"Welcome, Esther CHE!\r\n" + 
				"> change credit card|000|5000\r\n" + 
				"> display my info\r\n" + 
				"Name: Esther CHE    UserID: 1\r\n" + 
				"Number of created items: 0\r\n" + 
				"Card number: 0    Balance: 5000.00\r\n" + 
				"> add item|Note book|A new note book\r\n" + 
				"Item 1 Note book is added successfully.\r\n" + 
				"> add tag to item|1|New\r\n" + 
				"Succeeded. Tag New is added to item 1 successfully.\r\n" + 
				"> delete tag from item|1|New\r\n" + 
				"Succeeded. Tag New is deleted from item 1 successfully.\r\n" + 
				"> bid for auction|1|100\r\n" + 
				"Bidding failed. This auctID does not exist or is unaudited.\r\n" + 
				"> invalid command|0|0\r\n" + 
				"Invalid command! Please retry.\r\n";
		assertEquals(expectedOut, getOutput());
	}
	@Test
	public void test_user4() throws Exception {
		setOutput();
		String inStr="create account|Esther CHE|001|1000.05\r\n" + 
				"login|1\r\n" + 
				"add item|Note book|A new note book\r\n" + 
				"apply for auction|1|10.00|50.00\r\n" + 
				"invalid command|0|0|0\r\n";
		System.setIn(new ByteArrayInputStream(inStr.getBytes()));

		String[] args = null;
		Main.main(args);
		String expectedOut="Auction System\r\n" + 
				"> create account|Esther CHE|001|1000.05\r\n" + 
				"Hello Esther CHE! Your account is created. UserID: 1\r\n" + 
				"> login|1\r\n" + 
				"Welcome, Esther CHE!\r\n" + 
				"> add item|Note book|A new note book\r\n" + 
				"Item 1 Note book is added successfully.\r\n" + 
				"> apply for auction|1|10.00|50.00\r\n" + 
				"New auction is created and pending for audition.\r\n" + 
				"> invalid command|0|0|0\r\n" + 
				"Invalid command! Please retry.\r\n";
		assertEquals(expectedOut, getOutput());
	}
	@Test
	public void test_user5() throws Exception {
		setOutput();
		String inStr="create account|Esther CHE|001|1000.05\r\n" + 
				"login|1\r\n" + 
				"A|Very|Very|Long|Command\r\n";
		System.setIn(new ByteArrayInputStream(inStr.getBytes()));

		String[] args = null;
		Main.main(args);
		String expectedOut="Auction System\r\n" + 
				"> create account|Esther CHE|001|1000.05\r\n" + 
				"Hello Esther CHE! Your account is created. UserID: 1\r\n" + 
				"> login|1\r\n" + 
				"Welcome, Esther CHE!\r\n" + 
				"> A|Very|Very|Long|Command\r\n" + 
				"Invalid command! Please retry.\r\n";
		assertEquals(expectedOut, getOutput());
	}
	
	@Test
	public void test_process01() throws Exception {
		setOutput();
		String inStr="create account|Esther|1|100\r\n" + 
				"login|1\r\n" + 
				"add item|laptop|a laptop\r\n" + 
				"add tag to item|1|New\r\n" + 
				"apply for auction|1|2000|3000\r\n" + 
				"admin|approve auction|1\r\n" + 
				"create account|Claire|2|5000\r\n" + 
				"login|2\r\n" + 
				"bid for auction|1|2100\r\n" + 
				"finish payment|1\r\n" + 
				"login|1\r\n" + 
				"finish payment|1\r\n" + 
				"confirm transaction|1\r\n" + 
				"display my msgs\r\n" + 
				"login|2\r\n" + 
				"display my msgs\r\n" + 
				"finish payment|2\r\n" + 
				"finish payment|1\r\n" + 
				"display my info\r\n" + 
				"login|1\r\n" + 
				"display my msgs\r\n" + 
				"display my info\r\n";
		System.setIn(new ByteArrayInputStream(inStr.getBytes()));

		String[] args = null;
		Main.main(args);
		String expectedOut="Auction System\r\n" + 
				"> create account|Esther|1|100\r\n" + 
				"Hello Esther! Your account is created. UserID: 1\r\n" + 
				"> login|1\r\n" + 
				"Welcome, Esther!\r\n" + 
				"> add item|laptop|a laptop\r\n" + 
				"Item 1 laptop is added successfully.\r\n" + 
				"> add tag to item|1|New\r\n" + 
				"Succeeded. Tag New is added to item 1 successfully.\r\n" + 
				"> apply for auction|1|2000|3000\r\n" + 
				"New auction is created and pending for audition.\r\n" + 
				"> admin|approve auction|1\r\n" + 
				"Unaudited auction found\r\n" + 
				"> create account|Claire|2|5000\r\n" + 
				"Hello Claire! Your account is created. UserID: 2\r\n" + 
				"> login|2\r\n" + 
				"Welcome, Claire!\r\n" + 
				"> bid for auction|1|2100\r\n" + 
				"Your price is the highest now.\r\n" + 
				"> finish payment|1\r\n" + 
				"Auction found.\r\n" + 
				"This auction is not available for finishing payment.\r\n" + 
				"> login|1\r\n" + 
				"Welcome, Esther!\r\n" + 
				"> finish payment|1\r\n" + 
				"Auction found.\r\n" + 
				"Confirmation failed! Auction (ID: 1) is owned by yourself.\r\n" + 
				"> confirm transaction|1\r\n" + 
				"Auction found.\r\n" + 
				"Confirmation of auction (ID:1) succeeds. Message has been sent to the buyer.\r\n" + 
				"> display my msgs\r\n" + 
				"1.\n" + 
				"System: Your auction (ID: 1) is approved by the admin.\r\n" + 
				"> login|2\r\n" + 
				"Welcome, Claire!\r\n" + 
				"> display my msgs\r\n" + 
				"1.\n" + 
				"System: Your bidding in auction (ID: 1) is confirmed by the seller. Please remember to finish the payment.\r\n" + 
				"> finish payment|2\r\n" + 
				"Confirmation failed! Auction with ID 2 is not found!\r\n" + 
				"> finish payment|1\r\n" + 
				"Auction found.\r\n" + 
				"Transaction between buyer (ID: 2) and seller (ID: 1) is done.\r\n" + 
				"Paying amount is 2100.0.\r\n" + 
				"> display my info\r\n" + 
				"Name: Claire    UserID: 2\r\n" + 
				"Number of created items: 0\r\n" + 
				"Card number: 2    Balance: 2900.00\r\n" + 
				"> login|1\r\n" + 
				"Welcome, Esther!\r\n" + 
				"> display my msgs\r\n" + 
				"1.\n" + 
				"System: Your auction (ID: 1) is approved by the admin.\r\n" + 
				"2.\n" + 
				"System: Your auction (ID: 1) is done. Transaction price is 2100.0.\r\n" + 
				"> display my info\r\n" + 
				"Name: Esther    UserID: 1\r\n" + 
				"Number of created items: 1\r\n" + 
				"Card number: 1    Balance: 2200.00\r\n";
		assertEquals(expectedOut, getOutput());
	}
	
	@Test
	public void test_process02() throws Exception {
		setOutput();
		String inStr="create account|Esther|1|100\r\n" + 
				"create account|Claire|2|100\r\n" + 
				"create account|Justin|3|100\r\n" + 
				"login|1\r\n" + 
				"add item|laptop|a laptop\r\n" + 
				"apply for auction|1|2000|3000\r\n" + 
				"add tag to item|1|New\r\n" + 
				"admin|approve auction|1\r\n" + 
				"bid for auction|1|2000\r\n" + 
				"login|2\r\n" + 
				"bid for auction|1|2000\r\n" + 
				"bid for auction|1|2100\r\n" + 
				"display my msgs\r\n" + 
				"login|3\r\n" + 
				"bid for auction|1|2000\r\n" + 
				"bid for auction|1|2200\r\n" + 
				"login|2\r\n" + 
				"display my msgs\r\n";
		System.setIn(new ByteArrayInputStream(inStr.getBytes()));

		String[] args = null;
		Main.main(args);
		String expectedOut="Auction System\r\n" + 
				"> create account|Esther|1|100\r\n" + 
				"Hello Esther! Your account is created. UserID: 1\r\n" + 
				"> create account|Claire|2|100\r\n" + 
				"Hello Claire! Your account is created. UserID: 2\r\n" + 
				"> create account|Justin|3|100\r\n" + 
				"Hello Justin! Your account is created. UserID: 3\r\n" + 
				"> login|1\r\n" + 
				"Welcome, Esther!\r\n" + 
				"> add item|laptop|a laptop\r\n" + 
				"Item 1 laptop is added successfully.\r\n" + 
				"> apply for auction|1|2000|3000\r\n" + 
				"New auction is created and pending for audition.\r\n" + 
				"> add tag to item|1|New\r\n" + 
				"Failed. This item has already been published as an auction!\r\n" + 
				"> admin|approve auction|1\r\n" + 
				"Unaudited auction found\r\n" + 
				"> bid for auction|1|2000\r\n" + 
				"Bidding failed. This auction is owned by yourself!\r\n" + 
				"> login|2\r\n" + 
				"Welcome, Claire!\r\n" + 
				"> bid for auction|1|2000\r\n" + 
				"Your price is the highest now.\r\n" + 
				"> bid for auction|1|2100\r\n" + 
				"Your price is the highest now.\r\n" + 
				"> display my msgs\r\n" + 
				"> login|3\r\n" + 
				"Welcome, Justin!\r\n" + 
				"> bid for auction|1|2000\r\n" + 
				"Bidding failed. Your price is not higher than the current price.\r\n" + 
				"> bid for auction|1|2200\r\n" + 
				"Your price is the highest now.\r\n" + 
				"> login|2\r\n" + 
				"Welcome, Claire!\r\n" + 
				"> display my msgs\r\n" + 
				"1.\n" + 
				"System: Your highest price in auction 1 is replaced by a higher price from Justin (ID: 3).\r\n";
		assertEquals(expectedOut, getOutput());
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
