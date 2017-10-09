package org.ack.admin.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.common.ResultMessage;
import org.ack.common.datatable.DataTableTemplate;
import org.ack.persist.page.Page;
import org.ack.pojo.Department;
import org.ack.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 部门管理
 * 
 * @author ack
 *
 */
@RequestMapping("/dept")
@Controller
public class DepartmentController extends
		AckPageController<Department, Integer> {

	private static final Logger logger = LoggerFactory
			.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService departmentServiceImpl;

	@Override
	public AckMapperService<Department, Integer> getService() {
		return departmentServiceImpl;
	}

	@RequestMapping(value = "/all")
	@AckPermission(value = "dept:list")
	@ResponseBody
	public List<Department> findAll() {
		if (logger.isDebugEnabled()) {
			logger.debug("查询全部部门信息");
		}
		return departmentServiceImpl.findAll();
	}

	/**
	 * 列表页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/list/ui")
	@AckPermission(value = "dept:list")
	public String listUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "dept/deptList";
	}

	@RequestMapping(value = "/page")
	@AckPermission(value = "dept:list")
	@ResponseBody
	public Page<Department> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() Department t,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		Map<String, Object> map = new HashMap<String, Object>();
		return super.findPage(request, response, model, map, t, currentPage,
				count, orderColumn, orderType);
	}

	@RequestMapping(value = "/table")
	@AckPermission(value = "dept:list")
	@ResponseBody
	@Override
	public DataTableTemplate<Department> dataTable(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			Map<String, Object> extraCondition,
			@ModelAttribute() Department t,
			@RequestParam(required = false, defaultValue = "0") int start,/* 第一条记录的起始位置 */
			@RequestParam(required = false, defaultValue = "10") int length,/* 每页显示多少记录 */
			@RequestParam(required = false, defaultValue = "1") int draw,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		return super.dataTable(request, response, model, extraCondition, t,
				start, length, draw, orderColumn, orderType);
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
	@AckPermission(value = "dept:add")
	public String addUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("新建部门");
		}
		model.addAttribute("optionName", "新建部门");
		model.addAttribute("optionFlag", "0");// 表示添加操作
		return "dept/deptEdit";
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
	@AckPermission(value = "dept:update")
	public String editUI(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Integer id) {
		model.addAttribute("optionName", "修改部门");
		model.addAttribute("optionFlag", "1");
		Department dept = departmentServiceImpl.findById(id);
		model.addAttribute("department", dept);
		return "dept/deptEdit";
	}

	/**
	 * 根据id查询
	 * 
	 * */
	@RequestMapping(value = "/id/{id}")
	@AckPermission(value = "dept:find or dept:update")
	@ResponseBody
	@Override
	public Department findById(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Integer id) {
		return super.findById(request, response, model, id);
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param t
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/add")
	@AckPermission(value = "dept:add")
	@ResponseBody
	public ResultMessage insert(HttpServletRequest request,
			HttpServletResponse response, Model model, @Valid Department t,
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
	 * 根据id删除
	 * */
	@RequestMapping(value = "/del/{id}")
	@AckPermission(value = "dept:delete")
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
	@AckPermission(value = "dept:update")
	@ResponseBody
	public ResultMessage edit(HttpServletRequest request,
			HttpServletResponse response, Model model, @Valid Department t,
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
