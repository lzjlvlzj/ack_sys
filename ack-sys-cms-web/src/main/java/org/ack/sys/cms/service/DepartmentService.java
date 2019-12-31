package org.ack.sys.cms.service;

import java.util.List;

import org.ack.sys.base.service.BaseService;
import org.ack.sys.cms.pojo.Department;

public interface DepartmentService extends BaseService<Department, Long> {

	public List<Department> findTree();

}
