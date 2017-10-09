package org.ack.admin.web.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.common.ResultMessage;
import org.ack.pojo.Menu;
import org.ack.pojo.Role;
import org.ack.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 角色控制器
 * 
 * @author ack
 *
 */
@RequestMapping("/role")
@Controller
public class RoleController extends AckDeaultController<Role, Integer> {

	@Autowired
	private RoleService roleServiceImpl;

	@Override
	public AckMapperService<Role, Integer> getService() {
		return roleServiceImpl;
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
	public String listUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "role/roleList";
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
	public String addUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "role/roleEdit";
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
	public String editUI(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Integer id) {
		return "role/roleEdit";
	}

	@RequestMapping(value = "/menus")
	@AckPermission(value = "role:menu")
	@ResponseBody
	public Set<Menu> findMenusByRoleId(HttpServletRequest request,
			HttpServletResponse response, Model model, Role role) {
		return roleServiceImpl.findMenusByRole(role);
	}

	/**
	 * 角色菜单
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/menu2role/ui")
	@AckPermission(value = "role:menu")
	public String menu2RoleUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "role/roleMenu";
	}

	/**
	 * 角色菜单
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/menu2role")
	@AckPermission(value = "role:menu")
	@ResponseBody
	public Integer menu2Role(HttpServletRequest request,
			HttpServletResponse response, Model model, Role role) {
		return roleServiceImpl.update(role);
	}

	/**
	 * 角色列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	@AckPermission(value = "role:list")
	@ResponseBody
	@Override
	public List<Role> list(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return super.list(request, response, model);
	}

	/**
	 * 根据id进行查询
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/id/{id}")
	@AckPermission(value = "role:find or role:update")
	@ResponseBody
	@Override
	public Role findById(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Integer id) {
		return super.findById(request, response, model, id);
	}

	/**
	 * 新建角色
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param t
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add")
	@AckPermission(value = "role:add")
	@ResponseBody
	public ResultMessage insert(HttpServletRequest request,
			HttpServletResponse response, Model model, @Valid Role t,
			BindingResult result) {
		boolean b = result.hasErrors();
		if (b) {
			FieldError fe = result.getFieldError();
			String msg = fe.getDefaultMessage();
			return new ResultMessage("0", msg);
		}
		Integer r = super.insert(request, response, model, t);
		return new ResultMessage(r.toString(), "");
	}

	/**
	 * 根据id删除角色
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{id}")
	@AckPermission(value = "role:delete")
	@ResponseBody
	@Override
	public Integer deleteById(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Integer id) {
		return super.deleteById(request, response, model, id);
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param t
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@AckPermission(value = "role:update")
	@ResponseBody
	public ResultMessage edit(HttpServletRequest request,
			HttpServletResponse response, Model model, @Valid Role t,
			BindingResult result) {
		boolean b = result.hasErrors();
		if (b) {
			FieldError fe = result.getFieldError();
			String msg = fe.getDefaultMessage();
			return new ResultMessage("0", msg);
		}
		Integer r = super.edit(request, response, model, t);
		return new ResultMessage(r.toString(), "");
	}

}
