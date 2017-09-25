package org.ack.service.impl;


import java.util.Date;
import java.util.List;

import org.ack.persist.mapper.EmployeeMapper;
import org.ack.persist.page.Page;
import org.ack.pojo.Project;
import org.ack.service.EmployeeService;
import org.ack.service.ProjectService;
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
	private ProjectService projectServiceImpl;

	@Override
	public Page<Project> findPage(Page<Project> page) {
		List<Project> list = employeeMapper.findInterceptorPageList(page);
		page.setResult(list);
		return page;
	}

	@Override
	public Integer completeTask(Long taskId) {
		if(logger.isDebugEnabled()){
			logger.debug("完成任务id : {}" , taskId);
		}
		Date endTime = new Date();
		Project pt = new Project();
		pt.setEndTime(endTime);
		return projectServiceImpl.update(pt);
	}


}
