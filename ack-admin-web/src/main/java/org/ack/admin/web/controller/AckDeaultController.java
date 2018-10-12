package org.ack.admin.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

/**
 * 默认list全部查询
 * <p>
 * 无需分页的(小数据量),只需要简单list的继承这个。
 * @author ack
 *
 * @param <T>
 * @param <PK>
 */
public abstract class AckDeaultController<T extends Object, PK extends Serializable>
		extends AckController<T, PK> {
	/**
	 * 查询所有
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public List<T> list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<T> list = getService().findAll();
		return list;
	}
}
