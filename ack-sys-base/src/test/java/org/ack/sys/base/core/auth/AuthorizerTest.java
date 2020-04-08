package org.ack.sys.base.core.auth;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthorizerTest {
	
	class User{
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
	private Map<String, User> map = new HashMap<String, User>();
	
	@BeforeEach
	void init() {
		User user = new User();
		user.setName("aaa");
		map.put("user", user);
	}
	
	@Test
	void testMap() {
		User user = map.get("user");
		System.out.println(user.getName());
		user.setName("bbb");
		User u = map.get("user");
		System.out.println(u.getName());
	}

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
