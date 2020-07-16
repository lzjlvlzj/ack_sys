package org.ack.sys.cms.service.impl;

import java.util.Arrays;
import java.util.List;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.persist.mapper.RoleMapper;
import org.ack.sys.pojo.Menu;
import org.ack.sys.pojo.Role;
import org.ack.sys.pojo.RoleMenu;
import org.ack.sys.pojo.User;
import org.ack.sys.cms.service.MenuService;
import org.ack.sys.cms.service.RoleMenuService;
import org.ack.sys.cms.service.RoleService;
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
public class RoleServiceImpl extends PageServiceImpl<Role, Long> implements RoleService {

	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private MenuService menuServiceImpl;
	@Autowired
	private RoleMenuService roleMenuServiceImpl;

	@Override
	protected PageDao<Role, Long> getPageDao() {
		logger.debug("mapper is {}", roleMapper);
		return roleMapper;
	}

	@Override
	public List<Role> findByUserId(Long id) {
		return roleMapper.findByUserId(id);
	}

	@Override
	@Transactional
	public int insert(Role t) {
		Role role = findRoleByName(t.getName());
		if (null != role) {
			logger.debug("角色:{}已存在", t.getName());
			return -1;
		}
		t.setDeleteStatus(0);
		return super.insert(t);
	}

	@Override
	@Transactional
	public int update(Role t) {
		Role role = findRoleByName(t.getName());
		if (null != role) {
			if (role.getId() != t.getId()) {
				logger.debug("角色:{}已存在", t.getName());
				return -1;
			}
		}
		return super.update(t);
	}

	@Override
	@Transactional
	public int batchDelete(List<Role> list) {
		int size = list.size();
		int r = 0;
		for (int i = 0; i < size; i++) {
			Role role = list.get(i);
			role.setDeleteStatus(1);
			int rt = update(role);
			r = r + rt;
		}
		logger.debug("需要修改的数据为{}条,实际修改{}条", size, r);
		return r;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Role findRoleByName(String name) {
		return roleMapper.findRoleByName(name);
	}

	@Override
	public List<Menu> findMenuByRoleId(Long roleId) {
		logger.debug("roleid = {}", roleId);
		return menuServiceImpl.findByRoleId(roleId);
	}

	@Override
	@Transactional
	public Integer saveRoleMenus(Role role) {
		int r = -1;
		// 删除原来权限对应的菜单
		int delCount = roleMenuServiceImpl.deleteByRoleId(role.getId());
		logger.debug("删除{}条记录", delCount);
		int incCount = 0;
		// 插入新的
		for (RoleMenu roleMenu : role.getRoleMenus()) {
			// 插入新的
			incCount = roleMenuServiceImpl.insert(roleMenu);
			r += incCount;
		}
		logger.debug("新增{}条记录", incCount);
		return r;
	}

	public int getMinWeight(List<Role> roleList) {
		int size = roleList.size();
		int[] tmp = new int[size];
		for (int i = 0; i < size; i++) {
			Role role = roleList.get(i);
			int weight = role.getWeight();
			tmp[i] = weight;
		}
		Arrays.sort(tmp);
		int min = tmp[0];
		return min;
	}

	@Override
	public List<Role> findRoleListByUser(User user) {
		/*
		 * 查询当前用户的角色, 找出角色权重最小的值
		 */
		List<Role> roleList = findByUserId(user.getId());
		int minWeight = getMinWeight(roleList);
		logger.debug("当前用户{}最小权重值为{}", user.getUsername(), minWeight);
		return roleMapper.findRoleByWeight(minWeight);
	}

}
