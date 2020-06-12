package org.ack.sys.cms.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.ack.sys.cms.service.portal.PortalMenuService;
import org.ack.sys.pojo.PortalMenu;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class PortalMenuServiceImplTest {
	
	@Autowired
	private PortalMenuService portalMenuServiceImpl;

	@Test
	void testFindSortedMenu() {
		PortalMenu portalMenu = portalMenuServiceImpl.findSortedMenu();
		assertNotNull(portalMenu);
		List<PortalMenu> list = portalMenu.getChildren();
		int size = list.size();
		System.out.println(size);
	
	}

	@Test
	void testFindPagePageOfT() {
		fail("Not yet implemented");
	}

	@Test
	void testFindPagePageRequest() {
		fail("Not yet implemented");
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
