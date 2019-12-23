package org.ack.sys.cms.service;

import org.ack.sys.cms.pojo.User;
import org.ack.sys.service.PageService;

/**用户接口
 * @author ack
 *
 */
public interface UserService extends PageService<User, Integer>{

	/**根據用户登陆名称查询用户信息
	 * @param username
	 * @return User
	 */
	public User findUserByUserName(String username);

}
