package org.ack.sys.persist.mapper;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper extends PageDao<Role, Long> {

	/**
	 * 根据名称查询角色
	 * 
	 * @param name
	 * @return Role
	 */
	public Role findRoleByName(String name);

	/**
	 * 根据用户id查询角色
	 * 
	 * @param id
	 * @return
	 */
	public List<Role> findByUserId(Long id);

	/**
	 * 根据权重查询角色
	 * 
	 * @param minWeight
	 * @return
	 */
	public List<Role> findRoleByWeight(int minWeight);

}
