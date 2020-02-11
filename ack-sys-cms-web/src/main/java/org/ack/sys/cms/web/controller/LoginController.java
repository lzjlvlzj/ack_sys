package org.ack.sys.cms.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.common.Token;
import org.ack.sys.cms.config.web.LonginResponseConfig;
import org.ack.sys.cms.exception.CaptchaException;
import org.ack.sys.cms.exception.LoginException;
import org.ack.sys.cms.exception.UserAndPasswordException;
import org.ack.sys.cms.exception.UserLockedException;
import org.ack.sys.cms.service.UserService;
import org.ack.sys.cms.web.template.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userServiceImpl;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginUser user) {
		if (logger.isDebugEnabled()) {
			logger.debug("用户登陆");

		}
		ResponseResult rt = null;
		String token = Token.getToken(new Date());
		LoginUser loginUser = userServiceImpl.login(request, response, user, token);
		if (null == loginUser) {
			throw new LoginException();
		}
		int result = loginUser.getStatus();
		if (result == 1) {
			throw new CaptchaException();
		} else if (result == 2) {
			throw new UserAndPasswordException();
		} else if (result == 3) {
			throw new UserAndPasswordException(LonginResponseConfig.LOGIN_USER_NOT_EXISIT);
		} else if (result == 4) {
			throw new UserLockedException();
		} else if (result == 0) {
			logger.debug("用户登陆成功");
			rt = new ResponseResult(200, loginUser);
		}
		return rt;
	}

}
