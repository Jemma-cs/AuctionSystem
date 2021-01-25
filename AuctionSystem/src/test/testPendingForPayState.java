package test;

import static org.junit.Assert.*;
import org.junit.Test;
import AuctionSystem.*;

public class testPendingForPayState {

	@Test
	public void testStateString(){
		AuctionState aState = new PendingForPayState();
		assertEquals("Pending for payment", aState.toString());
	}
	
	@Test
	public void testBidboolean(){
		AuctionState aState = new PendingForPayState();
		assertEquals(false, aState.isOpenForBidding());
	}
	
	@Test
	public void testPaymentboolean(){
		AuctionState aState = new PendingForPayState();
		assertEquals(true, aState.isOpenForPayment());
	}
}
