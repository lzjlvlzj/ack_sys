package org.ack.sys.cms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.sys.base.service.PageService;
import org.ack.sys.cms.pojo.Role;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.web.template.LoginUser;

/**
 * 用户接口
 * 
 * @author ack
 *
 */
public interface UserService extends PageService<User, Long> {

	/**
	 * 根據用户登陆名称查询用户信息
	 * 
	 * @param email
	 * @return User
	 */
	public User findUserByEmail(String email);

	/**
	 * 根據用户登陆名称查询用户信息
	 * 
	 * @param qq
	 * @return User
	 */
	public User findUserByQq(String qq);

	/**
	 * 根據用户邮箱名称查询用户信息
	 * 
	 * @param mobile
	 * @return User
	 */
	public User findUserByMobile(String mobile);

	/**
	 * 根據用户手机名称查询用户信息
	 * 
	 * @param username
	 * @return User
	 */
	public User findUserByUserName(String username);

	/**
	 * 查询当前用户的权限字符串
	 * 
	 * @param username
	 * @return
	 */
	public List<String> findUserPermissions(Long id);

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @param token
	 * @return
	 */
	public int login(HttpServletRequest request, HttpServletResponse response, LoginUser user, String token);

	/**
	 * 根据用户id查询角色
	 * 
	 * @param id
	 * @return
	 */
	public List<Role> findUserRoles(Long id);

}
