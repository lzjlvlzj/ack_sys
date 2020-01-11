package org.ack.sys.cms.persist.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.List;

import org.ack.sys.cms.persist.BaseTest;
import org.ack.sys.cms.pojo.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRoleMapperTest extends BaseTest{
	
	UserRoleMapper userRoleMapper;
	@BeforeEach
	public void init() {
		userRoleMapper = sqlSession.getMapper(UserRoleMapper.class);
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
	void testFindByUserId() {
		Long userId = 2L;
		List<UserRole> list = userRoleMapper.findByUserId(userId);
		System.out.println(list.size());
		assertNotNull(list);
	}

	@Test
	void testInsert() {
		Date date = new Date();
		Long id = 2L;
		UserRole ur = new UserRole();
		ur.setUserId(2L);
		ur.setRoleId(1L);
		ur.setCreator(id);
		ur.setCreateTime(date);
		ur.setModifier(id);
		ur.setModifyTime(date);
		
		int r = userRoleMapper.insert(ur);
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
