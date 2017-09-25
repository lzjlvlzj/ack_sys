package org.ack.service;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.ProjectLinkUser;

public interface ProjectLinkUserService extends
		AckMapperService<ProjectLinkUser, Long> {

	/**
	 * 根据项目任务id删除
	 * @param deleteByProjectTaskId
	 * @return
	 */
	public Integer deleteByProjectId(Long deleteByProjectId);
	
}
