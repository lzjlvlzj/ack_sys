package org.ack.persist.mapper;

import static org.junit.Assert.*;

import org.ack.pojo.Department;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DepartmentMapperTest extends BaseTest{
	
	DepartmentMapper departmentMapper;
	
	@Before
	public void init() {
		departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
	}

	@Test
	public void testFindInterceptorPageList() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		Department dept = new Department();
		dept.setDepartmentName("xxxx科技有限公司");
		dept.setParentId(-1);
		dept.setComments("xxxx科技有限公司测试");
		
		int r = departmentMapper.insert(dept);
    	sqlSession.commit();
		close();
		Assert.assertEquals(1, r);
		
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
