package org.ack.sys.cms.service;

import java.util.List;

import org.ack.sys.base.service.PageService;
import org.ack.sys.cms.pojo.Menu;

/**
 * 用户接口
 * 
 * @author ack
 *
 */
public interface MenuService extends PageService<Menu, Long> {

	/**
	 * 根據用户登陆名称查询用户信息
	 * 
	 * @param Menuname
	 * @return Menu
	 */
	public List<Menu> findByUserId(Long id);

	/**
	 * 根据用户id查询没有重复的菜单
	 * 
	 * @param id
	 * @return
	 */
	public List<Menu> findNoReapetListByUserId(Long id);

	/**
	 * 根据名称查询菜单
	 * 
	 * @param name
	 * @return
	 */
	public Menu findByName(String name);

	/**
	 * find tree
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public List<Menu> findTree();

	/**
	 * 根据角色查询菜单
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Menu> findByRoleId(Long roleId);

}
