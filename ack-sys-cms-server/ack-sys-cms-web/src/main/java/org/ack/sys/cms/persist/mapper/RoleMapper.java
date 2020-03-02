package org.ack.sys.cms.persist.mapper;

import java.util.List;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.cms.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

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
