package org.ack.sys.cms.persist.mapper;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.cms.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**用戶接口
 * @author ack
 *
 */
@Mapper
public interface UserMapper extends PageDao<User, Long> {

	/** 根据用户登陆名称查询用户信息
	 * @param username
	 * @return
	 */
	public User findUserByUserName(String username);

}
