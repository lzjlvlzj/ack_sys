package org.ack.sys.persist;

import org.ack.sys.persist.mapper.RoleMenuMapper;
import org.ack.sys.pojo.RoleMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class RoleMenuMapperTest extends BaseTest{
	
	RoleMenuMapper roleMenuMapper;
	@BeforeEach
	public void init() {
		roleMenuMapper = sqlSession.getMapper(RoleMenuMapper.class);
	}
	
	@AfterEach
	void after() {
		close();
	}

	@Test
	void testFind() {
		fail("Not yet implemented");
	}

	@Test
	void testInsert() {
		Date date = new Date();
		Long id = 2L;
		
		RoleMenu rm = new RoleMenu();
		rm.setCreator(id);
		rm.setCreateTime(date);
		rm.setModifier(id);
		rm.setModifyTime(date);
		rm.setRoleId(1L);
		rm.setMenuId(28L);
		
		int r = roleMenuMapper.insert(rm);
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
	void testFindBySelected() {
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

	@Test
	void testBatchUpdate() {
		fail("Not yet implemented");
	}

}
