package org.ack.sys.persist;

import org.ack.sys.persist.mapper.UserRoleMapper;
import org.ack.sys.pojo.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
	void testSave() {
		Long id = 2L;
		Date date = new Date();
		UserRole ur1 = new UserRole(2L, 5L);
		ur1.setCreator(id);
		ur1.setCreateTime(date);
		ur1.setModifier(2L);
		ur1.setModifyTime(date);
		
	    int r = userRoleMapper.save(ur1);
	    System.out.println(r);
	    commit();
	    assertEquals(1, r);
	}
	
	@Test
	void testBatchUpdate() {
		Long id = 2L;
		Date date = new Date();
		List<UserRole> list = new ArrayList<>();
		UserRole ur1 = new UserRole(2L, 1L);
		ur1.setCreator(id);
		ur1.setCreateTime(date);
		ur1.setModifier(2L);
		ur1.setModifyTime(date);
		
		UserRole ur2 = new UserRole(2L, 3L);
		ur2.setCreator(id);
		ur2.setCreateTime(date);
		ur2.setModifier(2L);
		ur2.setModifyTime(date);
		
		list.add(ur1);
		list.add(ur2);
		int r = userRoleMapper.batchUpdate(list);
		System.out.println(r);
		//commit();
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
