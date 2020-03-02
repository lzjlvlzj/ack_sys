package org.ack.sys.cms.persist.mapper;

import java.util.List;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.cms.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;

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