package org.ack.sys.base.util;

import java.lang.reflect.Field;

/**
 * 反射工具
 * 
 * @author ack
 *
 */
public class ReflectUtil {
	
	/** 设置反射对象的值
	 * @param obj
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public static int setFieldValue(Object obj, String fieldName, Object fieldValue) {
		Field field = getField(obj, fieldName);
		int r = -1;
		if (field != null) {
			field.setAccessible(true);
			try {
				field.set(obj, fieldValue);
				r = 1;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}
		return r;
	}

	/** 获得反射对象的值
	 * @param obj
	 * @param fieldName
	 * @return
	 */
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
				//e.printStackTrace();
			}
		}
		return field;
	}

}
