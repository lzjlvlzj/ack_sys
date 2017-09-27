package org.ack.base.web;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ack.common.Content;
import org.ack.pojo.Role;
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

	/**
	 * 是否只查看当前部门
	 * 
	 * @param request
	 * @return
	 */
	protected boolean onlyDepartment(HttpServletRequest request) {
		return onlyDepartment(null, request);
	}
	/**
	 * 是否只查看当前部门
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	protected boolean onlyDepartment(User user, HttpServletRequest request) {
		if(null == user){
			user = getCurrentUser(request);
		}
		Set<Role> roles = user.getRoles();
		boolean b = true;
		for (Role role : roles) {
			Integer viewStatus = role.getViewStatus();
			if (viewStatus == 1) {
				b = false;
				break;
			}
		}
		return b;
	}

}
