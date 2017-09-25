package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.ProjectLinkUserMapper;
import org.ack.pojo.ProjectLinkUser;
import org.ack.service.ProjectLinkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectLinkUserServiceImpl extends
		AckMapperServiceImpl<ProjectLinkUser, Long> implements
		ProjectLinkUserService {
	
	@Autowired
	private ProjectLinkUserMapper projectLinkUserMapper;

	@Override
	protected AckMapper<ProjectLinkUser, Long> getAckMapper() {
		return projectLinkUserMapper;
	}

	@Override
	public Integer deleteByProjectId(Long projectId) {
		return projectLinkUserMapper.deleteByProjectId(projectId);
	}

}
