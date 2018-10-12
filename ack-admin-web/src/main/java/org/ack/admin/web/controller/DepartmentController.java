package org.ack.admin.web.controller;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.common.datatable.DataTableTemplate;
import org.ack.persist.page.Page;
import org.ack.pojo.Department;
import org.ack.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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

	/**
	 * 列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list/ui")
	@AckPermission(value="dept:list")
	public String listUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "dept/deptList";
	}
	
	@RequestMapping(value = "/page")
	@AckPermission(value="dept:list")
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
		return super.findPage(request, response, model, null, t, currentPage, count,
				orderColumn, orderType);
	}
	
	@RequestMapping(value = "/table")
	@AckPermission(value="dept:list")
	@ResponseBody
	public DataTableTemplate<Department> dataTable(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			Map<String, Object> extraCondition,
			@ModelAttribute() Department t,
			@RequestParam(required = false, defaultValue = "0") int start,/*第一条记录的起始位置*/
			@RequestParam(required = false, defaultValue = "10") int length,/*每页显示多少记录*/
			@RequestParam(required = false, defaultValue = "1") int draw,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		return super.dataTable(request, response, model, extraCondition, t, start, length, draw,
				orderColumn, orderType);
	}
	
	/**
	 * 添加页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add/ui")
	@AckPermission(value="dept:add")
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
	 * @return
	 */
	@RequestMapping(value = "/edit/ui/{id}")
	@AckPermission(value="dept:update")
	public String editUI(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Integer id) {
		model.addAttribute("optionName", "修改部门");
		model.addAttribute("optionFlag", "1");
		Department dept = departmentServiceImpl.findById(id);
		model.addAttribute("department", dept);
		return "dept/deptEdit";
	}
	@RequestMapping(value = "/id/{id}")
	@AckPermission(value="dept:find or dept:update")
	@ResponseBody
	@Override
	public Department findById(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Integer id) {
		return super.findById(request, response, model, id);
	}
	@RequestMapping(value = "/insert")
	@AckPermission(value="dept:add")
	@ResponseBody
	@Override
	public Integer insert(HttpServletRequest request,
			HttpServletResponse response, Model model, Department t) {
		return super.insert(request, response, model, t);
	}
	@RequestMapping(value = "/del/{id}")
	@AckPermission(value="dept:delete")
	@ResponseBody
	@Override
	public Integer deleteById(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Integer id) {
		return super.deleteById(request, response, model, id);
	}
	@RequestMapping(value = "/edit")
	@AckPermission(value="dept:update")
	@ResponseBody
	@Override
	public Integer edit(HttpServletRequest request,
			HttpServletResponse response, Model model, Department t) {
		return super.edit(request, response, model, t);
	}
	
}
