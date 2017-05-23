package org.ack.persist.mapper;

import java.util.Set;

import org.ack.persist.AckMapper;
import org.ack.pojo.Menu;
import org.ack.pojo.Permission;

/**
 * 权限mapper
 * 
 * @author ack
 *
 */
public interface PermissionMapper extends AckMapper<Permission, Integer> {

	/**
	 * 查询权限
	 * 
	 * @param id
	 * @return
	 */
	public Set<Menu> findMenuById(Integer id);

	/**
	 * 关联查询
	 * 
	 * @param id
	 * @return
	 */
	public Permission findPermissionMenuById(Integer id);

}
