package org.ack.persist.mapper;

import org.ack.persist.AckMapper;
import org.ack.pojo.ProjectLinkDepartMent;

public interface ProjectLinkDepartMentMapper extends
		AckMapper<ProjectLinkDepartMent, Long> {

	/**
	 * 根据项目id删除
	 * 
	 * @param pid
	 * @return
	 */
	public int deleteByProjectId(long pid);

}
