package org.ack.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.base.service.AckMapperService;
import org.ack.persist.page.Page;
import org.ack.pojo.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	/**
	 * 默认分页查询
	 * <p>
	 * 从1到10,按时间降序排列,一定要注意你的字段是否有<code>createtime</code>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/page")
	@ResponseBody
	public Page<T> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() User user,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		// 构造查询page参数
		Page<T> page = new Page<T>(currentPage, count);
		page.setOrderColumn(orderColumn);
		page.setOrderType(orderType);
		page.setCondition(map);
		// 查询
		page = getService().findPage(page);
		return page;
	}

}
