package org.ack.auth.authenticate.annotation;

import static org.junit.Assert.fail;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.ack.auth.authenticate.Authenticate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AckPermissionTest {
	
	String className;
	String methodName;
	Method method;
	
	@Before
	public void init() throws Exception{
		className = "org.ack.auth.authenticate.annotation.MyClass";
		methodName = "myMethod";
		method = Class.forName(className).getDeclaredMethod(methodName);
	}
	
	@Test
	public void testPermission(){
		Authenticate a = new Authenticate();
		Set<String> set = new HashSet<String>();
		set.add("user:list");
		set.add("user:update");
		set.add("user:find");
		
		String strs = "user:update or user:find2";
		boolean b = a.matchPermission(strs, set);
		Assert.assertTrue(b);
	}
	
	@Test
	public void test2() {
		String s = "user:update and user:find2";
		String[] str = s.split("and|or");
		for(String p : str){
			System.out.println(p);
		}
	}
	@Test
	public void test1() throws Exception {
		AckPermission ap = method.getAnnotation(AckPermission.class);
		System.out.println(ap.value());
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
