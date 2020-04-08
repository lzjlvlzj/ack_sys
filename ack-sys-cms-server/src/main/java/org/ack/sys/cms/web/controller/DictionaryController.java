package org.ack.sys.cms.web.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.common.Validation;
import org.ack.sys.base.core.auth.annotation.AckPermission;
import org.ack.sys.base.persist.page.Page;
import org.ack.sys.base.persist.page.PageRequest;
import org.ack.sys.pojo.Dictionary;
import org.ack.sys.cms.service.DictionaryService;
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

/**
 * 数据字典接口
 * 
 * @author ack
 *
 */
@Controller
@RequestMapping("/dict")
public class DictionaryController extends BaseController {
	
	@Autowired
	private DictionaryService dictionaryServiceImpl;
	

	@AckPermission("sys:dict:view")
	@PostMapping("/findPage")
	@ResponseBody
	public ResponseResult findPage(@RequestBody PageRequest pageRequest) {
		pageRequest.setOrderColumn("createTime");
		Page<Dictionary> page = dictionaryServiceImpl.findPage(pageRequest);
		ResponseResult result = new ResponseResult(200, page);
		return result;
	}
	
	@AckPermission("sys:dict:add")
	@PostMapping("/add")
	@ResponseBody
	public ResponseResult insert(@RequestBody @Validated Dictionary dict, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult responseResult = Validation.getValidationResult(result);
		if (null != responseResult) {
			return responseResult;
		} else {
			int r = dictionaryServiceImpl.insert(dict);
			int code = 500;
			String msg = "";
			Object data = null;
			if (r == 1) {
				code = 200;
				msg = "";
				data = r;
			} else if(r == -1) {
				code = 400;
				msg = "字典已存在";
				data = r;
			}
			return new ResponseResult(code, msg, data);
		}

	}
	
	
	@AckPermission("sys:dict:edit")
	@PatchMapping("/edit")
	@ResponseBody
	public ResponseResult edit(@RequestBody Dictionary dict, HttpServletRequest request, HttpServletResponse response) {
		int r = dictionaryServiceImpl.update(dict);
		int code = 500;
		String msg = "";
		Object data = null;
		if (r == 1) {
			code = 200;
			msg = "";
			data = r;
		} else if(r == -1) {
			code = 400;
			msg = "字典不存在";
			data = r;
		}
		return new ResponseResult(code, msg, data);
	}
	
	@AckPermission("sys:dict:delete")
	@DeleteMapping("/delete")
	@ResponseBody
	public ResponseResult delete(@RequestBody Dictionary dict, HttpServletRequest request,
			HttpServletResponse response) {
		int code = 200;
		String msg = "";
		int rt = dictionaryServiceImpl.delete(dict);
		if (rt != 1) {
			code = 500;
			msg = "删除菜单失败";
		}
		return new ResponseResult(code, msg, rt);
	}
	
	
	
	
	
	
	
	
	
}
