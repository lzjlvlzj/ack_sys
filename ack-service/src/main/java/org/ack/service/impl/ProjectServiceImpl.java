package org.ack.service.impl;

import java.util.List;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.ProjectMapper;
import org.ack.pojo.Department;
import org.ack.pojo.Project;
import org.ack.pojo.ProjectLinkDepartMent;
import org.ack.service.DepartmentService;
import org.ack.service.ProjectLinkDepartMentService;
import org.ack.service.ProjectService;
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


}
