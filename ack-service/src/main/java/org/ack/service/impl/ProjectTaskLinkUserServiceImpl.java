package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.ProjectTaskLinkUserMapper;
import org.ack.pojo.ProjectTaskLinkUser;
import org.ack.service.ProjectTaskLinkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskLinkUserServiceImpl extends
		AckMapperServiceImpl<ProjectTaskLinkUser, Long> implements
		ProjectTaskLinkUserService {
	
	@Autowired
	private ProjectTaskLinkUserMapper projectTaskLinkUserMapper;

	@Override
	protected AckMapper<ProjectTaskLinkUser, Long> getAckMapper() {
		return projectTaskLinkUserMapper;
	}

	@Override
	public Integer deleteByProjectTaskId(Long projectTaskId) {
		return projectTaskLinkUserMapper.deleteByProjectTaskId(projectTaskId);
	}

}
