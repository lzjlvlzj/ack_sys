package org.ack.sys.base.core.auth;

import org.ack.sys.base.util.MD5Util;
import org.ack.sys.base.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 用户认证
 * 
 * @author ack
 *
 */
public class Authenticator implements Authentication {

	private static final Logger logger = LoggerFactory.getLogger(Authenticator.class);
	
	private String passEncoder = "";

	public Authenticator() {

	}

	public Authenticator(String passEncoder) {
       this.passEncoder = passEncoder;
	}

	@Override
	public int authenticate(String username, String password) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			logger.debug("用户登陆参数错误。");
			return 1;
		}
		String pwd = password;
        if("md5".equals(passEncoder)) {
        	pwd = MD5Util.md5(password);
        	logger.debug("加密后的密码: {}", pwd);
        }
		return 0;
	}

}
