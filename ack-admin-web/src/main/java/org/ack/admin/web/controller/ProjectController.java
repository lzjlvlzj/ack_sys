package org.ack.admin.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.base.service.AckMapperService;
import org.ack.persist.page.Page;
import org.ack.pojo.Project;
import org.ack.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ack
 *
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends AckPageController<Project, Long>{
    
	@Autowired
	private ProjectService projectServiceImpl;

	@Override
	public AckMapperService<Project, Long> getService() {
		return projectServiceImpl;
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
	public String listUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "project/projectList";
	}
	
	/**
	 * 列表数据查询
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/page")
	@ResponseBody
	@Override
	public Page<Project> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() Project t,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "startTime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		return super.findPage(request, response, model, t, currentPage, count,
				orderColumn, orderType);
	}
}
