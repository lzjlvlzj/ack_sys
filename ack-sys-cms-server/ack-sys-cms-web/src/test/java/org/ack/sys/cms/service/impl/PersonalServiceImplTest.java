package org.ack.sys.cms.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.service.PersonalService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class PersonalServiceImplTest {
	
	@Autowired
	PersonalService personalServiceImpl;
	
	@Test
	void testChangePwd() {
		User user = new User();
		user.setPassword("123");
		user.setNewPassword("123456");
		user.setUsername("admin");
		int r = personalServiceImpl.changePwd(user);
		assertEquals(1, r);
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
	void testInsert() {
		fail("Not yet implemented");
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

	@Test
	void testBatchUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testBatchDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

}
