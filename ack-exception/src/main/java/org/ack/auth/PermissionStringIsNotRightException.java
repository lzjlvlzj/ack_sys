package org.ack.auth;

/**
 * 权限字符串不正确
 * */
public class PermissionStringIsNotRightException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7328495848219011974L;

	public PermissionStringIsNotRightException(String msg) {
		super(msg);
	}

}
