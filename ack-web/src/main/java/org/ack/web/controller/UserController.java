package org.ack.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.base.service.AckMapperService;
import org.ack.persist.page.Page;
import org.ack.pojo.User;
import org.ack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User, Long>{
	
	@Autowired
	private UserService userServiceImpl;
	
	@Override
	public AckMapperService<User, Long> getService() {
		return userServiceImpl;
	}
	

	/**
	 * 修改页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/edit/ui")
	public String editUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "user/user-edit";
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
			HttpServletResponse response, Model model, User user) {
		int result = userServiceImpl.update(user);
		return result;
	}

	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "page")
	@ResponseBody
	public Page<User> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() User user,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		//查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		// 构造查询page参数
		Page<User> page = new Page<User>(currentPage, count);
		page.setOrderColumn(orderColumn);
		page.setOrderType(orderType);
		page.setCondition(map);
		//查询
		page = userServiceImpl.findPage(page);
		
		return page;
	}

	
}
