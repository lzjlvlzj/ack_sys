package org.ack.sys.cms.persist.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;

import org.ack.sys.cms.persist.BaseTest;
import org.ack.sys.cms.pojo.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepartmentMapperTest extends BaseTest{

	DepartmentMapper departmentMapper;
	@BeforeEach
	public void init() {
		departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
	}
	
	@AfterEach
	void after() {
		close();
	}
	@Test
	void testCount() {
		fail("Not yet implemented");
	}

	@Test
	void testFindPageList() {
		fail("Not yet implemented");
	}

	@Test
	void testFindInterceptorPageList() {
		fail("Not yet implemented");
	}

	@Test
	void testFind() {
		fail("Not yet implemented");
	}

	@Test
	void testInsert() {
		Long userId = 2L;
		Date date = new Date();
		
		Department dept = new Department();
		dept.setName("销售一部");
		dept.setCreator(userId);
		dept.setCreateTime(date);
		dept.setDeleteStatus(3);
		dept.setModifier(userId);
		dept.setModifyTime(date);
		dept.setParentId(0L);
		
		int r = departmentMapper.insert(dept);
		System.out.println(r);
		assertEquals(1, r);
		commit();
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteById() {
		fail("Not yet implemented");
	}

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testFindByIds() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteByIds() {
		fail("Not yet implemented");
	}

}
