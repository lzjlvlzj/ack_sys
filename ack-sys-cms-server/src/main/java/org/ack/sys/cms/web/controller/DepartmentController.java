package org.ack.sys.cms.web.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.common.Validation;
import org.ack.sys.base.core.auth.annotation.AckPermission;
import org.ack.sys.base.util.StringUtils;
import org.ack.sys.pojo.Department;
import org.ack.sys.cms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author ack
 *
 */
@Controller
@RequestMapping("/dept")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentServiceImpl;


	@PostMapping("/upload")
	@ResponseBody
	public ResponseResult upload(MultipartHttpServletRequest request){
		Map<String, MultipartFile> map = request.getFileMap();
		int size = map.size();
		if(size == 0){
			return new ResponseResult(400, "上传文件不能为空", null);
		}
        Set<String> set = map.keySet();
		MultipartFile file  = null;
		for(String key : set){
			file = map.get(key);
			break;
		}
		ResponseResult result = departmentServiceImpl.upload(file);

		return result;
	}
	
	@AckPermission("sys:dept:view OR sys:user:view OR personal:center:view")
	@GetMapping("/tree")
	@ResponseBody
	public ResponseResult findTree() {
		List<Department> departmentList = departmentServiceImpl.findTree();
		return new ResponseResult(200, departmentList);
	}
	
	@AckPermission("sys:dept:add")
	@PostMapping("/add")
	@ResponseBody
	public ResponseResult insert(@RequestBody @Validated Department menu, 
			BindingResult result, HttpServletRequest request) {
		ResponseResult responseResult = Validation.getValidationResult(result);
		if (null != responseResult) {
			return responseResult;
		} else {
			int r = departmentServiceImpl.insert(menu);
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
	
	@AckPermission("sys:dept:edit")
	@PatchMapping("/edit")
	@ResponseBody
	public ResponseResult edit(@RequestBody Department menu, HttpServletRequest request, HttpServletResponse response) {
		int r = departmentServiceImpl.update(menu);
		return new ResponseResult(200, r); 
	}
	
	@AckPermission("sys:dept:delete")
	@DeleteMapping("/delete")
	@ResponseBody
	public ResponseResult delete(@RequestBody List<Department> list, HttpServletRequest request,
			HttpServletResponse response) {
		int code = 200;
		String msg = "";
		int rt = departmentServiceImpl.batchDelete(list);
		if (rt != list.size()) {
			code = 500;
			msg = "删除菜单失败";
		}
		return new ResponseResult(code, msg, rt);
	}

}
