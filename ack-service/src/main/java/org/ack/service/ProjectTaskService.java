package org.ack.service;

import java.util.List;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.ProjectTask;
import org.ack.pojo.User;

/**
 * 项目明细
 * 
 * @author ack
 *
 */
public interface ProjectTaskService extends AckMapperService<ProjectTask, Long> {

	/**
	 * 设置合作用户
	 * 
	 * @param t
	 * @return
	 */
	public Integer setCooperatorAdd(Long taskId, String userIds);

	/**
	 * 查询项目合作者
	 * 
	 * @param t
	 * @return
	 */
	public List<User> findAllProjectCooperator(ProjectTask t);

	/**
	 * 查询已有
	 * 
	 * @param t
	 * @return
	 */
	public List<User> findExistProjectCooperator(ProjectTask t);

	/**
	 * 查询可进行的项目任务
	 * 
	 * @param user
	 * @return
	 */
	public List<ProjectTask> findUsableProjectTaskList(User user);

}
