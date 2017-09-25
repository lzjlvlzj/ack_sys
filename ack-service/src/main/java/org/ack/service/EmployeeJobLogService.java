package org.ack.service;

import java.util.List;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.EmployeeJobLog;
import org.ack.pojo.Project;
import org.ack.pojo.User;

public interface EmployeeJobLogService extends
		AckMapperService<EmployeeJobLog, Long> {

	/**
	 * 新建工作日志的缓冲
	 * 
	 * @param employeeJobLog
	 * @return 插入的主键id
	 */
	public Long insertCache(EmployeeJobLog employeeJobLogCache);

	/**
	 * 查询未关闭的项目任务
	 * 
	 * @param user
	 * @return
	 */
	public List<Project> findProjectList(User user);

}
