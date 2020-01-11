package org.ack.sys.base.core.auth;

import java.util.Set;

public interface SecurityManager {
	/**
	 * 校验用户
	 * 
	 * @return
	 */
	public int authenticate();

	/**
	 * 校验权限字符串
	 * 
	 * @param currentPermission
	 * @param permissions
	 * @return
	 */
	public boolean match(String currentPermission, Set<String> permissions);
}
