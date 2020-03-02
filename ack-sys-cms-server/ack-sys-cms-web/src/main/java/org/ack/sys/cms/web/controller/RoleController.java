package org.ack.sys.cms.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.common.Validation;
import org.ack.sys.base.core.auth.annotation.AckPermission;
import org.ack.sys.base.persist.page.Page;
import org.ack.sys.base.persist.page.PageRequest;
import org.ack.sys.cms.pojo.Menu;
import org.ack.sys.cms.pojo.Role;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.service.RoleService;
import org.ack.sys.cms.web.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleServiceImpl;
	
	@AckPermission("sys:role:add")
	@PostMapping("/add")
	@ResponseBody
	public ResponseResult insert(@RequestBody @Validated Role role, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult responseResult = Validation.getValidationResult(result);
		if (null != responseResult) {
			return responseResult;
		} else {
			int r = roleServiceImpl.insert(role);
			int code = 500;
			String msg = "";
			Object data = null;
			if (r == 1) {
				code = 200;
				msg = "";
				data = r;
			} else if(r == -1) {
				code = 400;
				msg = "角色已存在";
				data = r;
			}
			return new ResponseResult(code, msg, data);
		}

	}
	
	@AckPermission("sys:role:view")
	@GetMapping("/findMenuByRoleId")
	@ResponseBody
	public ResponseResult findMenuByRoleId(@RequestParam Long roleId) {
		List<Menu> list = roleServiceImpl.findMenuByRoleId(roleId);
		ResponseResult result = new ResponseResult(200, list);
		return result;
	}
	
	@AckPermission("sys:role:edit")
	@PostMapping("/saveRoleMenus")
	@ResponseBody
	public ResponseResult saveRoleMenus(@RequestBody Role role, HttpServletRequest request) {
		Integer r = roleServiceImpl.saveRoleMenus(role);
		ResponseResult result = new ResponseResult(200, r);
		return result;
	}
	
	@AckPermission("sys:role:edit")
	@PatchMapping("/edit")
	@ResponseBody
	public ResponseResult edit(@RequestBody Role role, HttpServletRequest request, HttpServletResponse response) {
		int r = roleServiceImpl.update(role);
		int code = 500;
		String msg = "";
		Object data = null;
		if (r == 1) {
			code = 200;
			msg = "";
			data = r;
		} else if(r == -1) {
			code = 400;
			msg = "角色已存在";
			data = r;
		}
		return new ResponseResult(code, msg, data);
	}
	
	@AckPermission("sys:role:delete")
	@DeleteMapping("/delete")
	@ResponseBody
	public ResponseResult delete(@RequestBody List<Role> list, HttpServletRequest request,
			HttpServletResponse response) {
		int code = 200;
		String msg = "";
		int rt = roleServiceImpl.batchDelete(list);
		if (rt != list.size()) {
			code = 500;
			msg = "删除失败";
		}
		return new ResponseResult(code, msg, rt);
	}
	
	@AckPermission("sys:role:view or sys:user:view")
	@GetMapping("/list")
	@ResponseBody
	public ResponseResult findAll(HttpServletRequest request) {
		User user = WebUtil.getCurrentUser(request);
		//List<Role> list = roleServiceImpl.findAll();
		List<Role> list = roleServiceImpl.findRoleListByUser(user);
		ResponseResult result = new ResponseResult(200, list);
		return result;
	}
	
	@AckPermission("sys:role:view")
	@PostMapping("/findPage")
	@ResponseBody
	public ResponseResult findPage(@RequestBody PageRequest pageRequest) {
		pageRequest.setOrderColumn("createTime");
		Page<Role> page = roleServiceImpl.findPage(pageRequest);
		ResponseResult result = new ResponseResult(200, page);
		return result;
	}
}
