package org.ack.admin.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.admin.web.template.ZTreeNode;
import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.common.datatable.DataTableTemplate;
import org.ack.persist.page.Page;
import org.ack.pojo.Menu;
import org.ack.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 菜单控制器
 * 
 * @author ack
 *
 */
@RequestMapping("/menu")
@Controller
public class MenuController extends AckPageController<Menu, Integer> {

	private static final Logger logger = LoggerFactory
			.getLogger(MenuController.class);

	@Autowired
	private MenuService menuServiceImpl;

	@Override
	public AckMapperService<Menu, Integer> getService() {
		return menuServiceImpl;
	}

	/**
	 * 列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/list/ui")
	@AckPermission(value="menu:list")
	public String listUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "menu/menuList";
	}
	
	@RequestMapping(value = "/page")
	@AckPermission(value="menu:list")
	@ResponseBody
	@Override
	public Page<Menu> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() Menu t,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		return super.findPage(request, response, model, t, currentPage, count,
				orderColumn, orderType);
	}
	
	@RequestMapping(value = "/table")
	@AckPermission(value="menu:list")
	@ResponseBody
	@Override
	public DataTableTemplate<Menu> dataTable(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() Menu t,
			@RequestParam(required = false, defaultValue = "0") int start,/*第一条记录的起始位置*/
			@RequestParam(required = false, defaultValue = "10") int length,/*每页显示多少记录*/
			@RequestParam(required = false, defaultValue = "1") int draw,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		return super.dataTable(request, response, model, t, start, length, draw,
				orderColumn, orderType);
	}
	

	/**
	 * 添加页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/add/ui")
	@AckPermission(value="menu:add")
	public String addUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("新建导航菜单");
		}
		return "menu/menuEdit";
	}

	/**
	 * 修改页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/edit/ui/{id}")
	@AckPermission(value="menu:update")
	public String editUI(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Integer id) {
		return "menu/menuEdit";
	}
	
	/**
	 * 修改页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/tree")
	@ResponseBody
	public List<ZTreeNode> findAll(HttpServletRequest request,
			HttpServletResponse response, Model model, ZTreeNode zTreeNode) {
		Integer id = zTreeNode.getId();
		if(null == id){
			id = 0;
		}
		List<Menu> menus = menuServiceImpl.findAll();
		Set<Menu> ms = new HashSet<Menu>();
		ms.addAll(menus);
		List<ZTreeNode> set = changeMenu2ZTreeNode(ms);
		return set;
	}
	/**
	 * 修改页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/tree2")
	@ResponseBody
	public List<ZTreeNode> findMenuByPId(HttpServletRequest request,
			HttpServletResponse response, Model model, ZTreeNode zTreeNode) {
		Integer id = zTreeNode.getId();
		if(null == id){
			id = 0;
		}
		Set<Menu> menus = menuServiceImpl.findMenuByPId(id);
		List<ZTreeNode> set = changeMenu2ZTreeNode(menus);
		return set;
	}
	
	
	@RequestMapping(value = "/id/{id}")
	@AckPermission(value="menu:find")
	@ResponseBody
	@Override
	public Menu findById(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Integer id) {
		return super.findById(request, response, model, id);
	}

	@RequestMapping(value = "/add")
	@AckPermission(value="menu:add")
	@ResponseBody
	@Override
	public Integer insert(HttpServletRequest request,
			HttpServletResponse response, Model model, Menu t) {
		return super.insert(request, response, model, t);
	}

	@RequestMapping(value = "/del/{id}")
	@AckPermission(value="menu:delete")
	@ResponseBody
	@Override
	public Integer deleteById(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Integer id) {
		return super.deleteById(request, response, model, id);
	}
	@RequestMapping(value = "/edit")
	@AckPermission(value="menu:update")
	@ResponseBody
	@Override
	public Integer edit(HttpServletRequest request,
			HttpServletResponse response, Model model, Menu t) {
		return super.edit(request, response, model, t);
	}

	private List<ZTreeNode> changeMenu2ZTreeNode(Set<Menu> menus) {
		List<ZTreeNode> list = new ArrayList<ZTreeNode>();
		for(Menu menu : menus){
			ZTreeNode node = new ZTreeNode();
			node.setId(menu.getId());
			node.setPId(menu.getParentId());
			node.setName(menu.getMenuName());
			node.setValue(menu.getPermission());
			if(menu.getMenuType() == 0) {
				node.setIsParent(true);
			} else {
				node.setIsParent(false);
			}
			
			list.add(node);
		}
		return list;
	}

}
