package org.ack.admin.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.auth.authenticate.Authenticate;
import org.ack.common.Content;
import org.ack.pojo.User;
import org.ack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录
 * 
 * @author ack
 *
 */
@Controller
public class LoginController {

	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userServiceImpl;

	/**
	 * 登录页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login/ui")
	public String ackLoginUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (log.isDebugEnabled()) {
			log.debug("进入登录页面");
		}
		return "login";
	}

	/**
	 * 登录功能
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String ackLogin(HttpServletRequest request,
			HttpServletResponse response, Model model, User user) {
		if (log.isDebugEnabled()) {
			log.debug("登录");
		}
		Authenticate au = new Authenticate(userServiceImpl);
		if (au.checkUser(user) > 0) {
			if (log.isDebugEnabled()) {
				log.debug("登录失败");
			}
			return "redirect:/login/ui";
		}
		User u = au.getUser();
		request.getSession().setAttribute(Content.USER, u);
		return "redirect:/";
	}

}
