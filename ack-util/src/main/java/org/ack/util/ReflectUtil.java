package org.ack.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具
 * 
 * @author ack
 *
 */
public class ReflectUtil {
	
	/**
	 * 获得method
	 * */
	public static Method getMethod(String className, String methodName){
		Method method = null;
		try {
			method = Class.forName(className).getDeclaredMethod(methodName);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return method;
	}

	public static Object getFieldValue(Object obj, String fieldName) {
		Object result = null;
		Field field = getField(obj, fieldName);
		if (field != null) {
			field.setAccessible(true);
			try {
				result = field.get(obj);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static Field getField(Object obj, String fieldName) {

		Field field = null;
		for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz
				.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName);
				break;
			} catch (NoSuchFieldException e) {
				// 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
			}
		}
		return field;
	}

}
