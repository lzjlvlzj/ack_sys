package org.ack.service;

import java.util.List;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.Project;

/**
 * 项目业务逻辑
 * 
 * @author ack
 *
 */
public interface ProjectService extends AckMapperService<Project, Long> {

	/**
	 * 根据部门id查询项目
	 * 
	 * @param id
	 * @return
	 */
	public List<Project> findByDepartmentId(Integer id);

}
