package org.ack.service;

import java.util.Set;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.Menu;
import org.ack.pojo.Permission;

/**
 * 权限接口
 * 
 * @author ack
 *
 */
public interface PermissionService extends
		AckMapperService<Permission, Integer> {

	/**
	 * 根据id查询菜单
	 * 
	 * @param id
	 * @return
	 */
	public Set<Menu> findMenusById(Integer id);

}
