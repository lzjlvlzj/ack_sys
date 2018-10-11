package org.ack.admin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/error")
public class ErrorController {
	private static final Logger logger = LoggerFactory
			.getLogger(ErrorController.class);

	@RequestMapping("/403")
	public String Error403(HttpServletRequest request,
						   HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			String url = request.getContextPath();
			logger.debug("403错误路径 : " + url);
		}
		return "error/403";
	}

	@RequestMapping("/404")
	public String Error404(HttpServletRequest request,
						   HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			String url = request.getContextPath();
			logger.debug("404错误路径 : " + url);
		}
		return "error/404";
	}

	@RequestMapping("/500")
	public String Error500(HttpServletRequest request,
						   HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			String url = request.getContextPath();
			logger.debug("500错误路径 : " + url);
		}
		return "error/500";
	}

	
}
