package org.ack.persist.mapper;

import org.ack.pojo.Role;
import org.ack.pojo.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.fail;

/**
 * @author ack
 *
 */
public class UserMapperTest extends BaseTest {

	UserMapper userMapper;

	@Before
	public void init() {
		userMapper = sqlSession.getMapper(UserMapper.class);
	}

	@Test
	public void findUserByRoleId(){
		String id = "7";
		List<User> list = userMapper.findUserByRoleId(id);
		for(User user : list){
			System.out.println(user);
		}
	}

	
	@Test
	public void testInitData() {
		User user = null;
		for(int i = 0 ; i < 35; i++){
			user = new User();
			user.setLoginName("zhangsan" + i);
			user.setPassword("123");
			user.setSurname("张" + i);
			user.setName("三" + i);
			user.setCreateTime(new Date());
			userMapper.insert(user);
			//sqlSession.commit();
		}
		close();

	}
	
	@Test
	public void testFindUserRoleById(){
		Set<Role> roles = userMapper.findRoleById(5L);
		for(Role role : roles){
			System.out.println(role);
		}
	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindPageList() {
		fail("Not yet implemented");
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
	public void testDeleteByIds() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		User t = userMapper.findById(5L);
		Assert.assertNotNull(t);
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testFindUserByLoginName() {
		String loginName = "zhangsan";
		User user = userMapper.findUserByLoginName(loginName);
		Assert.assertNotNull(user);
	}

	@Test
	public void testFindByIds() {

	}

	@Test
	public void testFind() {
		User user = new User();
		user.setId(5L);
		User t = userMapper.find(user);
		Assert.assertNotNull(t);
	}

	@Test
	public void testInsert() {
		User user = new User();
		user.setLoginName("zhangsan");
		user.setPassword("123");
		user.setSurname("张");
		user.setName("三");
		user.setCreateTime(new Date());

		Integer r = userMapper.insert(user);
		sqlSession.commit();
		close();
		Assert.assertEquals(1, r.intValue());

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
