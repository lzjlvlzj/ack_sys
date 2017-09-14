package org.ack.base.web;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ack.common.Content;
import org.ack.pojo.User;


/**
 * Controller 父接口
 * 
 * @author ack
 *
 */
public abstract class BaseController {

	/**
	 * get current user
	 * 
	 * @param request
	 * @return
	 */
	public User getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Content.USER);
		return user;
	}

	/**
	 * set current user
	 * 
	 * @param request
	 * @param user
	 */
	public void setCurrentUser(HttpServletRequest request, User user) {
		HttpSession session = request.getSession();
		session.setAttribute(Content.USER, user);
	}
	
}
