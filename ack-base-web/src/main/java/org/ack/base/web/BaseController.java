package org.ack.base.web;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ack.base.service.AckMapperService;
import org.ack.common.Content;
import org.ack.pojo.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * contoller父接口
 * <p>
 * 本接口提供了基础的curd，但是页面还需要继承的接口提供.
 * <p>
 * <b>继承这个接口一定要注意，这个跟你实际的权限有很大关系，要根据实际情况看是否要继承.</b>
 * 
 * @author ack
 *
 */
public abstract class BaseController<T extends Object, PK extends Serializable> {

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
	 * @return 获得服务层接口
	 */
	public abstract AckMapperService<T, PK> getService();

	/**
	 * 查询
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/id/{id}")
	@ResponseBody
	public T findById(HttpServletRequest request, HttpServletResponse response,
			Model model, @PathVariable PK id) {
		T t = getService().findById(id);
		return t;
	}

	/**
	 * 添加数据
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Integer insert(HttpServletRequest request,
			HttpServletResponse response, Model model, T t) {
		int result = getService().insert(t);
		return result;
	}

	/**
	 * 根据删除
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	public Integer deleteById(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable PK id) {
		Integer t = getService().deleteById(id);
		return t;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public Integer edit(HttpServletRequest request,
			HttpServletResponse response, Model model, T t) {
		int result = getService().update(t);
		return result;
	}

}
