package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.DepartmentMapper;
import org.ack.pojo.Department;
import org.ack.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends AckMapperServiceImpl<Department,Integer> implements DepartmentService{

	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	protected AckMapper<Department, Integer> getAckMapper() {
		return departmentMapper;
	}

}
