package org.ack.sys.cms.persist.mapper;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.cms.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends PageDao<Role, Long>{

	/** 根据名称查询角色
	 * @param name
	 * @return Role
	 */
	public Role findRoleByName(String name);

}
