package org.ack.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射工具
 * 
 * @author ack
 *
 */
public class ReflectUtil {
	
	
	public static Class<?> getInstance(String s){
		Class<?> cls = null;
		try {
			cls = Class.forName(s);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cls;
	}
	
	
	/**
	 * 获得所有字段名称
	 * */
	public static String[] getAllFieldNames(Class<?> clazz){
		
		Field[] fields = clazz.getDeclaredFields();
		if(null == fields || fields.length == 0){
			return null;
		}
		String[] names = new String[fields.length];
		for(int i = 0; i < fields.length; i++){
			Field field  = fields[i];
			names[i] = field.getName();
		}
		return names;
	}

	/**
	 * 获得泛型类型
	 * 
	 * @param clazz
	 * @return
	 */
	public static Type[] getRealClassType(Class<?> clazz) {
		ParameterizedType pt = (ParameterizedType) clazz.getGenericSuperclass();
		Type[] type = pt.getActualTypeArguments();
		return type;
	}

	/**
	 * 获得method
	 * */
	public static Method getMethod(String className, String methodName) {
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
