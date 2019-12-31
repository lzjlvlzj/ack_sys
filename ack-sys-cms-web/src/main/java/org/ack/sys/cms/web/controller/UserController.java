package org.ack.sys.cms.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.persist.page.Page;
import org.ack.sys.base.persist.page.PageRequest;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    
	@PostMapping("/add")
	@ResponseBody
	public ResponseResult insert(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response) {
		//获得当前登录的用户
		User currentUser = getCurrentUser(request);
		System.out.println(currentUser);
		int r = userServiceImpl.insert(user);
		return new ResponseResult(200, r);
	}
	
	
	@RequestMapping("/findPage")
	@ResponseBody
	public ResponseResult findPage(@RequestBody PageRequest pageRequest) {
		Page<User> page = new Page<User>();
		page = userServiceImpl.findPage(page);
		ResponseResult result = new ResponseResult(200, page);
		return result;
	}
	
	@GetMapping("/findPermissions")
	@ResponseBody
	public ResponseResult findUserPermissions(@RequestParam String username) {
		List<String> list = userServiceImpl.findUserPermissions(username);
		ResponseResult result = new ResponseResult(200, list);
		return result;
	}
	
	@GetMapping("/name/{username}")
	@ResponseBody
	public User findUserByUserName(@PathVariable  String username) {
		logger.debug("username = {}", username);
		return userServiceImpl.findUserByUserName(username);
	}
}
