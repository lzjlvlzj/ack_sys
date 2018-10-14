package org.ack.persist.mapper;

import org.ack.persist.AckMapper;
import org.ack.pojo.Role;
import org.ack.pojo.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户mapper接口
 * 
 * @author ack
 *
 */
public interface UserMapper extends AckMapper<User, Long> {

	/**
	 * 根据登录名查询
	 * 
	 * @param loginName
	 * @return
	 */
	public User findUserByLoginName(String loginName);

	/**
	 * 根据id查询用户
	 * 
	 * @param i
	 * @return
	 */
	public Set<Role> findRoleById(Long id);

	/**
	 * 关联角色查询
	 * 
	 * @param id
	 * @return
	 */
	public User findUserRoleById(Long id);

	/**
	 * @param map
	 */
	public void updateRoleByUser(Map<String, Object> map);
    /**
	 * 根据角色查询用户
	 *
	 * */
	List<User> findUserByRoleId(String roleId);

}
