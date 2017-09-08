package org.ack.persist.mapper;

import java.util.Set;

import org.ack.persist.AckMapper;
import org.ack.pojo.Permission;
import org.ack.pojo.Role;

/**
 * 角色
 * 
 * @author ack
 *
 */
public interface RoleMapper extends AckMapper<Role, Integer> {

	/**
	 * 根据角色查询权限
	 * 
	 * @param i
	 * @return
	 */
	public Set<Permission> findPermissionById(Integer id);

	/**
	 * 关联查询
	 * 
	 * @param i
	 * @return
	 */
	public Role findRolePermissionById(Integer id);

	/**
	 * 根据id查询
	 * 
	 * @param roleIds
	 * @return
	 */
	public Set<Role> findByIds(String[] ids);

	/** 
	 * @param projectManage
	 * @return
	 */
	public Role findByAbbreviation(String projectManage);

}
