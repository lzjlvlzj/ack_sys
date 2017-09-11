package org.ack.service.impl;


import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.ProjectLinkDepartMentMapper;
import org.ack.pojo.ProjectLinkDepartMent;
import org.ack.service.ProjectLinkDepartMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectLinkDepartMentServiceImpl extends
		AckMapperServiceImpl<ProjectLinkDepartMent, Long> implements
		ProjectLinkDepartMentService {

	@Autowired
	private ProjectLinkDepartMentMapper projectLinkDepartMentMapper;
	
	@Override
	protected AckMapper<ProjectLinkDepartMent, Long> getAckMapper() {
		return projectLinkDepartMentMapper;
	}

	

}
