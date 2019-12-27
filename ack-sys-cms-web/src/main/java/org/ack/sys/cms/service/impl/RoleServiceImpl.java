package org.ack.sys.cms.service.impl;


import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.cms.persist.mapper.RoleMapper;
import org.ack.sys.cms.pojo.Role;
import org.ack.sys.cms.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**用戶邏輯
 * @author ack
 *
 */
@Service
public class RoleServiceImpl extends PageServiceImpl<Role, Long> implements RoleService {
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Autowired
	private RoleMapper roleMapper;

	@Override
	protected PageDao<Role, Long> getPageDao() {
		logger.debug("mapper is {}", roleMapper);
		return roleMapper;
	}




	

}
