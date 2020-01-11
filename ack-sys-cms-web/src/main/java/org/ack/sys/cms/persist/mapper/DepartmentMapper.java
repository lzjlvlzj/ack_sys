package org.ack.sys.cms.persist.mapper;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.cms.pojo.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper extends PageDao<Department, Long> {

	public Department findByName(String name);


}