package org.ack.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.ack.common.tree.Tree;
import org.ack.pojo.Menu;
import org.ack.pojo.Permission;
import org.ack.pojo.Role;
import org.ack.pojo.User;
import org.ack.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:serviceSpringContext.xml")
public class UserServiceImplTest {
	
	@Autowired
	UserService userServiceImpl;
	
	public final ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testFindMenuTreeByUser() throws JsonProcessingException{
		User user = userServiceImpl.findUserByLoginName("admin");
		Tree tree = userServiceImpl.findMenuTreeByUser(user);
		String s = mapper.writeValueAsString(tree);
		
		System.out.println(s);
	}
	@Test
	public void testFindMenuByUser() throws JsonProcessingException{
		User user = userServiceImpl.findUserByLoginName("admin");
		List<Menu> menus = userServiceImpl.findMenuByUser(user);
		String s = mapper.writeValueAsString(menus);
		
		System.out.println(s);
	}
	@Test
	public void testFindAuthByUser(){
		User user = userServiceImpl.findUserByLoginName("lisi");
		List<Permission> permissions = userServiceImpl.findAuthByUser(user);
		for(Permission p : permissions){
			System.out.println(p);	
		}
	}

	@Test
	public void testFindUserByLoginName() {
		User user = userServiceImpl.findUserByLoginName("zhangsan");
		for(Role role : user.getRoles()){
			System.out.println(role);
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
