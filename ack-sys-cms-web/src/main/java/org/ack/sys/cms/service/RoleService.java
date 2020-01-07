package org.ack.sys.cms.service;

import org.ack.sys.base.service.PageService;
import org.ack.sys.cms.pojo.Role;

public interface RoleService extends PageService<Role, Long> {
	/**根据名称查询角色
	 * @param name
	 * @return Role
	 */
	public Role findRoleByName (String name);
}
