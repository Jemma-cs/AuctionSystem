package test;

import static org.junit.Assert.*;
import org.junit.Test;
import AuctionSystem.*;

public class testUnpublishedState {


	@Test
	public void testStateString(){
		AuctionState aState = new UnpublishedState();
		assertEquals("Unpublished", aState.toString());
	}
	
	@Test
	public void testBidboolean(){
		AuctionState aState = new UnpublishedState();
		assertEquals(false, aState.isOpenForBidding());
	}
	
	@Test
	public void testPaymentboolean(){
		AuctionState aState = new UnpublishedState();
		assertEquals(false, aState.isOpenForPayment());
	}
}
