package org.ack.admin.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.common.datatable.DataTableTemplate;
import org.ack.pojo.ProjectTask;
import org.ack.pojo.ProjectTaskLinkUser;
import org.ack.pojo.User;
import org.ack.service.ProjectTaskService;
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

@Controller
@RequestMapping("/ptask")
public class ProjectTaskController extends AckPageController<ProjectTask, Long> {

	@Autowired
	private ProjectTaskService projectTaskServiceImpl;

	private static final Logger logger = LoggerFactory
			.getLogger(ProjectTaskController.class);

	@Override
	public AckMapperService<ProjectTask, Long> getService() {
		return projectTaskServiceImpl;
	}

	@RequestMapping("/edit/ui/{id}")
	public String eidtUI(@PathVariable Integer id) {
		if (logger.isDebugEnabled()) {
			logger.debug("修改项目任务:{}", id);
		}
		return "projectTask/projectTaskEdit";
	}

	@RequestMapping("/cooperators/{id}")
	public String setCooperatorsUI(@PathVariable Integer id) {
		if (logger.isDebugEnabled()) {
			logger.debug("分配项目人员:{}", id);
		}
		return "projectTask/projectTaskUsers";
	}

	@RequestMapping("/add/ui")
	public String addUI() {
		if (logger.isDebugEnabled()) {
			logger.debug("新建项目任务");
		}
		return "projectTask/projectTaskEdit";
	}

	@RequestMapping("/id/{id}")
	@ResponseBody
	@Override
	public ProjectTask findById(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Long id) {
		return super.findById(request, response, model, id);
	}

	/**
	 * 插入
	 * 
	 * */
	@RequestMapping("/add")
	@ResponseBody
	@Override
	public Integer insert(HttpServletRequest request,
			HttpServletResponse response, Model model, ProjectTask t) {
		t.setStatus(0);
		t.setStartTime(new Date());
		return super.insert(request, response, model, t);
	}

	@RequestMapping(value = "/del/{id}")
	@AckPermission(value = "ptask:delete")
	@ResponseBody
	@Override
	public Integer deleteById(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Long id) {
		return super.deleteById(request, response, model, id);
	}

	@RequestMapping(value = "/edit")
	@AckPermission(value = "ptask:update")
	@ResponseBody
	@Override
	public Integer edit(HttpServletRequest request,
			HttpServletResponse response, Model model, ProjectTask t) {
		return super.edit(request, response, model, t);
	}

	/**
	 * 查询惨目的所有参与人员
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param t
	 * @return
	 */
	@RequestMapping(value = "/cooperators")
	@ResponseBody
	public List<User> findProjectCooperator(HttpServletRequest request,
			HttpServletResponse response, Model model, ProjectTask t) {
	    String flag = request.getParameter("flag");
	    if(logger.isDebugEnabled()){
			logger.debug("项目任务id : {}, 查询标志位  : {}", t.getId(), flag);
		}
	    List<User> list = null;
	    if("0".equals(flag)){
	    	list = projectTaskServiceImpl.findAllProjectCooperator(t);
	    }
	    
	    if("1".equals(flag)){
	    	list = projectTaskServiceImpl.findExistProjectCooperator(t);
	    }
		return list;
	}

	/**
	 * 设置合作用户
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param t
	 * @return
	 */
	@RequestMapping("/cooperator/add")
	@ResponseBody
	public Integer setCooperatorAdd(HttpServletRequest request,
			HttpServletResponse response, Model model, ProjectTaskLinkUser t) {
		if (logger.isDebugEnabled()) {
			logger.debug("设置合作用户");
		}
		String userIds = request.getParameter("userIds");
		String projectTaskId = request.getParameter("projectTaskId");
		Long taskId = Long.parseLong(projectTaskId);
		//return 1;
		return projectTaskServiceImpl.setCooperatorAdd(taskId, userIds);
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
	public DataTableTemplate<ProjectTask> dataTable(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() ProjectTask t,
			@RequestParam(required = false, defaultValue = "1") int draw,
			@RequestParam(required = false, defaultValue = "1") int start,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		// 额外查询条件
		Map<String, Object> extraCondition = new HashMap<String, Object>();
		return super.dataTable(request, response, model, extraCondition, t,
				start, count, draw, orderColumn, orderType);
	}

}
