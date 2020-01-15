package org.ack.sys.cms.persist.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ack.sys.base.persist.page.Page;
import org.ack.sys.base.util.MD5Util;
import org.ack.sys.cms.persist.BaseTest;
import org.ack.sys.cms.pojo.Department;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.pojo.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserMapperTest extends BaseTest {
	UserMapper userMapper;
	UserRoleMapper userRoleMapper;

	@BeforeEach
	public void init() {
		userMapper = sqlSession.getMapper(UserMapper.class);
		userRoleMapper = sqlSession.getMapper(UserRoleMapper.class);
	}

	@AfterEach
	void after() {
		close();
	}

	@Test
	void testInitData() {
		Long cid = 2L;
		Date date = new Date();
		for (int i = 0; i < 17; i++) {
			User user = new User();
			user.setUsername("ack_"+i);
			String pass = "123";
			String password = MD5Util.md5(pass);
			user.setPassword(password);
			user.setEmail("22"+i+"@qq.com");
			user.setMobile("15801232"+i);
			user.setQq("1236547");
			user.setRealName("张三2");
			user.setType(0);
			user.setAvatar("/aaa/b.png");
			user.setCreator(cid);
			user.setCreateTime(date);
			user.setModifier(cid);
			user.setModifyTime(date);
			user.setDepartmentId(2L);

			UserRole ur = new UserRole();
			ur.setUserId(2L);
			Integer r = userMapper.insert(user);
			System.out.println("r = " + r);
			//sqlSession.commit();
			System.out.println(user.getId());
			assertEquals(1, r);
		}
		
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
		Page<User> page = new Page<>();
		Map<String, Object> map = new HashMap<>();
		map.put("username", "admin");
		page.setCondition(map);
		List<User> list = userMapper.findInterceptorPageList(page);
		assertNotNull(list);
		for (User user : list) {
			Department dept = user.getDepartment();
			System.out.println(dept);
			List<UserRole> url = user.getUserRoles();
			System.out.println(url.size());
		}
	}

	@Test
	void testFind() {
		User user = new User();
		user.setId(2L);
		user = userMapper.find(user);
		System.out.println(user);
		assertNotNull(user);
	}

	@Test
	void testInsert() {
		Long cid = 2L;
		Date date = new Date();

		User user = new User();
		user.setUsername("ack1");
		String pass = "123";
		String password = MD5Util.md5(pass);
		user.setPassword(password);
		user.setEmail("223112@qq.com");
		user.setMobile("15801232246");
		user.setQq("1236547");
		user.setRealName("张三2");
		user.setType(0);
		user.setAvatar("/aaa/b.png");
		user.setCreator(cid);
		user.setCreateTime(date);
		user.setModifier(cid);
		user.setModifyTime(date);
		user.setDepartmentId(2L);

		UserRole ur = new UserRole();
		ur.setUserId(2L);
		Integer r = userMapper.insert(user);
		System.out.println("r = " + r);
		sqlSession.commit();
		System.out.println(user.getId());
		assertEquals(1, r);
	}

	@Test
	void testUpdate() {
		User user = new User();
		user.setId(4L);
		user.setRealName("疙瘩汤");
		int r = userMapper.update(user);
		System.out.println(r);
		commit();
		assertEquals(1, r);
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
		long id = 2;
		User user = userMapper.findById(id);
		assertNotNull(user);
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
