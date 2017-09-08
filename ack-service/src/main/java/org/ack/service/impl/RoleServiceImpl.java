package org.ack.service.impl;

import java.util.Set;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.common.Content;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.RoleMapper;
import org.ack.pojo.Menu;
import org.ack.pojo.Permission;
import org.ack.pojo.Role;
import org.ack.service.MenuService;
import org.ack.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色业务处理
 * 
 * @author ack
 *
 */
@Service
public class RoleServiceImpl extends AckMapperServiceImpl<Role, Integer>
		implements RoleService {
	
	@Autowired
    private RoleMapper roleMapper;
	
	@Autowired
	private MenuService menuServiceImpl;
	
	@Override
	protected AckMapper<Role, Integer> getAckMapper() {
		return roleMapper;
	}

	@Override
	public Set<Permission> findPermissoinsByRole(Role role) {
		Set<Permission> set = roleMapper.findPermissionById(role.getId());
		return set;
	}

	@Override
	public Set<Role> findByIds(String[] roleIds) {
		return roleMapper.findByIds(roleIds);
	}

	@Override
	public Set<Menu> findMenusByRole(Role role) {
		role = roleMapper.find(role);
		String menuIds = role.getMenuIds();
		String[] ids = menuIds.split(",");
		Set<Menu> menus = menuServiceImpl.findByIds(ids);
		return menus;
	}

	@Override
	public Role findManager() {
		return roleMapper.findByAbbreviation(Content.PROJECT_MANAGE);
	}

}
