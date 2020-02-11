package org.ack.sys.base.core.auth;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class AuthorizerTest {

	@Test
	void testMatch() {
		String s = "sys:user:view or sys:personal:view or sys:dept:view";
		Set<String> set = new HashSet<String>();
		set.add("sys:personal:view");
		Authorizer  auth = new Authorizer();
		boolean b = auth.match(s, set);
		assertTrue(b);
	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
