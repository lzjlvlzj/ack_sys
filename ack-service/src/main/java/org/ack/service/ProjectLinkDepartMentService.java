package org.ack.service;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.ProjectLinkDepartMent;

public interface ProjectLinkDepartMentService extends
		AckMapperService<ProjectLinkDepartMent, Long> {

	/**
	 * 根据项目id删除
	 * 
	 * @param pid
	 * @return
	 */
	public int deleteByProjectId(long pid);

}
