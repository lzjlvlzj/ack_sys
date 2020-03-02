package org.ack.sys.cms.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.common.Validation;
import org.ack.sys.base.core.auth.annotation.AckPermission;
import org.ack.sys.base.persist.page.Page;
import org.ack.sys.base.persist.page.PageRequest;
import org.ack.sys.cms.exception.AuthorityException;
import org.ack.sys.cms.pojo.Role;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.service.UserService;
import org.ack.sys.cms.web.util.WebUtil;
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

	@AckPermission("sys:user:able")
	@PatchMapping("/able")
	@ResponseBody
	public ResponseResult enableOrdisableUser(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response) {
		int status = userServiceImpl.getOperationStatus(user, request);
		User currentUser = WebUtil.getCurrentUser(request);
		if (status >= 0 ) {
			logger.debug("当前用户{}没有权限修改目标用户{}的数据",currentUser.getUsername(), user.getUsername());
			throw new AuthorityException();
		}
		int r = userServiceImpl.ableUser(user, request);
		return new ResponseResult(200, r);
	}
	
	@AckPermission("sys:user:pwd")
	@PatchMapping("/restPassword")
	@ResponseBody
	public ResponseResult resetPassword(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response) {
		User currentUser = WebUtil.getCurrentUser(request);
		String username = currentUser.getUsername();
		if(!"admin".equals(username)) {
			int status = userServiceImpl.getOperationStatus(user, request);
			if (status >= 0) {
				logger.debug("当前用户{}没有权限修改目标用户{}的数据",currentUser.getUsername(), user.getUsername());
				throw new AuthorityException();
			}
		}
		int r = userServiceImpl.resetPassword(user, request);
		return new ResponseResult(200, r);
	}


	@AckPermission("sys:user:auth")
	@PatchMapping("/grant")
	@ResponseBody
	public ResponseResult grantAuth(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		User currentUser = WebUtil.getCurrentUser(request);
		String username = currentUser.getUsername();
		if(!"admin".equals(username)) {
			int status = userServiceImpl.getOperationStatus(user, request);
			if (status >= 0) {
				logger.debug("当前用户{}没有权限修改目标用户{}的数据",currentUser.getUsername(), user.getUsername());
				throw new AuthorityException();
			}
		}
		int r = userServiceImpl.grauntAuth(user, request);
		return new ResponseResult(200, r);
	}

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
			} else if (r == -1) {
				code = 400;
				msg = "用户已存在";
				data = r;
			} else if (r == -2) {
				code = 400;
				msg = "用户邮箱已存在";
				data = r;
			} else if (r == -3) {
				code = 400;
				msg = "用户qq已存在";
				data = r;
			} else if (r == -4) {
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
		User currentUser = WebUtil.getCurrentUser(request);
		String username = currentUser.getUsername();
		if(!"admin".equals(username)) {
			int status = userServiceImpl.getOperationStatus(user, request);
			if (status >= 0) {
				logger.debug("当前用户{}没有权限修改目标用户{}的数据",currentUser.getUsername(), user.getUsername());
				throw new AuthorityException();
			}
		}
		int r = userServiceImpl.update(user, request);
		return new ResponseResult(200, r);
	}

	@AckPermission("sys:user:delete")
	@DeleteMapping("/delete")
	@ResponseBody
	public ResponseResult delete(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response) {
		int status = userServiceImpl.getOperationStatus(user, request);
		User currentUser = WebUtil.getCurrentUser(request);
		if (status >= 0) {
			logger.debug("当前用户{}没有权限修改目标用户{}的数据",currentUser.getUsername(), user.getId());
			throw new AuthorityException();
		}
		int code = 200;
		String msg = "";
		int rt = userServiceImpl.delete(user, request);
		if (rt == -1) {
			throw new AuthorityException();
		}
		return new ResponseResult(code, msg, rt);
	}
	
	@AckPermission("sys:user:delete")
	@DeleteMapping("/batchDelete")
	@ResponseBody
	public ResponseResult batchDelete(@RequestBody List<User> list, HttpServletRequest request,
			HttpServletResponse response) {
		int code = 200;
		String msg = "";
		int rt = userServiceImpl.batchDelete(list, request);
		if (rt == -1) {
			throw new AuthorityException();
		}
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
	public ResponseResult findPage(@RequestBody PageRequest pageRequest, HttpServletRequest request) {
		pageRequest.setOrderColumn("createTime");
		User user = getCurrentUser(request);
		Page<User> page = userServiceImpl.findPage(pageRequest, user);
		ResponseResult result = new ResponseResult(200, page);
		return result;
	}

	/**
	 * 公共读取,不需要设置具体的权限,否则对后面开发新功能有影响.
	 */
	@GetMapping("/findPermissions")
	@ResponseBody
	public ResponseResult findUserPermissions(@RequestParam String username, HttpServletRequest request) {
		User user = getCurrentUser(request);
		if (!user.getUsername().equals(username)) {
			logger.warn("当前登录用户:{},传参用户名:{}", user.getUsername(), username);
			return new ResponseResult(200, null);
		}
		List<String> list = userServiceImpl.findUserPermissions(user.getId());
		ResponseResult result = new ResponseResult(200, list);
		return result;
	}

	@GetMapping("/findUserRoles")
	@ResponseBody
	public ResponseResult findUserRoles(@RequestParam Long id) {
		logger.debug("用户id = {}", id);
		List<Role> list = userServiceImpl.findUserRoles(id);
		return new ResponseResult(200, list);
	}

	@GetMapping("/name/{username}")
	@ResponseBody
	public User findUserByUserName(@PathVariable String username) {
		logger.debug("username = {}", username);
		return userServiceImpl.findUserByUserName(username);
	}

}
