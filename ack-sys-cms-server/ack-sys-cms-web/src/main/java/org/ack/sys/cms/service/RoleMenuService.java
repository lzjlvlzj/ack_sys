package org.ack.sys.cms.service;

import java.util.List;

import org.ack.sys.base.service.BaseService;
import org.ack.sys.cms.pojo.RoleMenu;

public interface RoleMenuService extends BaseService<RoleMenu, Long> {

	/**
	 * 批量保存
	 * 
	 * @param roleMenus
	 * @return
	 */
	public Integer save(List<RoleMenu> roleMenus);

	/**
	 * 根据roleid删除
	 * 
	 * @param roleId
	 */
	public Integer deleteByRoleId(Long roleId);

}
