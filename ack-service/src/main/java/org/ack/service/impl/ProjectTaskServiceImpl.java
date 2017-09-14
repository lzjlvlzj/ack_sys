package org.ack.service.impl;

import java.util.List;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.ProjectTaskMapper;
import org.ack.pojo.ProjectTask;
import org.ack.pojo.ProjectTaskLinkUser;
import org.ack.pojo.User;
import org.ack.service.ProjectTaskLinkUserService;
import org.ack.service.ProjectTaskService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目明细业务逻辑
 * 
 * @author GeoStar
 *
 */
@Service
public class ProjectTaskServiceImpl extends
		AckMapperServiceImpl<ProjectTask, Long> implements ProjectTaskService {

	private static final Logger logger = LoggerFactory
			.getLogger(ProjectTaskServiceImpl.class);
	
	@Autowired
	private ProjectTaskMapper projectTaskMapper;
	
	@Autowired
	private ProjectTaskLinkUserService projectTaskLinkUserServiceImpl;
	
	@Override
	protected AckMapper<ProjectTask, Long> getAckMapper() {
		return projectTaskMapper;
	}

	@Override
	public Integer setCooperatorAdd(Long taskId, String ids) {
		
		if(StringUtils.isBlank(ids)){
			logger.error("项目参与人员为空");
			return 0;
		}
		String[] userIds = ids.split(",");
		int r = 0;
		r = projectTaskLinkUserServiceImpl.deleteByProjectTaskId(taskId);
		if(logger.isDebugEnabled()){
			logger.debug("删除已参与数量 :" + r);
		}
		if(r < 0 ){
			return 0;
		}
		for(int i = 0; i < userIds.length; i++){
			Long userId = Long.parseLong(userIds[i]);
			ProjectTaskLinkUser ptu = new ProjectTaskLinkUser();
			ptu.setProjectTaskId(taskId);
			ptu.setUserId(userId);
			r = projectTaskLinkUserServiceImpl.insert(ptu);
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
	public List<User> findAllProjectCooperator(ProjectTask t) {
		List<User> list = projectTaskMapper.findAllProjectCooperator(t);
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
	public List<User> findExistProjectCooperator(ProjectTask t) {
		List<User> existList = projectTaskMapper.findExistProjectCooperator(t);
		if(null != existList && logger.isDebugEnabled()){
			logger.debug("项目已经参与人员数量 : {}", existList.size());
		}
		return existList;
	}

}
