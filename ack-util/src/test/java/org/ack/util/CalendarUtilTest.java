package org.ack.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalendarUtilTest {

	@Test
	public void testGetLogStartDay() {
		String m = CalendarUtil.getLogStartDay();
		System.out.println(m);
	}
	
	@Test
	public void testGetLogEndDay() {
		String m = CalendarUtil.getLogEndDay();
		System.out.println(m);
	}
	@Test
	public void testGetCurrentMonth() {
		int m = CalendarUtil.getCurrentMonth();
		System.out.println(m);
	}
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
