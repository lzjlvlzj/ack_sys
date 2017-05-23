package org.ack.persist.mapper;

import java.util.Set;

import org.ack.persist.AckMapper;
import org.ack.pojo.Menu;

/**
 * 菜单
 * 
 * @author ack
 *
 */
public interface MenuMapper extends AckMapper<Menu, Integer> {

	/**
	 * 根据id集合查询菜单
	 * 
	 * @param ids
	 * @return
	 */
	public Set<Menu> findByIds(String[] ids);

	/**
	 * 根据pid查询集合
	 * 
	 * @param pid
	 * @return
	 */
	public Set<Menu> findMenuByPId(Integer pid);

}
