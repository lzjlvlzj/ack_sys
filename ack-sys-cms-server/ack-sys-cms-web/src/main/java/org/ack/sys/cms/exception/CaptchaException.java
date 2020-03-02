package org.ack.sys.cms.exception;

import org.ack.sys.base.config.ErrorMessageConfig;
import org.ack.sys.base.exception.BaseException;

/**验证码异常
 * @author ack
 *
 */
public class CaptchaException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2025298217838204944L;
	
	public CaptchaException() {
		super(ErrorMessageConfig.LOGIN_CAPTCHA_INVALIDATE);
	}

}
