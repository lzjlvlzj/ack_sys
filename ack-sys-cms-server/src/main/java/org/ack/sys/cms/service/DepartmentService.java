package org.ack.sys.cms.service;

import java.util.List;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.service.BaseService;
import org.ack.sys.pojo.Department;
import org.springframework.web.multipart.MultipartFile;

public interface DepartmentService extends BaseService<Department, Long> {

	public List<Department> findTree();
	
	public Department findByName(String name);

	ResponseResult upload(MultipartFile file);
}
