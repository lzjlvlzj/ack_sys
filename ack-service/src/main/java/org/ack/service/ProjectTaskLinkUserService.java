package org.ack.service;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.ProjectTaskLinkUser;

public interface ProjectTaskLinkUserService extends
		AckMapperService<ProjectTaskLinkUser, Long> {

	/**
	 * 根据项目任务id删除
	 * @param deleteByProjectTaskId
	 * @return
	 */
	public Integer deleteByProjectTaskId(Long deleteByProjectTaskId);
	
}
