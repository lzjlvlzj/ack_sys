package org.ack.sys.base.core.auth;

/**
 * 授权相关
 * 
 * @author ack
 *
 */
public interface Authentication {
	/**
	 * 认证用户
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public int authenticate(String username, String password);

}
