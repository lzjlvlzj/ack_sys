package org.ack.persist.mapper;

import org.ack.persist.AckMapper;
import org.ack.pojo.ProjectLinkUser;

public interface ProjectLinkUserMapper extends
AckMapper<ProjectLinkUser, Long> {
	/**
	 * 根据项目任务id删除
	 * 
	 * @param deleteByProjectTaskId
	 * @return
	 */
	public Integer deleteByProjectId(Long projectId);
}
