package org.ack.sys.cms.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.config.ErrorMessageConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 错误页面处理
 * 
 * @author ack
 *
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

	private static final Logger logger = LoggerFactory
			.getLogger(ErrorController.class);

	@RequestMapping("/403")
	@ResponseBody
	public ResponseResult Error403(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			String url = request.getContextPath();
			logger.debug("403错误路径 : " + url);
		}
		int code = ErrorMessageConfig.SYS_UNAUTHORIZED.getCode();
		String msg = ErrorMessageConfig.SYS_UNAUTHORIZED.getMsg();
		ResponseResult result = new ResponseResult(code, msg, null);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/404")
	public ResponseResult Error404(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			String url = request.getContextPath();
			logger.debug("404错误路径 : " + url);
		}
		int code = ErrorMessageConfig.SYS_NO_SERVICE.getCode();
		String msg = ErrorMessageConfig.SYS_NO_SERVICE.getMsg();
		ResponseResult result = new ResponseResult(code, msg, null);
		return result;
	}

	@RequestMapping("/500")
	@ResponseBody
	public ResponseResult Error500(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			String url = request.getContextPath();
			logger.debug("500错误路径 : " + url);
		}
		int code = ErrorMessageConfig.SYS_UNSPECIFIED.getCode();
		String msg = ErrorMessageConfig.SYS_UNSPECIFIED.getMsg();
		ResponseResult result = new ResponseResult(code, msg, null);
		return result;
	}

}
