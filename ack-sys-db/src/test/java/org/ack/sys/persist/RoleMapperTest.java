package org.ack.sys.persist;

import org.ack.sys.persist.mapper.RoleMapper;
import org.ack.sys.pojo.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class RoleMapperTest extends BaseTest{
	
	RoleMapper roleMapper;
	
	@BeforeEach
	public void init() {
		roleMapper = sqlSession.getMapper(RoleMapper.class);
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
		Date date = new Date();
		Long id = 2L;
		Role role = new Role();
		role.setName("销售人员");
		role.setCreator(id);
		role.setCreateTime(date);
		role.setModifier(id);
		role.setModifyTime(date);
		role.setRemark("销售人员");
		role.setDeleteStatus(0);
		
		int r = roleMapper.insert(role);
		System.out.println("r = " + r);
		commit();
		assertEquals(1, r);
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
