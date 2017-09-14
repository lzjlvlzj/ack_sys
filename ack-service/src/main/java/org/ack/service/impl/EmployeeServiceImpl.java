package org.ack.service.impl;


import java.util.List;

import org.ack.persist.mapper.EmployeeMapper;
import org.ack.persist.page.Page;
import org.ack.pojo.ProjectTask;
import org.ack.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public Page<ProjectTask> findPage(Page<ProjectTask> page) {
		List<ProjectTask> list = employeeMapper.findInterceptorPageList(page);
		page.setResult(list);
		return page;
	}


}
