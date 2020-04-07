package org.ack.sys.pojo;

import org.ack.sys.base.util.ReflectUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class UserTest {

	@Test
	void testValue() {
		User user = new User();
		user.setId(1L);
		int r = ReflectUtil.setFieldValue(user, "creator", 12L);
		System.out.println(user.getCreator());
		assertEquals(1, r);
	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
