package org.ack.sys.cms.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.common.Validation;
import org.ack.sys.base.persist.page.Page;
import org.ack.sys.base.persist.page.PageRequest;
import org.ack.sys.cms.pojo.Menu;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private MenuService menuServiceImpl;
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseResult insert(@RequestBody @Validated Menu menu, 
			BindingResult result, HttpServletRequest request) {
		ResponseResult responseResult = Validation.getValidationResult(result);
		if (null != responseResult) {
			return responseResult;
		} else {
			int r = menuServiceImpl.insert(menu);
			int code = 500;
			String msg = "";
			Object data = null;
			if (r == 1) {
				code = 200;
				msg = "";
				data = r;
			} else if(r == -1) {
				code = 400;
				msg = "菜单已存在";
				data = r;
			}
			return new ResponseResult(code, msg, data);
		}
		
	}
	
	@PatchMapping("/edit")
	@ResponseBody
	public ResponseResult edit(@RequestBody Menu menu, HttpServletRequest request, HttpServletResponse response) {
		int r = menuServiceImpl.update(menu);
		return new ResponseResult(200, r);
	}
	
	@DeleteMapping("/delete")
	@ResponseBody
	public ResponseResult delete(@RequestBody List<Menu> list, HttpServletRequest request,
			HttpServletResponse response) {
		int code = 200;
		String msg = "";
		int rt = menuServiceImpl.batchDelete(list);
		if (rt != list.size()) {
			code = 500;
			msg = "删除菜单失败";
		}
		return new ResponseResult(code, msg, rt);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Menu findById(@PathVariable Long id) {
		logger.debug("menuId = {}", id);
		return menuServiceImpl.findById(id);
	}
	
	@PostMapping("/findPage")
	@ResponseBody
	public ResponseResult findPage(@RequestBody PageRequest pageRequest) {
		pageRequest.setOrderColumn("createTime");
		Page<Menu> page = menuServiceImpl.findPage(pageRequest);
		ResponseResult result = new ResponseResult(200, page);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/findNavTree", method = RequestMethod.GET)
	public ResponseResult findNvaTree(@RequestParam String username, HttpServletRequest request) {
		logger.debug("username = {}", username);
		User user = getCurrentUser(request);
		if(!user.getUsername().equals(username)) {
			logger.warn("当前登录用户:{},传参用户名:{}",user.getUsername(), username);
			return new ResponseResult(200, null);
		}
		List<Menu> menus = menuServiceImpl.findByUserId(user.getId());
		ResponseResult result = new ResponseResult(200, menus);
		return result;
	}
}
