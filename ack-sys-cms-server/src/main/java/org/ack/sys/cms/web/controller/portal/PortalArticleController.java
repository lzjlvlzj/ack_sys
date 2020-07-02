package org.ack.sys.cms.web.controller.portal;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.common.Validation;
import org.ack.sys.cms.service.portal.PortalArticleService;
import org.ack.sys.pojo.PortalArticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/portal/artice")
public class PortalArticleController {
	private static final Logger logger = LoggerFactory.getLogger(PortalArticleController.class);
	@Autowired
	private PortalArticleService portalArticleServiceImpl;
	
	
	public ResponseResult publish(Long id) {
		int r = portalArticleServiceImpl.publish(id);
		ResponseResult responseResult = new ResponseResult(200, r);
		return responseResult;
	}
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseResult insert(@RequestBody @Validated PortalArticle article,
			BindingResult result, HttpServletRequest request) {
		logger.debug("新建文章");
		ResponseResult responseResult = Validation.getValidationResult(result);
		if (null != responseResult) {
			return responseResult;
		} else {
			int r = portalArticleServiceImpl.insert(article);
			int code = 500;
			String msg = "";
			Object data = null;
			if (r == 1) {
				code = 200;
				msg = "";
				data = r;
			} else if(r == -1) {
				code = 400;
				msg = "部门已存在";
				data = r;
			}
			return new ResponseResult(code, msg, data);
		}
		
	}
	
	@PatchMapping("/edit")
	@ResponseBody
	public ResponseResult edit(@RequestBody PortalArticle menu, HttpServletRequest request, HttpServletResponse response) {
		int r = portalArticleServiceImpl.update(menu);
		return new ResponseResult(200, r); 
	}
	
	@DeleteMapping("/delete")
	@ResponseBody
	public ResponseResult delete(@RequestBody PortalArticle article, HttpServletRequest request,
			HttpServletResponse response) {
		int code = 200;
		String msg = "";
		int rt = portalArticleServiceImpl.delete(article);
		return new ResponseResult(code, msg, rt);
	}

}
