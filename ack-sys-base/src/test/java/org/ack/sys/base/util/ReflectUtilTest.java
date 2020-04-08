package org.ack.sys.base.util;

import static org.junit.jupiter.api.Assertions.*;

import org.ack.sys.base.persist.page.ColumnFilter;
import org.junit.jupiter.api.Test;

class ReflectUtilTest {

	@Test
	void testSetFieldValue() {
		ColumnFilter cf = new ColumnFilter();
		cf.setName("qq");
		int r = ReflectUtil.setFieldValue(cf, "value", "325641");
		String value = cf.getValue();
		System.out.println(value);
		assertEquals(1, r);
	}

	@Test
	void testGetFieldValue() {
		fail("Not yet implemented");
	}

	@Test
	void testGetField() {
		fail("Not yet implemented");
	}

}
