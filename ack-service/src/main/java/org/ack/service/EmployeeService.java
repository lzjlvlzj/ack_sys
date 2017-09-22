package org.ack.service;

import org.ack.persist.page.Page;
import org.ack.pojo.ProjectTask;

public interface EmployeeService {

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @return
	 */
	public Page<ProjectTask> findPage(Page<ProjectTask> page);

	/** 完成任务
	 * @param taskId
	 * @return
	 */
	public Integer completeTask(Long taskId);

}
