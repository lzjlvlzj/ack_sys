package org.ack.service.impl;


import java.util.Date;
import java.util.List;

import org.ack.persist.mapper.EmployeeMapper;
import org.ack.persist.page.Page;
import org.ack.pojo.ProjectTask;
import org.ack.service.EmployeeService;
import org.ack.service.ProjectTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private static final Logger logger = LoggerFactory
			.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private ProjectTaskService projectTaskServiceImpl;

	@Override
	public Page<ProjectTask> findPage(Page<ProjectTask> page) {
		List<ProjectTask> list = employeeMapper.findInterceptorPageList(page);
		page.setResult(list);
		return page;
	}

	@Override
	public Integer completeTask(Long taskId) {
		if(logger.isDebugEnabled()){
			logger.debug("完成任务id : {}" , taskId);
		}
		Date endTime = new Date();
		ProjectTask pt = new ProjectTask();
		pt.setEndTime(endTime);
		return projectTaskServiceImpl.update(pt);
	}


}
