package org.ack.sys.cms.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.core.auth.annotation.AckPermission;
import org.ack.sys.cms.config.web.LonginResponseConfig;
import org.ack.sys.cms.exception.UserAndPasswordException;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.service.PersonalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/personal")
public class PersonalController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PersonalController.class);
	
	@Autowired
	private PersonalService personalServiceImpl;
	
	@AckPermission("personal:center:view")
	@GetMapping("/uploadAvatar")
	@ResponseBody
	public ResponseResult upload(@RequestParam(value = "avatarFile") MultipartFile file, Model model,
			HttpServletRequest request) {
		return null;
	}
	
	@AckPermission("personal:center:view")
	@GetMapping("/findUserByName")
	@ResponseBody
	public ResponseResult findByUserName(@RequestParam String username) {
		logger.debug("username = {}", username);
		User user = personalServiceImpl.findUserByUserName(username);
		if(null == user) {
			logger.debug("用户没有查询到");
		}
		return new ResponseResult(200, user);
	}
	
	@AckPermission("personal:center:view")
	@PatchMapping("/edit")
	@ResponseBody
	public ResponseResult edit(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		int r = personalServiceImpl.update(user);
		return new ResponseResult(200, r);
	}
	
	@AckPermission("personal:center:pwd")
	@PatchMapping("/changePwd")
	@ResponseBody
	public ResponseResult changePwd(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		int r = personalServiceImpl.changePwd(user);
		if (r == 2) {
			throw new UserAndPasswordException(LonginResponseConfig.LOGIN_PASSWORD_INVALIDATE);
		}
		return new ResponseResult(200, r);
	}

	
}
