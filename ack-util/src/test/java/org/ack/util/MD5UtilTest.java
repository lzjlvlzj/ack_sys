package org.ack.util;

import junit.framework.TestCase;

public class MD5UtilTest extends TestCase {

	public void testMd5String() {
		fail("Not yet implemented");
	}

	public void testMd5ByteArray() {
		String s = "ack_sys_3698" + "689";
		String p = MD5Util.md5(s.getBytes());
		System.out.println(p);
	}

}
