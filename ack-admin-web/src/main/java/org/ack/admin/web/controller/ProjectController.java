package org.ack.admin.web.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.persist.page.Page;
import org.ack.pojo.Department;
import org.ack.pojo.Project;
import org.ack.pojo.ProjectLinkUser;
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
	
	
	@RequestMapping("/cooperators/{id}")
	@AckPermission(value = "project:allocate")
	public String setCooperatorsUI(@PathVariable Integer id) {
		if (logger.isDebugEnabled()) {
			logger.debug("分配项目人员:{}", id);
		}
		return "project/projectCooperators";
	}
	
	/**
	 * 查找当前部门的项目经理
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/managers")
	@AckPermission(value = "project:add or project:update")
	@ResponseBody
	public List<User> findManagers(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("查找项目经理");
		}
		User currentUser = getCurrentUser(request);
		return projectServiceImpl.findManagers(currentUser);
	}
	
	@RequestMapping("/dept/{id}")
	@AckPermission(value = "project:add or project:update")
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
	 * 查询惨目的所有参与人员
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param t
	 * @return
	 */
	@RequestMapping(value = "/cooperators")
	@AckPermission(value = "project:allocate")
	@ResponseBody
	public List<User> findProjectCooperator(HttpServletRequest request,
			HttpServletResponse response, Model model, Project t) {
	    String flag = request.getParameter("flag");
	    if(logger.isDebugEnabled()){
			logger.debug("项目任务id : {}, 查询标志位  : {}", t.getId(), flag);
		}
	    List<User> list = null;
	    if("0".equals(flag)){
	    	list = projectServiceImpl.findAllProjectCooperator(t);
	    }
	    
	    if("1".equals(flag)){
	    	list = projectServiceImpl.findExistProjectCooperator(t);
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
	@AckPermission(value = "project:allocate")
	@ResponseBody
	public Integer setCooperatorAdd(HttpServletRequest request,
			HttpServletResponse response, Model model, ProjectLinkUser t) {
		if (logger.isDebugEnabled()) {
			logger.debug("设置合作用户");
		}
		String userIds = request.getParameter("userIds");
		String projectId = request.getParameter("projectId");
		Long taskId = Long.parseLong(projectId);
		//return 1;
		return projectServiceImpl.setCooperatorAdd(taskId, userIds);
	}
	
	@RequestMapping("/add/ui")
	@AckPermission(value = "project:add")
	public String addUI(){
		if (logger.isDebugEnabled()) {
			logger.debug("新建项目");
		}
		return "project/projectEdit";
	}
	 
	/**
	 * 插入
	 * 
	 * */
	@RequestMapping("/add")
	@AckPermission(value = "project:add")
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
	@AckPermission(value = "project:delete")
	@ResponseBody
	@Override
	public Integer deleteById(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable Long id) {
		return super.deleteById(request, response, model, id);
	}

	@RequestMapping("/edit/ui/{id}")
	@AckPermission(value = "project:update")
	public String eidtUI(@PathVariable Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("修改项目:{}", id);
		}
		return "project/projectEdit";
	}
	
	@RequestMapping(value = "/edit")
	@AckPermission(value = "project:update")
	@ResponseBody
	@Override
	public Integer edit(HttpServletRequest request,
			HttpServletResponse response, Model model, Project project) {
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
		
		return projectServiceImpl.update(project);
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
	@AckPermission(value = "project:list")
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
	@AckPermission(value = "project:list")
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
		// 非admin用户只能查询当前用户所在部门的员工信息
		Set<Role> roles = user.getRoles();
		boolean b = true;
		for(Role role : roles){
			Integer viewStatus = role.getViewStatus();
			if(viewStatus == 1){
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
