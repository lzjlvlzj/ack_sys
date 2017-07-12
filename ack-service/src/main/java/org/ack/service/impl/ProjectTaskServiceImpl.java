package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.ProjectTaskMapper;
import org.ack.pojo.ProjectTask;
import org.ack.service.ProjectTaskService;
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

	@Autowired
	private ProjectTaskMapper projectTaskMapper;
	
	@Override
	protected AckMapper<ProjectTask, Long> getAckMapper() {
		return projectTaskMapper;
	}

}
