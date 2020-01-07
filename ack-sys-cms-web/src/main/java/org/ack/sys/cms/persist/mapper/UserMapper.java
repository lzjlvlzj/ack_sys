package org.ack.sys.cms.persist.mapper;

import java.util.Map;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.cms.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用戶接口
 * 
 * @author ack
 *
 */
@Mapper
public interface UserMapper extends PageDao<User, Long> {

	/**
	 * 根据用户登陆名称查询用户信息
	 * 
	 * @param username
	 * @return
	 */
	public User findUserByUserName(String username);

	/**
	 * 根据用户登陆名称查询用户信息
	 * 
	 * @param email
	 * @return
	 */
	public User findUserByEmail(String email);

	/**
	 * 根据用户登陆名称查询用户信息
	 * 
	 * @param qq
	 * @return
	 */
	public User findUserByQq(String qq);

	/**
	 * 根据用户登陆名称查询用户信息
	 * 
	 * @param mobile
	 * @return
	 */
	public User findUserByMobile(String mobile);

	/**
	 * 根据用户名,邮箱,qq,手机查询用户
	 * 
	 * @param map
	 * @return
	 */
	public User findBySelected(Map<String, String> map);

}
