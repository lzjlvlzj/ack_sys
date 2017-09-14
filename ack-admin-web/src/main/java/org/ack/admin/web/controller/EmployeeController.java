package org.ack.admin.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.base.web.PageController;
import org.ack.common.datatable.DataTableTemplate;
import org.ack.persist.page.Page;
import org.ack.pojo.ProjectTask;
import org.ack.pojo.User;
import org.ack.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public DataTableTemplate<ProjectTask> dataTable(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() ProjectTask t,
			@RequestParam(required = false, defaultValue = "1") int draw,
			@RequestParam(required = false, defaultValue = "1") int start,
			@RequestParam(required = false, defaultValue = "10") int length,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		if (logger.isDebugEnabled()) {
			logger.debug("员工任务分页查询");
		}
		// 查询条件
		Map<String, Object> map = this.getQueryConditions(request, null, ProjectTask.class);
		User user = (User)getCurrentUser(request);
		map.put("userId", user.getId());
		// 排序map
		Map<String, String> sortMap = getSortCondition(request);
		// 排序方式
		orderType = sortMap.get("orderType");
		// 排序字段
		orderColumn = sortMap.get("orderColumn");
		// 计算当前页，构造page
		int currentPage = start / length + 1;
		Page<ProjectTask> page = new Page<ProjectTask>(currentPage, length);
		page.setOrderColumn(orderColumn);
		page.setOrderType(orderType);
		page.setCondition(map);

		page = employeeServiceImpl.findPage(page);
		DataTableTemplate<ProjectTask> dt = changePage2DataTableTemplate(page,
				draw);
		return dt;
	}

	private DataTableTemplate<ProjectTask> changePage2DataTableTemplate(
			Page<ProjectTask> page, int draw) {
		DataTableTemplate<ProjectTask> dt = new DataTableTemplate<ProjectTask>();
		dt.setDraw(draw);
		dt.setError("");
		dt.setData(page.getResult());
		dt.setRecordsTotal(page.getTotalRecord());
		dt.setRecordsFiltered(page.getTotalRecord());
		return dt;
	}

}
