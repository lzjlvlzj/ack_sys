package org.ack.sys.cms.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.sys.base.common.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataServerController {
	
	private static final Logger logger = LoggerFactory.getLogger(DataServerController.class);
	
	@GetMapping("/tile/test")
	public void  dataServer1( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/vec_w/wmts").forward(request, response);
	}
	@GetMapping("/DataServer")
	@ResponseBody
	public ResponseResult dataServer( HttpServletRequest request, HttpServletResponse response) {
		
		return new ResponseResult(200, "test");
	}
	
	@GetMapping("/vec_w/wmts")
	@ResponseBody
	public ResponseResult wmts( HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		String str = request.getQueryString();
		logger.debug("*******************");
		logger.debug("uri = {}", uri);
		logger.debug(str);
		logger.debug("*******************");
		return new ResponseResult(200, "wmts服务");
	}

}
