package test;

import static org.junit.Assert.*;
import org.junit.Test;
import AuctionSystem.*;

public class testUnauditedState {


	@Test
	public void testStateString(){
		AuctionState aState = new UnauditedState();
		assertEquals("Unaudited", aState.toString());
	}
	
	@Test
	public void testBidboolean(){
		AuctionState aState = new UnauditedState();
		assertEquals(false, aState.isOpenForBidding());
	}
	
	@Test
	public void testPaymentboolean(){
		AuctionState aState = new UnauditedState();
		assertEquals(false, aState.isOpenForPayment());
	}
}
