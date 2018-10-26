package org.ack.admin.web.controller;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.common.Content;
import org.ack.common.datatable.DataTableTemplate;
import org.ack.common.message.MessageEntry;
import org.ack.common.tree.Tree;
import org.ack.persist.page.Page;
import org.ack.pojo.Role;
import org.ack.pojo.User;
import org.ack.service.UserService;
import org.ack.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController extends AckPageController<User, Long> {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	private UserService userServiceImpl;

	@Override
	public AckMapperService<User, Long> getService() {
		return userServiceImpl;
	}

	@RequestMapping("/current")
	@ResponseBody
	public User getCurrentUser(HttpServletRequest request,
							   HttpServletResponse response, Model model) {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute(Content.USER);
		return user;
	}

	/**
	 * dashboard页面
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/dashboard")
	@AckPermission(value = "user:dashboard")
	public String dashboard(HttpServletRequest request,
							HttpServletResponse response, Model model) {
		return "user/dashboard";
	}

	/**
	 * 列表
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list/ui")
	@AckPermission(value = "user:list")
	public String listUI(HttpServletRequest request,
						 HttpServletResponse response, Model model) {
		return "user/userList";
	}

	/**
	 * 单用户
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByLoginName")
	@AckPermission(value = "user:add or user:update")
	@ResponseBody
	public Integer findByLoginName(HttpServletRequest request,
						 HttpServletResponse response, User user) {
		User u = userServiceImpl.findUserByLoginName(user.getLoginName());
		if(null == u){
			return 0;

		} else {
			return 1;
		}
	}

	/**
	 * 修改页面
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add/ui")
	@AckPermission(value = "user:add")
	public String addUI(HttpServletRequest request,
						 HttpServletResponse response, Model model) {
		return "user/userEdit";
	}

	/**
	 * 修改页面
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/edit/ui/{id}")
	@AckPermission(value = "user:update")
	public String editUI(HttpServletRequest request,
						 HttpServletResponse response, @PathVariable Integer id) {
		return "user/userEdit";
	}

	/**
	 * 查询当前用户菜单树
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/menus")
	@ResponseBody
	public Tree getMenus(HttpServletRequest request,
						 HttpServletResponse response, Model model) {
		User user = (User) request.getSession().getAttribute(Content.USER);
		Tree tree = userServiceImpl.findMenuTreeByUser(user);
		return tree;
	}

	/**
	 * 用户授予角色页面
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/role2user/ui")
	@AckPermission(value = "user:role")
	public String role2UserUI(HttpServletRequest request, User user,
							  HttpServletResponse response, Model model) {
		return "user/userRole";
	}

	/**
	 * 角色列表
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/role/list")
	@AckPermission(value = "user:role")
	@ResponseBody
	public List<Role> findRoleList(HttpServletRequest request,
								   Integer[] rid, HttpServletResponse response, Model model) {
		User user = getCurrentUser(request);
		List<Role> roleList = userServiceImpl.findRoleList(user);
		return roleList;
	}
	/**
	 * 用户授予角色页面
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/role2user")
	@AckPermission(value = "user:role")
	@ResponseBody
	public String role2User(HttpServletRequest request, User user,
							Integer[] rid, HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("给用户 : [" + user + "] 设置角色");
		}
		String ids = StringUtils.array2String(rid);
		user.setRoleIds(ids);
		int r = userServiceImpl.update(user);
		return r + "";
	}

	/**
	 * 用户授予角色页面
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/roles/list")
	@ResponseBody
	public Set<Role> findRolesByUser(HttpServletRequest request, User user,
									 HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("user id is {}", user.getId());
		}
		Set<Role> roles = userServiceImpl.findRolesByUser(user);
		return roles;
	}

	@RequestMapping(value = "/page")
	@AckPermission(value = "user:list")
	@ResponseBody
	@Override
	public Page<User> findPage(HttpServletRequest request,
							   HttpServletResponse response, Model model, Map<String, Object> map,
							   User t, int currentPage,
							   int count, String orderColumn, String orderType) {
		// 查询条件
		map = new HashMap<String, Object>();
		// 构造查询page参数
		Page<User> page = new Page<User>(currentPage, count);
		page.setOrderColumn(orderColumn);
		page.setOrderType(orderType);
		page.setCondition(map);
		// 查询
		page = getService().findPage(page);
		return page;
	}

	@RequestMapping(value = "/table")
	@AckPermission(value = "user:list")
	@ResponseBody
	public DataTableTemplate<User> dataTable(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			Map<String, Object> extraCondition,
			@ModelAttribute() User t,
			@RequestParam(required = false, defaultValue = "1") int draw,
			@RequestParam(required = false, defaultValue = "1") int start,
			@RequestParam(required = false, defaultValue = "10") int length,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		Map<String, Object> map = new HashMap<String, Object>();
		return super.dataTable(request, response, model, map, t, start, length,
				draw, orderColumn, orderType);
	}

	@RequestMapping(value = "/id/{id}")
	@AckPermission(value = "user:find or user:update")
	@ResponseBody
	@Override
	public User findById(HttpServletRequest request,
						 HttpServletResponse response, Model model, @PathVariable Long id) {
		return super.findById(request, response, model, id);
	}

	@RequestMapping(value = "/add")
	@AckPermission(value = "user:add")
	@ResponseBody
	public MessageEntry MessageEntry(HttpServletRequest request,
						  HttpServletResponse response, Model model,
						  @Valid User t, BindingResult result) {
		boolean b = result.hasErrors();
		if (b) {
			FieldError fe = result.getFieldError();
			String msg = fe.getDefaultMessage();
			if (logger.isDebugEnabled()) {
				logger.debug("表单验证错误  : {}", msg);
			}
			return new MessageEntry(0, msg);
		}

		int r = userServiceImpl.insert(t);
		return new MessageEntry(r, "");
	}

	@RequestMapping(value = "/del/{id}")
	@AckPermission(value = "user:delete")
	@ResponseBody
	@Override
	public Integer deleteById(HttpServletRequest request,
							  HttpServletResponse response, Model model, @PathVariable Long id) {
		return super.deleteById(request, response, model, id);
	}

	@RequestMapping(value = "/edit")
	@AckPermission(value = "user:update")
	@ResponseBody
	@Override
	public MessageEntry edit(HttpServletRequest request,
						HttpServletResponse response, Model model,
						@Valid User t, BindingResult result) {
		boolean b = result.hasErrors();
		if (b) {
			FieldError fe = result.getFieldError();
			String msg = fe.getDefaultMessage();
			if (logger.isDebugEnabled()) {
				logger.debug("表单验证错误  : {}", msg);
			}
			return new MessageEntry(0, msg);
		}
		int r = userServiceImpl.update(t);
		return new MessageEntry(r, "");
	}

	@RequestMapping(value = "/password/reset")
	@AckPermission(value = "user:pass")
	@ResponseBody
	public Integer resetPassword(User user){
		user.setPassword("123456");
		return userServiceImpl.updateUserPassword(user, 0);
	}

	/**
	 * 密码修改页面
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/password/update/ui")
	public String updatePasswordUI(HttpServletRequest request,
							HttpServletResponse response, Model model) {
		return "user/userPass";
	}

	@RequestMapping(value = "/password/update")
	@ResponseBody
	public Integer updatePassword(HttpServletRequest request, User user){
		User u = getCurrentUser(request);
		user.setId(u.getId());
		return userServiceImpl.updateUserPassword(user, 1);
	}

}
