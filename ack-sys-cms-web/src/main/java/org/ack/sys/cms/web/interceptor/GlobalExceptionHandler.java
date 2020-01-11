package org.ack.sys.cms.web.interceptor;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 全局异常捕获
	 * 
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ModelMap handleException(Exception e) {
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("msg", "后台异常捕获");
		modelMap.addAttribute("Exception", e);
		return modelMap;
	}

	
	/**
	 * 全局异常捕获
	 * 
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = BaseException.class)
	@ResponseBody
	public ResponseResult handleRuntimeException(BaseException e) {
		logger.debug("捕获到异常");
		String msg = e.getMessage();
		int code = e.getCode();
		ResponseResult ex = new ResponseResult(code, msg);
		logger.debug(ex.toString());
		return ex;
	}
}