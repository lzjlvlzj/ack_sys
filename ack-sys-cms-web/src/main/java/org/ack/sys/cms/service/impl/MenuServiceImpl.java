package org.ack.sys.cms.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.cms.persist.mapper.MenuMapper;
import org.ack.sys.cms.pojo.Menu;
import org.ack.sys.cms.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用戶邏輯
 * 
 * @author ack
 *
 */
@Service
public class MenuServiceImpl extends PageServiceImpl<Menu, Long> implements MenuService {
	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	@Autowired
	private MenuMapper menuMapper;

	@Override
	protected PageDao<Menu, Long> getPageDao() {
		logger.debug("mapper is： {}", menuMapper);
		return menuMapper;
	}

	@Override
	@Transactional
	public int insert(Menu t) {
		Menu dbMenu = findByName(t.getName());
		if (null != dbMenu) {
			logger.debug("菜单:{}已经存在", t.getName());
			return -1;
		}
		return super.insert(t);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Menu findByName(String name) {
		return menuMapper.findByName(name);
	}

	@Override
	@Transactional
	public int batchDelete(List<Menu> list) {
		int size = list.size();
		int r = 0;
		for (int i = 0; i < size; i++) {
			Menu menu = list.get(i);
			logger.debug("用户id : {}", menu.getId());
			menu.setDeleteStatus(1);
			int rt = update(menu);
			r = r + rt;
		}
		logger.debug("需要修改的数据为{}条,实际修改{}条", size, r);
		return r;
	}

	private Menu getSortedNavMenu(List<Menu> list, Menu parent) {
		List<Menu> children = new ArrayList<Menu>();
		for (Menu menu : list) {
			if (parent.getId() == menu.getParentId()) {
				if (menu.getType() != 2L) {
					menu.setParentName(parent.getName());
					children.add(menu);
					getSortedNavMenu(list, menu);
				}
			}
		}
		parent.setChildren(children);
		return parent;
	}

	private Menu getSortedAllMenu(List<Menu> list, Menu parent) {
		List<Menu> children = new ArrayList<Menu>();
		for (Menu menu : list) {
			if (parent.getId() == menu.getParentId()) {
				menu.setParentName(parent.getName());
				children.add(menu);
				getSortedAllMenu(list, menu);
			}
		}
		parent.setChildren(children);
		return parent;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Menu> findNoReapetListByUserId(Long id) {
		List<Menu> list = menuMapper.findByUserId(id);
		// 去重
		Set<Menu> set = new HashSet<Menu>();
		for (Menu menu : list) {
			set.add(menu);
		}
		List<Menu> result = new ArrayList<Menu>(set);
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Menu> findByUserId(Long id) {
		List<Menu> result = findNoReapetListByUserId(id);
		Menu root = new Menu();
		root.setId(0L);
		List<Menu> sortedList = getSortedNavMenu(result, root).getChildren();
		return sortedList;
	}

	@Override
	public List<Menu> findTree() {
		List<Menu> result = findAll();
		Menu root = new Menu();
		root.setId(0L);
		List<Menu> sortedList = getSortedAllMenu(result, root).getChildren();
		return sortedList;
	}

	@Override
	public List<Menu> findByRoleId(Long roleId) {
		
		return menuMapper.findByRoleId(roleId);
	}

}
