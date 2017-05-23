package org.ack.service.impl;

import static org.junit.Assert.fail;

import java.util.Set;

import org.ack.pojo.Menu;
import org.ack.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:serviceSpringContext.xml")
public class PermissionServiceImplTest {

	@Autowired
	private PermissionService permissionServiceImpl;
	
	@Test
	public void testFindMenusById() {
		Set<Menu> set = permissionServiceImpl.findMenusById(1);
		for(Menu m : set){
			System.out.println(m);
		}
	}
	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

}
