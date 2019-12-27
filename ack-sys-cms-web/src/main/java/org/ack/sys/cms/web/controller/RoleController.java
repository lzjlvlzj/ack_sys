package org.ack.sys.cms.web.controller;

import java.util.List;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.cms.pojo.Role;
import org.ack.sys.cms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleServiceImpl;

	@GetMapping("/list")
	public ResponseResult findAll() {
		List<Role> list = roleServiceImpl.findAll();
		ResponseResult result = new ResponseResult(200, list);
		return result;
	}
}
