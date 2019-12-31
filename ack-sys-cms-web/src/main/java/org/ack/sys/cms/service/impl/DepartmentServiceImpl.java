package org.ack.sys.cms.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.cms.persist.mapper.DepartmentMapper;
import org.ack.sys.cms.pojo.Department;
import org.ack.sys.cms.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**用戶邏輯
 * @author ack
 *
 */
@Service
public class DepartmentServiceImpl extends PageServiceImpl<Department, Long> implements DepartmentService {
	private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	protected PageDao<Department, Long> getPageDao() {
		logger.debug("mapper is： {}", departmentMapper);
		return departmentMapper;
	}

	@Override
	public List<Department> findTree() {
		List<Department> list = departmentMapper.findAll();
		List<Department> deptList = new ArrayList<Department>();
		int size = list.size();
		for(int i = 0 ; i < size; i++) {
			Department dept = list.get(i);
			if(dept.getParentId() == 0) {
				deptList.add(dept);
			} 
		}
		
		findChildren(deptList, list);
		return deptList;
	}

	private void findChildren(List<Department> deptList, List<Department> list) {
		for(Department dept : deptList) {
			Long id = dept.getId();
			List<Department> children = new ArrayList<Department>();
			for(Department dt : list) {
				if(dt.getParentId() == id) {
					children.add(dt);
				} 
			}
			dept.setChildren(children);
			findChildren(children, list);
		}
		
	}

	

	



	

}
