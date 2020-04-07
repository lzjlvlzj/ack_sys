package org.ack.sys.persist.mapper;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends PageDao<Menu, Long> {

	/**
	 * 根据用户id查询菜单
	 * 
	 * @param id
	 * @return
	 */
	public List<Menu> findByUserId(Long id);

	/**
	 * 根据名称查询菜单
	 * 
	 * @param name
	 * @return
	 */
	public Menu findByName(String name);

	/**
	 * @param roleId
	 * @return
	 */
	public List<Menu> findByRoleId(Long roleId);

}
