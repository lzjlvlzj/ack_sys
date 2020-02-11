package org.ack.sys.cms.service;

import org.ack.sys.base.service.BaseService;
import org.ack.sys.cms.pojo.User;

/**
 * 个人接口
 * 
 * @author ack
 *
 */
public interface PersonalService extends BaseService<User, Long> {

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param username
	 * @return
	 */
	public User findUserByUserName(String username);

	/**
	 * 密码修改
	 * 
	 * @param user
	 * @return
	 */
	public int changePwd(User user);

}
