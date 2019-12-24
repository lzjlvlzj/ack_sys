package org.ack.sys.cms.service.impl;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.cms.persist.mapper.UserMapper;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**用戶邏輯
 * @author ack
 *
 */
@Service
public class UserServiceImpl extends PageServiceImpl<User, Integer> implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;

	@Override
	protected PageDao<User, Integer> getPageDao() {
		return userMapper;
	}

	@Override
	public User findUserByUserName(String username) {
		logger.debug("username = {}" , username);
		return userMapper.findUserByUserName(username);
	}



	

}
