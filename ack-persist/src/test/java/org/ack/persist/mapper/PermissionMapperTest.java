package org.ack.persist.mapper;

import static org.junit.Assert.fail;

import java.util.Set;

import org.ack.pojo.Menu;
import org.junit.Before;
import org.junit.Test;

public class PermissionMapperTest extends BaseTest {

	PermissionMapper permissionMapper;

	@Before
	public void init() {
		permissionMapper = sqlSession.getMapper(PermissionMapper.class);
	}

	@Test
	public void testFindMenuById() {
		Set<Menu> menus = permissionMapper.findMenuById(1);
		for (Menu menu : menus) {
			System.out.println(menu);
		}
	}

	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
