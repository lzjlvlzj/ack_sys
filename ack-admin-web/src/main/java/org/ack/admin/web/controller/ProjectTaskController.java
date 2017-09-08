package org.ack.admin.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.base.service.AckMapperService;
import org.ack.common.datatable.DataTableTemplate;
import org.ack.pojo.ProjectTask;
import org.ack.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ptask")
public class ProjectTaskController extends AckPageController<ProjectTask, Long> {

	@Autowired
	private ProjectTaskService projectTaskServiceImpl;

	@Override
	public AckMapperService<ProjectTask, Long> getService() {
		return projectTaskServiceImpl;
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
		return "projectTask/projectTaskList";
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
	@Override
	public DataTableTemplate<ProjectTask> dataTable(HttpServletRequest request,
			HttpServletResponse response, Model model,
			Map<String, Object> extraCondition, ProjectTask t, int start,
			int length, int draw, String orderColumn, String orderType) {
		return super.dataTable(request, response, model, extraCondition, t,
				start, length, draw, orderColumn, orderType);
	}

}
