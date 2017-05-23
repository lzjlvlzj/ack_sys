package org.ack.auth.authenticate.annotation;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

public class AckPermissionTest {

	@Test
	public void test1() throws Exception {
		String className = "org.ack.auth.authenticate.annotation.MyClass";
		String methodName = "myMethod";
		Method method = Class.forName(className).getDeclaredMethod(methodName);
		AckPermission ap = method.getAnnotation(AckPermission.class);
		System.out.println(ap.value());
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
