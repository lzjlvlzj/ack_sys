package org.ack.sys.cms.persist.mapper;

import org.ack.sys.cms.pojo.User;
import org.ack.sys.persist.PageDao;

/**用戶接口
 * @author ack
 *
 */
public interface UserMapper extends PageDao<User, Integer> {

	/** 根据用户登陆名称查询用户信息
	 * @param username
	 * @return
	 */
	public User findUserByUserName(String username);

}
