package test;

import static org.junit.Assert.*;
import org.junit.Test;
import AuctionSystem.*;

public class testTagNew {
	
	@Test
	public void testName(){
		Tag tag = TagNew.getInstance();
		assertEquals("New", tag.getName());
	}
	
	@Test
	public void testDesc(){
		Tag tag = TagNew.getInstance();
		assertEquals("", tag.getDesc());
	}
}
