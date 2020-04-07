package org.ack.sys.persist.mapper;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.pojo.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMenuMapper extends PageDao<RoleMenu, Long> {

	/**根据roleId删除
	 * @param roleId
	 * @return
	 */
	public Integer deleteByRoleId(Long roleId);
}