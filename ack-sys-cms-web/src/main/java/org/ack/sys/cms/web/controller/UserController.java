package org.ack.sys.cms.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.common.Validation;
import org.ack.sys.base.core.auth.annotation.AckPermission;
import org.ack.sys.base.persist.page.Page;
import org.ack.sys.base.persist.page.PageRequest;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userServiceImpl;

	@AckPermission("sys:user:add")
	@PostMapping("/add")
	@ResponseBody
	public ResponseResult insert(@RequestBody @Validated User user, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult responseResult = Validation.getValidationResult(result);
		if (null != responseResult) {
			return responseResult;
		} else {
			int r = userServiceImpl.insert(user);
			int code = 500;
			String msg = "";
			Object data = null;
			if (r == 1) {
				code = 200;
				msg = "";
				data = r;
			} else if(r == -1) {
				code = 400;
				msg = "用户已存在";
				data = r;
			}else if(r == -2) {
				code = 400;
				msg = "用户邮箱已存在";
				data = r;
			}else if(r == -3) {
				code = 400;
				msg = "用户qq已存在";
				data = r;
			}else if(r == -4) {
				code = 400;
				msg = "用户手机已存在";
				data = r;
			}
			return new ResponseResult(code, msg, data);
		}

	}

	@AckPermission("sys:user:edit")
	@PatchMapping("/edit")
	@ResponseBody
	public ResponseResult edit(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		int r = userServiceImpl.update(user);
		return new ResponseResult(200, r);
	}
	
	@AckPermission("sys:user:delete")
	@DeleteMapping("/delete")
	@ResponseBody
	public ResponseResult delete(@RequestBody List<User> list, HttpServletRequest request,
			HttpServletResponse response) {
		int code = 200;
		String msg = "";
		int rt = userServiceImpl.batchDelete(list);
		if (rt != list.size()) {
			code = 500;
			msg = "删除用户失败";
		}
		return new ResponseResult(code, msg, rt);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public ResponseResult deleteById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
		int rt = userServiceImpl.deleteById(id);
		return new ResponseResult(200, rt);
	}

	@AckPermission("sys:user:view")
	@PostMapping("/findPage")
	@ResponseBody
	public ResponseResult findPage(@RequestBody PageRequest pageRequest) {
		pageRequest.setOrderColumn("createTime");
		Page<User> page = userServiceImpl.findPage(pageRequest);
		ResponseResult result = new ResponseResult(200, page);
		return result;
	}

	@AckPermission("sys:user:view")
	@GetMapping("/findPermissions")
	@ResponseBody
	public ResponseResult findUserPermissions(@RequestParam String username, HttpServletRequest request) {
		User user = getCurrentUser(request);
		if(!user.getUsername().equals(username)) {
			logger.warn("当前登录用户:{},传参用户名:{}",user.getUsername(), username);
			return new ResponseResult(200, null);
		}
		List<String> list = userServiceImpl.findUserPermissions(user.getId());
		ResponseResult result = new ResponseResult(200, list);
		return result;
	}

	@GetMapping("/name/{username}")
	@ResponseBody
	public User findUserByUserName(@PathVariable String username) {
		logger.debug("username = {}", username);
		return userServiceImpl.findUserByUserName(username);
	}

}
