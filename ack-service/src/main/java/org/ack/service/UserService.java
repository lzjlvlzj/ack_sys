package org.ack.service;

import org.ack.base.service.AckMapperService;
import org.ack.common.tree.Tree;
import org.ack.pojo.Menu;
import org.ack.pojo.Permission;
import org.ack.pojo.Role;
import org.ack.pojo.User;

import java.util.List;
import java.util.Set;

/**
 * 用户业务逻辑封装
 * 
 * @author ack
 *
 */
public interface UserService extends AckMapperService<User, Long> {

	/**
	 * 根据登录查询用户
	 * 
	 * @param loginName
	 * @return
	 */
	public User findUserByLoginName(String loginName);

	/**
	 * 根据id查询权限
	 * 
	 * @return
	 */
	public List<Permission> findAuthByUser(User user);

	/**
	 * 根据id查询菜单
	 * 
	 * @return
	 */
	public List<Menu> findMenuByUser(User user);
	
	/**
	 * 根据id查询菜单
	 * 
	 * @return
	 */
	public Tree findMenuTreeByUser(User user);

	/**
	 * 查询所有角色
	 * 
	 * @return
	 */
	public List<Role> findAllRoles();

	/**
	 * 查询用户的角色信息
	 * 
	 * @param user
	 * @return
	 */
	public Set<Role> findRolesByUser(User user);

	/**
	 * 更新用户角色
	 * 
	 * @param user
	 * @param rid
	 * @return
	 */
	public int updateRoleByUser(User user, Integer[] rid);

	/**
	 * 获得用户权限字符串
	 * */
	public Set<String> getPermissionString(User user);

	/**
	 *
	 * @param user
	 * @return
	 */
	public List<Role> findRoleList(User user);

	/**
	 * 查询角色对应的用户
	 * @param roleId
	 * @return
	 */
	Set<User> findUserByRoleId(int roleId);

}
