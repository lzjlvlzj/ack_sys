package org.ack.admin.web.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.base.service.AckMapperService;
import org.ack.common.Content;
import org.ack.persist.page.Page;
import org.ack.pojo.Department;
import org.ack.pojo.Project;
import org.ack.pojo.Role;
import org.ack.pojo.User;
import org.ack.service.ProjectService;
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
 * @author ack
 *
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends AckPageController<Project, Long>{
	
	private static final Logger logger = LoggerFactory
			.getLogger(ProjectController.class);
    
	@Autowired
	private ProjectService projectServiceImpl;

	@Override
	public AckMapperService<Project, Long> getService() {
		return projectServiceImpl;
	}
	 
	
	/**
	 * 插入
	 * 
	 * */
	@RequestMapping("/add")
	@ResponseBody
	@Override
	public Integer insert(HttpServletRequest request,
			HttpServletResponse response, Model model, Project project) {
		if (logger.isDebugEnabled()) {
			logger.debug("新建项目");
		}
		List<Department> list = new ArrayList<Department>();
		String[] cooperativeSector = request.getParameterValues("cooperativeSector");
		if(null != cooperativeSector){
			int len = cooperativeSector.length;
			for(int i = 0; i < len; i++){
				int deptId = Integer.parseInt(cooperativeSector[i]);
				Department dept = new Department();
				dept.setId(deptId);
				list.add(dept);
			}
		}
		project.setCooperativeSectors(list);
		project.setStartTime(new Date());
		project.setStatus(0);
		return projectServiceImpl.insert(project);
	}

	@RequestMapping(value = "/id/{id}")
	@ResponseBody
	@Override
	public Project findById(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Long id) {
		return projectServiceImpl.findById(id);
	}
	
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	@Override
	public Integer deleteById(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Long id) {
		return super.deleteById(request, response, model, id);
	}

	@RequestMapping("/edit/ui/{id}")
	public String eidtUI(@PathVariable Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("修改项目:{}", id);
		}
		return "project/projectEdit";
	}
	
	@RequestMapping(value = "/edit")
	@ResponseBody
	@Override
	public Integer edit(HttpServletRequest request,
			HttpServletResponse response, Model model, Project t) {
		return super.edit(request, response, model, t);
	}
	
	
	@RequestMapping("/add/ui")
	public String addUI(){
		if (logger.isDebugEnabled()) {
			logger.debug("新建项目");
		}
		return "project/projectEdit";
	}
	
	@RequestMapping("/dept/{id}")
	@ResponseBody
	public List<Project> findByDepartmentId(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("部门id: {}", id);
		}
		/*
		 * 当前部门所有项目 + 公开项目  + 和其他部门合作的项目
		 * 
		 * */
		return projectServiceImpl.findByDepartmentId(id);
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
	public Page<Project> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() Project t,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "startTime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		User user = getCurrentUser(request);
		Set<Role> roles = user.getRoles();
		boolean b = true;
		for(Role role : roles){
			String abbr = role.getAbbreviation();
			if(abbr.indexOf(Content.ADMIN_USER) > 1
				|| abbr.equals(Content.CEO_USER)
				|| abbr.equals(Content.CTO_USER)
				|| abbr.equals(Content.CFO_USER)
				|| abbr.equals(Content.COO_USER)
			  ){
				b = false;
				break;
			}
		}
		if(b){
			map.put("departmentId", user.getDepartmentId());
		}
		
		return super.findPage(request, response, model, map, t, currentPage, count,
				orderColumn, orderType);
	}
}
