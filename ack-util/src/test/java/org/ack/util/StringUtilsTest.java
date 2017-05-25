package org.ack.util;

import org.junit.Assert;

import junit.framework.TestCase;

public class StringUtilsTest extends TestCase {

	public void testIsNumberic() {
		String s = "1233333fkd";
		//s = "123";
		boolean b = StringUtils.isNumeric(s);
		Assert.assertTrue(b);
	}
	public void testArray2String() {
		Integer[] arrays = {1,2,3};
		String s = StringUtils.array2String(arrays);
		System.out.println(s);
	}
	public void test() {
		fail("Not yet implemented");
	}

}
