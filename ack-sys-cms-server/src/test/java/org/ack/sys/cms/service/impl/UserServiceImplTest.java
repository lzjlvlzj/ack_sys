package org.ack.sys.cms.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ack.sys.base.persist.page.ColumnFilter;
import org.ack.sys.base.persist.page.Page;
import org.ack.sys.base.persist.page.PageRequest;
import org.ack.sys.base.util.MD5Util;
import org.ack.sys.pojo.Role;
import org.ack.sys.pojo.User;
import org.ack.sys.cms.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {
	
	@Autowired
	private UserService userServiceImpl;
	
	

	@Test
	void testFindUserByUserName() {
		fail("Not yet implemented");
	}

	@Test
	void testFindUserPermissions() {
		fail("Not yet implemented");
	}

	@Test
	void testFindPage() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username","zz");
		Page<User> page = new Page<User>();
		page.setCondition(map);
		
		page = userServiceImpl.findPage(page);
		List<User> list = page.getResult();
		for(User user : list) {
			System.out.println(user);
		}
	}

	@Test
	void testFindPagePageRequest() {
		PageRequest request = new PageRequest();
		Map<String,ColumnFilter> map = new HashMap<String,ColumnFilter>();
		ColumnFilter cf = new ColumnFilter("username","admin");
		map.put("username",cf);
		request.setColumnFilters(map);
		
		Page<User> page = userServiceImpl.findPage(request);
		List<User> list = page.getResult();
		for(User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
    void testFindUserRoles() {
    	Long id = 2L;
    	List<Role> list = userServiceImpl.findUserRoles(id);
    	assertNotNull(list);
    	for(Role role : list) {
    		System.out.println(role);
    	}
    }
	
	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testFindByIds() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}
	@Test
	void testBatchDelete() {
		List<User> list = new ArrayList<User>();
		
		User user = new User();
		user.setId(15L);
		user.setDeleteStatus(1);
		
		User user1 = new User();
		user1.setId(16L);
		user1.setDeleteStatus(1);
		
		list.add(user);
		list.add(user1);
		
		int r = userServiceImpl.batchDelete(list);
		System.out.println(r);
		assertEquals(2, r);
	}

	@Test
	void testInsert() {
		Date date = new Date();
		Long id = 2L;
		Long deptId = 3L;
		
		User user = new User();
		user.setUsername("ack2");
		String password = MD5Util.md5("123");
		user.setPassword(password);
		user.setEmail("1215545@qq.com");
		user.setMobile("15614541124");
		user.setAvatar("/b/a.png");
		user.setAddress("朝阳");
		user.setDeleteStatus(0);
		user.setModifier(id);
		user.setModifyTime(date);
		user.setCreator(id);
		user.setCreateTime(date);
		user.setDepartmentId(deptId);
		
		int r = userServiceImpl.insert(user);
		assertEquals(1, r);
		
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteById() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteByIds() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteAll() {
		fail("Not yet implemented");
	}

}
