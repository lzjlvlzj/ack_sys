package org.ack.sys.cms.web.controller;

import java.util.List;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.cms.pojo.Menu;
import org.ack.sys.cms.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/menu")
public class MenuController {
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private MenuService menuServiceImpl;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Menu findById(@PathVariable Integer id) {
		logger.debug("menuId = {}", id);
		return menuServiceImpl.findById(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/findNavTree", method = RequestMethod.GET)
	public ResponseResult findNvaTree(@RequestParam String username) {
		logger.debug("username = {}", username);
		List<Menu> menus = menuServiceImpl.findMenuByUser(username);
		ResponseResult result = new ResponseResult(200, menus);
		return result;
	}
}
