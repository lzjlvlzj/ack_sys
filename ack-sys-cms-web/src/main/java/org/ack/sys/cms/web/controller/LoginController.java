package org.ack.sys.cms.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ack.sys.base.common.Content;
import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.common.Token;
import org.ack.sys.base.config.ErrorMessageConfig;
import org.ack.sys.base.util.MD5Util;
import org.ack.sys.base.util.StringUtils;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.service.UserService;
import org.ack.sys.cms.web.template.LoginUser;
import org.ack.sys.cms.web.template.SessionUser;
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
		HttpSession session = request.getSession();
		//request.get
		// 检查验证码
		String captcha = user.getCaptcha();
		if (StringUtils.isBlank(captcha)) {
			return new ResponseResult(ErrorMessageConfig.LOGIN_CAPTCHA_INVALIDATE.getCode(),
					ErrorMessageConfig.LOGIN_CAPTCHA_INVALIDATE.getMessage());
		} else {
			captcha = captcha.trim();
			String sessionCaptcha = (String)session.getAttribute(Content.SESSION_KEY_KAPTCHA);
			if(!captcha.equalsIgnoreCase(sessionCaptcha)) {
				return new ResponseResult(ErrorMessageConfig.LOGIN_CAPTCHA_INVALIDATE.getCode(),
						ErrorMessageConfig.LOGIN_CAPTCHA_INVALIDATE.getMessage());
			}
			
		}
		// 检查用户名密码
		ResponseResult rt = null;
		String token = Token.getToken(new Date());
		String username = user.getUsername();
		User dbUser = userServiceImpl.findUserByUserName(username);
		boolean lg = checkLogin(user, dbUser);
		if(!lg) {
			rt = new ResponseResult(ErrorMessageConfig.LOGIN_USERNAME_PASSWORD_INVALIDATE.getCode(),
					ErrorMessageConfig.LOGIN_USERNAME_PASSWORD_INVALIDATE.getMessage());
			logger.debug("用户登陆失敗");
		}else{
			rt = new ResponseResult(200, token);
			session.setAttribute(Content.SESSION_KEY_USER, new SessionUser(token, dbUser));
			logger.debug("用户登陆成功");
		}
		
		return rt;
	}

	private boolean checkLogin(LoginUser user, User dbUser) {
		if(null == user 
				|| StringUtils.isBlank(user.getUsername()) 
				|| StringUtils.isBlank(user.getPassword())) {
			logger.debug("用户登陆参数错误。");
			return false;
		}
		boolean b = false;
		if(null == dbUser) {
			logger.debug("用户{}不存在", user.getUsername());
			b =  false;
		} else {
			String pwd = MD5Util.md5(user.getPassword());
			if(!pwd.equals(user.getPassword())) {
				logger.debug("用户{}輸入的密碼不正確.", user.getUsername());
				b = false;
			} 
			b = true;
		}
		return b;
	}
}
