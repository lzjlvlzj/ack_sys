package org.ack.sys.cms.exception;

import org.ack.sys.base.core.BaseMessage;
import org.ack.sys.base.exception.BaseException;
import org.ack.sys.cms.config.web.LonginResponseConfig;

/**
 * 用户密码不正确异常
 * 
 * @author ack
 *
 */
public class UserAndPasswordException extends BaseException {

	private static final long serialVersionUID = 7025512798972557660L;

	public UserAndPasswordException() {
		super(LonginResponseConfig.LOGIN_USERNAME_PASSWORD_INVALIDATE);
	}

	public UserAndPasswordException(String msg) {
		super(msg);
	}
	
	public UserAndPasswordException(BaseMessage msg) {
		super(msg);
	}

}
