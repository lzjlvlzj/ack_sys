package org.ack.service.impl;

import java.util.Set;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.PermissionMapper;
import org.ack.pojo.Menu;
import org.ack.pojo.Permission;
import org.ack.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 权限管理实现
 * 
 * @author ack
 *
 */
@Service
public class PermissionServiceImpl extends
		AckMapperServiceImpl<Permission, Integer> implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	protected AckMapper<Permission, Integer> getAckMapper() {
		return permissionMapper;
	}

	@Override
	public Set<Menu> findMenusById(Integer id) {
		Set<Menu> set = permissionMapper.findMenuById(id);
		return set;
	}

}
