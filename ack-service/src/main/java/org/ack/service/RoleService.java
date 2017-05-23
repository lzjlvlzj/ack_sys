package org.ack.service;

import java.util.Set;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.Menu;
import org.ack.pojo.Permission;
import org.ack.pojo.Role;

/**
 * 角色接口
 * 
 * @author ack
 *
 */
public interface RoleService extends AckMapperService<Role, Integer> {

	/**
	 * 根据角色查询权限
	 * 
	 * @return
	 */
	public Set<Permission> findPermissoinsByRole(Role role);

	/**
	 * @param roleIds
	 * @return
	 */
	public Set<Role> findByIds(String[] roleIds);
 
	/**
	 * @param role
	 * @return
	 */
	public Set<Menu> findMenusByRole(Role role);
	
}
