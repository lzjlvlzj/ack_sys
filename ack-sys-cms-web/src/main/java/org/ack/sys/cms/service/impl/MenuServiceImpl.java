package org.ack.sys.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.cms.persist.mapper.MenuMapper;
import org.ack.sys.cms.pojo.Menu;
import org.ack.sys.cms.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**用戶邏輯
 * @author ack
 *
 */
@Service
public class MenuServiceImpl extends PageServiceImpl<Menu, Integer> implements MenuService {
	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	@Autowired
	private MenuMapper menuMapper;

	@Override
	protected PageDao<Menu, Integer> getPageDao() {
		logger.debug("mapper is： {}", menuMapper);
		return menuMapper;
	}

	@Override
	public List<Menu> findMenuByUser(String username) {
		List<Menu> list = new ArrayList<Menu>();
		List<Menu> subMenu = new ArrayList<Menu>();
		Menu sysMenu = menuMapper.findById(6);
		
		Menu userMenu = menuMapper.findById(7);
		Menu roleMenu = menuMapper.findById(8);
		subMenu.add(userMenu);
		subMenu.add(roleMenu);
		
		sysMenu.setChildren(subMenu);
		list.add(sysMenu);
		return list;
	}

	



	

}
