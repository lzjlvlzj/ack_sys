package org.ack.service.impl;


import java.util.List;

import org.ack.base.persist.BaseMapper;
import org.ack.base.service.impl.BaseServiceImpl;
import org.ack.persist.mapper.EmployeeJobLogMapper;
import org.ack.persist.page.Page;
import org.ack.pojo.EmployeeJobLog;
import org.ack.pojo.ProjectTask;
import org.ack.pojo.User;
import org.ack.service.EmployeeJobLogService;
import org.ack.service.ProjectTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeJobLogServiceImpl extends BaseServiceImpl<EmployeeJobLog, Long>
		implements EmployeeJobLogService {
	
	private static final Logger logger = LoggerFactory
			.getLogger(EmployeeJobLogServiceImpl.class);
	@Autowired
	private EmployeeJobLogMapper employeeJobMapper;
	@Autowired
	ProjectTaskService projectTaskServiceImpl;

	@Override
	public BaseMapper<EmployeeJobLog, Long> getMapper() {
		return employeeJobMapper;
	}

	@Override
	public Page<EmployeeJobLog> findPage(Page<EmployeeJobLog> page) {
		List<EmployeeJobLog> list = employeeJobMapper.findInterceptorPageList(page);
		page.setResult(list);
		return page;
	}

	@Override
	public Long insertCache(EmployeeJobLog employeeJobLog) {
		int n = employeeJobMapper.insert(employeeJobLog);
		if(n > 0){
			long id = employeeJobLog.getId();
			if(logger.isDebugEnabled()){
				logger.debug("插入的主键id : {}", id);
			}
			return id;
		}
		return 0L;
	}

	@Override
	public List<ProjectTask> findProjectTaskList(User user) {
		return projectTaskServiceImpl.findUsableProjectTaskList(user);
	}

}
