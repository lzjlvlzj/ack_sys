package org.ack.sys.base.core.auth;

import java.lang.reflect.Method;
import java.util.Set;

public interface Authorization {
	
	/**
	 * 校验权限字符串
	 * 
	 * @param currentPermission
	 * @param permissions
	 * @return
	 */
	public boolean match(String currentPermission, Set<String> permissions);

	/**
	 * 校验方法是否有权限
	 * 
	 * @param method
	 * @param permissions
	 * @return
	 */
	public boolean hasPermission(Method method, Set<String> permissions);
}
