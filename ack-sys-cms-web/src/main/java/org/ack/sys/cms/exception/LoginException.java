package org.ack.sys.cms.exception;

import org.ack.sys.base.exception.BaseException;

public class LoginException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3364347924525507781L;
	
	public LoginException() {
		super("登录异常");
	}
	
	public LoginException(String msg) {
		super(msg);
	}
}
