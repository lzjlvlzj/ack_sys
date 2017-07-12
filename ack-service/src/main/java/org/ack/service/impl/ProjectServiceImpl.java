package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.ProjectMapper;
import org.ack.pojo.Project;
import org.ack.service.ProjectService;
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
	
	@Autowired
	private ProjectMapper projectMapper;

	@Override
	protected AckMapper<Project, Long> getAckMapper() {
		return projectMapper;
	}

}
