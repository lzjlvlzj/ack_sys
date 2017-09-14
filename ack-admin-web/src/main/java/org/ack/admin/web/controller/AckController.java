package org.ack.admin.web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.base.service.AckMapperService;
import org.ack.base.web.PageController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * 
 * @author ack
 *
 * @param <T>
 * @param <PK>
 */
public abstract class AckController<T extends Object, PK extends Serializable>
		extends PageController {

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
