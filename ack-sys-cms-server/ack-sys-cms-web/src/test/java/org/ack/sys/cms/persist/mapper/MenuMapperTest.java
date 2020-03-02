package org.ack.sys.cms.persist.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;

import org.ack.sys.cms.persist.BaseTest;
import org.ack.sys.cms.pojo.Menu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuMapperTest extends BaseTest {

	MenuMapper menuMapper;
	@BeforeEach
	public void init() {
		menuMapper = sqlSession.getMapper(MenuMapper.class);
	}
	
	@AfterEach
	void after() {
		close();
	}
	
	@Test
	public void testInitTestData() {
		Date date = new Date();
		Long userId = 2L;
		Menu menu = menuMapper.findById(1L);
		Menu userMenu = new Menu();
		userMenu.setName("用戶管理");
		userMenu.setIcon("el-icon-service");
		userMenu.setCreator(userId);
		userMenu.setCreateTime(date);
		userMenu.setParentId(menu.getId());
		userMenu.setLevel(1);
		userMenu.setOrderNum(1);
		userMenu.setModifier(userId);
		userMenu.setModifyTime(date);
		userMenu.setType(0);
		
		Menu roleMenu = new Menu();
		roleMenu.setName("用戶管理");
		roleMenu.setIcon("el-icon-service");
		roleMenu.setCreator(userId);
		roleMenu.setCreateTime(date);
		roleMenu.setParentId(1L);
		roleMenu.setLevel(1);
		roleMenu.setOrderNum(1);
		roleMenu.setModifier(userId);
		roleMenu.setModifyTime(date);
		roleMenu.setType(0);
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
		Long userId = 2L;
		
		Menu menu = new Menu();
		menu.setName("菜单删除");
		menu.setIcon("el-icon-service");
		menu.setCreator(userId);
		menu.setCreateTime(date);
		menu.setParentId(8L);
		menu.setLevel(1);
		menu.setOrderNum(1);
		menu.setModifier(userId);
		menu.setModifyTime(date);
		menu.setType(2);
		menu.setUrl("");
		menu.setPerms("sys:role:delete");
		
		//Menu userMenu = new Menu();
		
		int r = menuMapper.insert(menu);
		System.out.println(r);
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
