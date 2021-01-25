package test;

import static org.junit.Assert.*;
import org.junit.Test;
import AuctionSystem.*;

public class testOnsaleState {

	@Test
	public void testStateString(){
		AuctionState aState = new OnSaleState();
		assertEquals("On Sale", aState.toString());
	}
	
	@Test
	public void testBidboolean(){
		AuctionState aState = new OnSaleState();
		assertEquals(true, aState.isOpenForBidding());
	}
	
	@Test
	public void testPaymentboolean(){
		AuctionState aState = new OnSaleState();
		assertEquals(false, aState.isOpenForPayment());
	}
}
