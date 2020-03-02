package org.ack.sys.cms.service;

import java.util.List;

import org.ack.sys.base.service.PageService;
import org.ack.sys.cms.pojo.Menu;
import org.ack.sys.cms.pojo.Role;
import org.ack.sys.cms.pojo.User;

public interface RoleService extends PageService<Role, Long> {
	/**
	 * 根据名称查询角色
	 * 
	 * @param name
	 * @return Role
	 */
	public Role findRoleByName(String name);

	/**
	 * 根据角色查询菜单
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Menu> findMenuByRoleId(Long roleId);

	/**
	 * 更新角色权限菜单
	 * 
	 * @param role
	 * @return
	 */
	public Integer saveRoleMenus(Role role);

	/**
	 * 根据用户id查询角色
	 * 
	 * @param id
	 * @return
	 */
	public List<Role> findByUserId(Long id);

	/**查询比当前用户权限小的所有角色
	 * @param user
	 * @return
	 */
	public List<Role> findRoleListByUser(User user);
	
	/**
	 * @param roleList
	 * @return
	 */
	public int getMinWeight(List<Role> roleList);
}
