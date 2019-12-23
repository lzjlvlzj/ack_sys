package org.ack.sys.cms.persist.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.ack.sys.cms.persist.BaseTest;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.util.MD5Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserMapperTest extends BaseTest{
	UserMapper userMapper;
	
	@BeforeEach
	public void init() {
		userMapper = sqlSession.getMapper(UserMapper.class);
	}
	
	@AfterEach
	void after() {
		close();
	}
	
	
	@Test
	void testFindUserByUserName() {
		String username = "ack";
	    User user = userMapper.findUserByUserName(username);
	    System.out.println(user);
	    assertNotNull(user);
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
		User user = new User();
		user.setId(2);
		user = userMapper.find(user);
		System.out.println(user);
		assertNotNull(user);
	}

	@Test
	void testInsert() {
		User user = new User();
		user.setUsername("ack1");
		String pass = "123";
		String password = MD5Util.md5(pass);
		user.setPassword(password);
		user.setEmail("1111222@qq.com");
		user.setMobile("15601232245");
		user.setQq("1236547");
		user.setRealName("张三");
		user.setType(0);
		user.setAvatar("/aaa/b.png");
		user.setCreateTime(new Date());
		
		Integer r  = userMapper.insert(user);
		System.out.println("r = " + r);
		sqlSession.commit();
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
