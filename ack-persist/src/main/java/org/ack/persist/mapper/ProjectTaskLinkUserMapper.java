package org.ack.persist.mapper;

import org.ack.persist.AckMapper;
import org.ack.pojo.ProjectTaskLinkUser;

public interface ProjectTaskLinkUserMapper extends
		AckMapper<ProjectTaskLinkUser, Long> {
	/**
	 * 根据项目任务id删除
	 * 
	 * @param deleteByProjectTaskId
	 * @return
	 */
	public Integer deleteByProjectTaskId(Long deleteByProjectTaskId);
}
