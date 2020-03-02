package org.ack.sys.cms.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.ack.sys.cms.pojo.Menu;
import org.ack.sys.cms.service.MenuService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class MenuServiceImplTest {
	
	@Autowired
	MenuService menuServiceImpl;

	@Test
	void testFindMenuByUser() {
		Long id = 2L;
		List<Menu> menu = menuServiceImpl.findByUserId(id);
		assertNotNull(menu);
	}

	@Test
	void testFindPage() {
		fail("Not yet implemented");
	}

	@Test
	void testFindById() {
		long id = 6L;
		Menu menu = menuServiceImpl.findById(id);
		System.out.println(menu);
		assertNotNull(menu);
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

}
