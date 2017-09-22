package org.ack.persist.mapper;

import java.util.List;

import org.ack.persist.AckMapper;
import org.ack.pojo.ProjectTask;
import org.ack.pojo.User;

/**
 * 项目细节
 * 
 * @author ack
 *
 */
public interface ProjectTaskMapper extends AckMapper<ProjectTask, Long> {

	/**
	 * 设置合作用户
	 * 
	 * @param t
	 * @return
	 */
	public Integer setUsers(ProjectTask t);

	/**
	 * 查询所有项目所有合作者
	 * 
	 * @param t
	 * @return
	 */
	public List<User> findAllProjectCooperator(ProjectTask t);

	/**
	 * 查询已有项目所有合作者
	 * 
	 * @param t
	 * @return
	 */
	public List<User> findExistProjectCooperator(ProjectTask t);

	/**
	 * 查询可进行项目
	 * 
	 * @param user
	 * @return
	 */
	public List<ProjectTask> findUsableProjectTaskList(User user);

}
