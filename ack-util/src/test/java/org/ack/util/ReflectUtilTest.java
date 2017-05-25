package org.ack.util;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import org.junit.Test;

public class ReflectUtilTest {

	@Test
	public void testGetFieldType() {
		Field[] fields  = ReflectTest.class.getDeclaredFields();
		for(Field field : fields){
			Type type = field.getGenericType();
			System.out.println(type.getTypeName());
		}
	}
	@Test
	public void testGetAllFieldNames() {
		String[] names = ReflectUtil.getAllFieldNames(ReflectTest.class);
		for(String name : names){
			System.out.println(name);
		}
	}

	@Test
	public void testGetRealClassType() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMethod() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFieldValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetField() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
