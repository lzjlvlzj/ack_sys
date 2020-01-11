package org.ack.sys.cms.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ack.sys.base.common.Content;
import org.ack.sys.base.util.StringUtils;
import org.ack.sys.cms.service.LoginService;
import org.ack.sys.cms.web.template.LoginUser;
import org.springframework.stereotype.Service;
/**
 * 
 * */
@Service
public class LoginServiceImpl implements LoginService {

	@Override
	public int login(HttpServletRequest request, HttpServletResponse response, LoginUser user) {
		HttpSession session = request.getSession();
		// 检查验证码
		String captcha = user.getCaptcha();
		if (StringUtils.isBlank(captcha)) {
			return 1;
		} else {
			captcha = captcha.trim();
			String sessionCaptcha = (String)session.getAttribute(Content.SESSION_KEY_KAPTCHA);
			if(!captcha.equalsIgnoreCase(sessionCaptcha)) {
				return 1;
			}
			
		}
		return 0;
	}

}
