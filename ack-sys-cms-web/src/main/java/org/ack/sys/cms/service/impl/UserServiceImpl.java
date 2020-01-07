package org.ack.sys.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.base.util.MD5Util;
import org.ack.sys.cms.persist.mapper.UserMapper;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 用戶邏輯
 * 
 * @author ack
 *
 */
@Service
public class UserServiceImpl extends PageServiceImpl<User, Long> implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	@Value(value = "${user.avatar}")
	private String deaultAvatar;
	
	@Override
	protected PageDao<User, Long> getPageDao() {
		return userMapper;
	}

	@Override
	public int insert(User user) {
		//查询用户名否存在
		User dbUser = findUserByUserName(user.getUsername());
	    if(null != dbUser) {
	    	logger.debug("用户{}已存在", user.getUsername());
            return -1;
	    }
	    //检查邮箱
	    dbUser = userMapper.findUserByEmail(user.getEmail());
	    if(null != dbUser) {
	    	logger.debug("用户邮箱{}已存在", user.getEmail());
	    	return -2;
	    }
	    //检查qq
	    dbUser = userMapper.findUserByQq(user.getQq());
	    if(null != dbUser) {
	    	logger.debug("用户邮箱{}已存在", user.getQq());
	    	return -3;
	    }
	    //检查手机
	    dbUser = userMapper.findUserByMobile(user.getMobile());
	    if(null != dbUser) {
	    	logger.debug("用户手机{}已存在", user.getMobile());
	    	return -4;
	    }
		user.setAvatar(deaultAvatar);
		String pass = MD5Util.md5(user.getPassword());
		user.setPassword(pass);
		return super.insert(user);
		
	}


	@Override
	public User findUserByUserName(String username) {
		logger.debug("username = {}", username);
		return userMapper.findUserByUserName(username);
	}
	
	
	
	

	@Override
	public int batchDelete(List<User> list) {
		int size = list.size();
		int r = 0;
		for (int i = 0; i < size; i++) {
			User user = list.get(i);
			logger.debug("用户id : {}", user.getId());
			user.setDeleteStatus(1);
			int rt = update(user);
			r = r + rt;
		}
		logger.debug("需要修改的数据为{}条,实际修改{}条", size, r);
		return r;
	}

	@Override
	public int deleteById(Long id) {
		User user = new User();
		user.setId(id);
		user.setDeleteStatus(1);
		return update(user);
	}

	@Override
	public List<String> findUserPermissions(String username) {
		List<String> list = new ArrayList<String>();
		list.add("sys:user:view");
		list.add("sys:user:add");
		list.add("sys:user:edit");
		list.add("sys:user:delete");
		list.add("sys:role:view");
		list.add("sys:role:view");
		list.add("sys:role:add");
		list.add("sys:role:edit");
		list.add("sys:role:delete");

		return list;
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByQq(String qq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByMobile(String mobile) {
		// TODO Auto-generated method stub
		return null;
	}

}
