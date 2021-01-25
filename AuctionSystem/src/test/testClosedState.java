package test;

import static org.junit.Assert.*;
import org.junit.Test;
import AuctionSystem.*;

public class testClosedState {

	@Test
	public void testStateString(){
		AuctionState aState = new ClosedState();
		assertEquals("Closed", aState.toString());
	}
	
	@Test
	public void testBidboolean(){
		AuctionState aState = new ClosedState();
		assertEquals(false, aState.isOpenForBidding());
	}
	
	@Test
	public void testPaymentboolean(){
		AuctionState aState = new ClosedState();
		assertEquals(false, aState.isOpenForPayment());
	}
}
