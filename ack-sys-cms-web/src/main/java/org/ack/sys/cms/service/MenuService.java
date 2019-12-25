package org.ack.sys.cms.service;

import java.util.List;

import org.ack.sys.base.service.PageService;
import org.ack.sys.cms.pojo.Menu;

/**用户接口
 * @author ack
 *
 */
public interface MenuService extends PageService<Menu, Long>{

	/**根據用户登陆名称查询用户信息
	 * @param Menuname
	 * @return Menu
	 */
	public List<Menu> findMenuByUser(String username);

}
