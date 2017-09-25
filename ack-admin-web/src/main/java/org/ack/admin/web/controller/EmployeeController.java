package org.ack.admin.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.base.web.PageController;
import org.ack.common.datatable.DataTableTemplate;
import org.ack.persist.page.Page;
import org.ack.pojo.Project;
import org.ack.pojo.User;
import org.ack.service.EmployeeService;
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

@RequestMapping("/employee")
@Controller
public class EmployeeController extends PageController {

	private static final Logger logger = LoggerFactory
			.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeServiceImpl;

	/**
	 * 结束任务
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param taskId
	 * @return
	 */
	@RequestMapping("/complete/{taskId}")
	@ResponseBody
	public Integer completeTask(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Long taskId) {
		return employeeServiceImpl.completeTask(taskId);
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
		return "employee/employeeList";
	}

	/**
	 * 分页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param t
	 * @param start
	 * @param length
	 * @param draw
	 * @param orderColumn
	 * @param orderType
	 * @return
	 */
	@RequestMapping(value = "/table")
	@ResponseBody
	public DataTableTemplate<Project> dataTable(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() Project t,
			@RequestParam(required = false, defaultValue = "1") int draw,
			@RequestParam(required = false, defaultValue = "1") int start,
			@RequestParam(required = false, defaultValue = "10") int length,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		if (logger.isDebugEnabled()) {
			logger.debug("员工任务分页查询");
		}
		// 查询条件
		Map<String, Object> map = this.getQueryConditions(request, null,
				Project.class);
		User user = (User) getCurrentUser(request);
		map.put("userId", user.getId());
		// 排序map
		Map<String, String> sortMap = getSortCondition(request);
		// 排序方式
		orderType = sortMap.get("orderType");
		// 排序字段
		orderColumn = sortMap.get("orderColumn");
		// 计算当前页，构造page
		int currentPage = start / length + 1;
		Page<Project> page = new Page<Project>(currentPage, length);
		page.setOrderColumn(orderColumn);
		page.setOrderType(orderType);
		page.setCondition(map);

		page = employeeServiceImpl.findPage(page);
		DataTableTemplate<Project> dt = changePage2DataTableTemplate(page,
				draw);
		return dt;
	}

	private DataTableTemplate<Project> changePage2DataTableTemplate(
			Page<Project> page, int draw) {
		DataTableTemplate<Project> dt = new DataTableTemplate<Project>();
		dt.setDraw(draw);
		dt.setError("");
		dt.setData(page.getResult());
		dt.setRecordsTotal(page.getTotalRecord());
		dt.setRecordsFiltered(page.getTotalRecord());
		return dt;
	}

}
