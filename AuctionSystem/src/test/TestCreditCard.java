package test;
import static org.junit.Assert.*;

import org.junit.Test;
import AuctionSystem.CreditCard;


public class TestCreditCard {
	private CreditCard card;
	@Test
	public void testToString() {
		card = new CreditCard(5566, 8000);
		String result = card.toString();
		assertEquals("Card number: 5566    Balance: 8000.00", result);
	}
	
	@Test
	public void testGetBalance() {
		card = new CreditCard(5566, 8000);
		double result = card.getBalance();
		assertEquals(8000, result, 0.001);
	}
	
	@Test
	public void testDecreaseBal1() {
		card = new CreditCard(5566, 8000);
		int result = card.decreaseBal(114514);
		assertEquals(0, result);
	}
	
	@Test
	public void testDecreaseBal2() {
		card = new CreditCard(5566, 8000);
		int result = card.decreaseBal(800);
		assertEquals(1, result);
	}
	
	@Test
	public void testIncreaseBal1() {
		card = new CreditCard(5566, 8000);
		int result = card.increaseBal(114);
		assertEquals(1, result);
	}
	
	@Test
	public void testIncreaseBal2() {
		card = new CreditCard(5566, 8000);
		int result = card.increaseBal(1145141919);
		assertEquals(0, result);
	}

}
