package org.ack.sys.cms.exception;

import org.ack.sys.base.core.BaseMessage;
import org.ack.sys.base.exception.BaseException;
import org.ack.sys.cms.config.web.LonginResponseConfig;

public class UserLockedException extends BaseException {

	private static final long serialVersionUID = 5399332036840559794L;
	public UserLockedException() {
		super(LonginResponseConfig.LOGIN_USER_IS_LOCKED);
	}

	public UserLockedException(String msg) {
		super(msg);
	}
	
	public UserLockedException(BaseMessage msg) {
		super(msg);
	}
}
