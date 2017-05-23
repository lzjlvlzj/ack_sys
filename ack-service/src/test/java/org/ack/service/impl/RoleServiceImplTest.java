package org.ack.service.impl;

import static org.junit.Assert.fail;

import java.util.Set;

import org.ack.pojo.Menu;
import org.ack.pojo.Permission;
import org.ack.pojo.Role;
import org.ack.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:serviceSpringContext.xml")
public class RoleServiceImplTest {
	
	@Autowired
	private RoleService roleServiceImpl;
	
	
	@Test
	public void testFindMenusByRole(){
		Role role = new Role();
		role.setId(1);
		
		Set<Menu> set = roleServiceImpl.findMenusByRole(role);
		for(Menu m : set){
			System.out.println(m);
		}
	}

	@Test
	public void testFindPermissoinsByRole() {
		Role role = new Role();
		role.setId(1);
		Set<Permission> permissions = roleServiceImpl.findPermissoinsByRole(role);
		for(Permission p : permissions){
			System.out.println(p);
		}
		
		
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByIds() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteByIds() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAll() {
		fail("Not yet implemented");
	}

}
