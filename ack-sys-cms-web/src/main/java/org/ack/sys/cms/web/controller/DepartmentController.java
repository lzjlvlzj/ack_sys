package org.ack.sys.cms.web.controller;

import java.util.List;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.cms.pojo.Department;
import org.ack.sys.cms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ack
 *
 */
@Controller
@RequestMapping("/dept")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentServiceImpl;
	
	@GetMapping("/tree")
	@ResponseBody
	public ResponseResult findTree() {
		List<Department> departmentList = departmentServiceImpl.findTree();
		return new ResponseResult(200, departmentList);
	}

}
