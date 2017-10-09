package org.ack.admin.web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.base.service.AckMapperService;
import org.ack.base.web.PageController;
import org.ack.common.ResultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;

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

	private static final Logger logger = LoggerFactory
			.getLogger(AckController.class);

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
	public Integer insert(HttpServletRequest request,
			HttpServletResponse response, Model model, T t) {
		int result = getService().insert(t);
		return result;
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
	public ResultMessage insert(HttpServletRequest request,
			HttpServletResponse response, Model model, T t, BindingResult result) {
		boolean b = result.hasErrors();
		if (b) {
			FieldError fe = result.getFieldError();
			String msg = fe.getDefaultMessage();
			if (logger.isDebugEnabled()) {
				logger.debug("表单验证错误  : {}", msg);
			}
			return new ResultMessage("0", msg);
		}
		Integer r = getService().insert(t);
		return new ResultMessage(r.toString(), "");
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
	public ResultMessage edit(HttpServletRequest request,
			HttpServletResponse response, Model model, T t, BindingResult result) {
		boolean b = result.hasErrors();
		if (b) {
			FieldError fe = result.getFieldError();
			String msg = fe.getDefaultMessage();
			if (logger.isDebugEnabled()) {
				logger.debug("表单验证错误  : {}", msg);
			}
			return new ResultMessage("0", msg);
		}
		Integer r = getService().update(t);
		return new ResultMessage(r.toString(), "");
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
	public Integer edit(HttpServletRequest request,
			HttpServletResponse response, Model model, T t) {
		int result = getService().update(t);
		return result;
	}

}
