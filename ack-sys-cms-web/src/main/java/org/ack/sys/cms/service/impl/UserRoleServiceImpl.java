package org.ack.sys.cms.service.impl;


import java.util.List;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.cms.persist.mapper.UserRoleMapper;
import org.ack.sys.cms.pojo.UserRole;
import org.ack.sys.cms.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRoleServiceImpl extends PageServiceImpl<UserRole, Long> implements UserRoleService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);
	private UserRoleMapper userRoleMapper;
	@Override
	protected PageDao<UserRole, Long> getPageDao() {
		return userRoleMapper;
	}
	@Override
	public List<UserRole> findByUserId(Long id) {
		logger.debug("用户id:{}", id);
		return userRoleMapper.findByUserId(id);
	}


}
