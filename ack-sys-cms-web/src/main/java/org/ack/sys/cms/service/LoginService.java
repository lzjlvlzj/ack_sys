package org.ack.sys.cms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.sys.cms.web.template.LoginUser;

public interface LoginService {
	/** 登录
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	public int login(HttpServletRequest request, HttpServletResponse response, LoginUser user);
}
