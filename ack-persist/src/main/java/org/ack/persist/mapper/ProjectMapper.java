package org.ack.persist.mapper;

import java.util.List;

import org.ack.persist.AckMapper;
import org.ack.pojo.Project;

/**
 * 项目mapper
 * 
 * @author ack
 *
 */
public interface ProjectMapper extends AckMapper<Project, Long> {

	/**
	 * 根据id查询项目
	 * 
	 * @param id
	 * @return
	 */
	public List<Project> findByDepartmentId(Integer departmentId);

	/**
	 * 返回插入主键
	 * 
	 * @param t
	 * @return
	 */
	public long insertReturnId(Project t);

}
