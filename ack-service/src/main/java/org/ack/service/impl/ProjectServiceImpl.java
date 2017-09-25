package org.ack.service.impl;

import java.util.List;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.ProjectMapper;
import org.ack.pojo.Department;
import org.ack.pojo.Project;
import org.ack.pojo.ProjectLinkDepartMent;
import org.ack.pojo.ProjectLinkUser;
import org.ack.pojo.User;
import org.ack.service.DepartmentService;
import org.ack.service.ProjectLinkDepartMentService;
import org.ack.service.ProjectLinkUserService;
import org.ack.service.ProjectService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目业务逻辑接口
 * 
 * @author ack
 *
 */
@Service
public class ProjectServiceImpl extends AckMapperServiceImpl<Project, Long>
		implements ProjectService {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ProjectServiceImpl.class);
	
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private DepartmentService departmentServiceImpl;
	@Autowired
	private ProjectLinkDepartMentService projectLinkDepartMentServiceImpl;
	@Autowired
	private ProjectLinkUserService projectLinkUserServiceImpl;

	@Override
	protected AckMapper<Project, Long> getAckMapper() {
		return projectMapper;
	}
	
	@Override
	public int insert(Project t) {
		// 插入工程
		projectMapper.insertReturnId(t);
		long pid = t.getProjectId();
		if(logger.isDebugEnabled()){
			logger.debug("插入新项目id : {}", pid);
		}
		if(pid <= 0){
			logger.error("插入新项目失败 : {}", t);
			return -1;
		}
		int r = 0;
		// 插入中间表
		for(Department dept : t.getCooperativeSectors()){
			int id = dept.getId();
			ProjectLinkDepartMent pdm = new ProjectLinkDepartMent();
			pdm.setDepartmentId(id);
			pdm.setProjectId(pid);
			r = projectLinkDepartMentServiceImpl.insert(pdm);
			if(r <= 0){
				logger.error("插入新项目失败 : {}", t);
				return  -1;
			}
		}
		return r;
	}
	
	@Override
	public Project findById(Long id) {
		Project p = super.findById(id);
		Department dept = departmentServiceImpl.findById(p.getDepartmentId());
		p.setDepartmentName(dept.getDepartmentName());
		return p;
	}

	@Override
	public List<Project> findByDepartmentId(Integer id) {
		
		return projectMapper.findByDepartmentId(id);
	}

	@Override
	public List<User> findAllProjectCooperator(Project t) {
		List<User> list = projectMapper.findAllProjectCooperator(t);
		if(null != list && logger.isDebugEnabled()){
			logger.debug("项目参与人员数量(没去重复) : {}", list.size());
		}
		List<User> existList = findExistProjectCooperator(t);
		list.removeAll(existList);
		if(null != list && logger.isDebugEnabled()){
			logger.debug("项目参与人员数量(去重复) : {}", list.size());
		}
		return list;
	}

	@Override
	public List<User> findExistProjectCooperator(Project t) {
		List<User> existList = projectMapper.findExistProjectCooperator(t);
		if(null != existList && logger.isDebugEnabled()){
			logger.debug("项目已经参与人员数量 : {}", existList.size());
		}
		return existList;
	}
	
	@Override
	public Integer setCooperatorAdd(Long taskId, String ids) {
		
		if(StringUtils.isBlank(ids)){
			logger.error("项目参与人员为空");
			return 0;
		}
		String[] userIds = ids.split(",");
		int r = 0;
		r = projectLinkUserServiceImpl.deleteByProjectId(taskId);
		if(logger.isDebugEnabled()){
			logger.debug("删除已参与数量 :" + r);
		}
		if(r < 0 ){
			return 0;
		}
		for(int i = 0; i < userIds.length; i++){
			Long userId = Long.parseLong(userIds[i]);
			ProjectLinkUser ptu = new ProjectLinkUser();
			ptu.setProjectId(taskId);
			ptu.setUserId(userId);
			r = projectLinkUserServiceImpl.insert(ptu);
			if(r <= 0){
				return 0;
			}
		}
		if(r >= 1){
		   return 1;	
		}
		return r;
	}

	@Override
	public List<Project> findUsableProjectList(User user) {
		return projectMapper.findUsableProjectList(user);
	}


}
