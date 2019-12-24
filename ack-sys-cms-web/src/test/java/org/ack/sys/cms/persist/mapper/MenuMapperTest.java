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
		Integer userId = 2;
		
		Menu menu = new Menu();
		menu.setName("系统管理");
		menu.setIcon("el-icon-setting");
		menu.setCreator(userId);
		menu.setCreateTime(date);
		menu.setParentId(0);
		menu.setLevel(1);
		menu.setOrderNum(1);
		menu.setModifier(userId);
		menu.setUpdateTime(date);
		menu.setType(0);
		
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
