package org.ack.util;

import junit.framework.TestCase;

public class StringUtilsTest extends TestCase {

	public void testArray2String() {
		Integer[] arrays = {1,2,3};
		String s = StringUtils.array2String(arrays);
		System.out.println(s);
	}
	public void test() {
		fail("Not yet implemented");
	}

}
