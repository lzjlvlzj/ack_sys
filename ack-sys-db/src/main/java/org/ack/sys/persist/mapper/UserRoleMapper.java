package org.ack.sys.persist.mapper;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper extends PageDao<UserRole, Long> {

	/**
	 * 根据用户id查询角色
	 * 
	 * @param id
	 * @return
	 */
	public List<UserRole> findByUserId(Long id);

	/**
	 * 根据用户id删除
	 * 
	 * @param userId
	 * @return
	 */
	public int deleteByUserId(Long userId);
}