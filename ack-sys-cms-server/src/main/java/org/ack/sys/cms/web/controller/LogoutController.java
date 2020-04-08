package org.ack.sys.cms.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.ack.sys.base.common.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogoutController {
	private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);

	@GetMapping("/logout")
	@ResponseBody
	public ResponseResult logout(HttpServletRequest request) {
		logger.debug("登出操作");
		request.getSession().invalidate();
		return new ResponseResult(200, "登出成功");
	}
}
