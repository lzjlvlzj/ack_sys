package org.ack.sys.cms.service.impl;

import java.util.List;

import org.ack.sys.base.persist.BaseDao;
import org.ack.sys.base.service.impl.BaseServiceImpl;
import org.ack.sys.cms.persist.mapper.RoleMenuMapper;
import org.ack.sys.cms.pojo.RoleMenu;
import org.ack.sys.cms.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenu, Long> implements RoleMenuService {
	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Override
	public BaseDao<RoleMenu, Long> getDao() {
		return roleMenuMapper;
	}

	@Override
	public Integer save(List<RoleMenu> roleMenus) {
		if (null == roleMenus || 0 == roleMenus.size()) {
			return -1;
		}
		int r = 0;
		for (RoleMenu rm : roleMenus) {
			int rt = save(rm);
			r += rt;
		}
		return r;
	}

	@Override
	public Integer deleteByRoleId(Long roleId) {
		return roleMenuMapper.deleteByRoleId(roleId);
	}

}
