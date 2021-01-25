package test;

import static org.junit.Assert.*;
import org.junit.Test;
import AuctionSystem.*;

public class testTagVirtual {

	@Test
	public void testName(){
		Tag tag = TagVirtual.getInstance();
		assertEquals("Virtual", tag.getName());
	}
	
	@Test
	public void testDesc(){
		Tag tag = TagVirtual.getInstance();
		assertEquals("", tag.getDesc());
	}
}
