package org.ack.service;

import java.util.Set;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.Menu;

/**
 * 菜单业务接口
 * 
 * @author ack
 *
 */
public interface MenuService extends AckMapperService<Menu, Integer> {

	/**
	 * @param ids
	 * @return
	 */
	public Set<Menu> findByIds(String[] ids);

	/**
	 * 根据pid查找集合
	 * 
	 * @param pid
	 * @return
	 */
	public Set<Menu> findMenuByPId(Integer pid);

}
