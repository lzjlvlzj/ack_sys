package org.ack.sys.cms.service.impl;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.base.util.MD5Util;
import org.ack.sys.cms.persist.mapper.UserMapper;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.service.PersonalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonalServiceImpl extends PageServiceImpl<User, Long> implements PersonalService {
	private static final Logger logger = LoggerFactory.getLogger(PersonalServiceImpl.class);
	@Autowired
	private UserMapper userMapper;

	@Override
	protected PageDao<User, Long> getPageDao() {
		return userMapper;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User findUserByUserName(String username) {
		logger.debug("username = {}", username);
		User user = userMapper.findUserByUserName(username);
		user.setPassword("");
		return user;
	}

	@Override
	public int changePwd(User user) {
		User u = userMapper.findUserByUserName(user.getUsername());
		if(!MD5Util.md5(user.getPassword()).equals(u.getPassword())) {
			logger.debug("原密码不正确");
			return 2;
		}
		String newPass = user.getNewPassword();
		String password = MD5Util.md5(newPass);
		user.setPassword(password);
		
		return userMapper.updateByUserName(user);
	}

}
