package org.ack.persist.mapper;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.Set;

import org.ack.pojo.Permission;
import org.ack.pojo.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoleMapperTest extends BaseTest {
	
	RoleMapper roleMapper;
	
	@Before
	public void init() {
		roleMapper = sqlSession.getMapper(RoleMapper.class);
	}
	
	@Test
	public void testFindPermissionById(){
		Set<Permission> permissions = roleMapper.findPermissionById(1);
		
		for(Permission p : permissions){
			System.out.println(p);
		}
	}

	@Test
	public void testFindInterceptorPageList() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByIds() {
		String[] ids = {"2","7"};
		Set<Role> roles = roleMapper.findByIds(ids);
		for(Role r : roles){
			System.out.println(r);
		}
	}
	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		Role role = new Role();
		role.setRoleName("超级管理员");
		role.setComments("用户系统管理权限没有业务权限");
		role.setCreateTime(new Date());
		
		int r = roleMapper.insert(role);
		sqlSession.commit();
		close();
		Assert.assertEquals(1, r);
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
