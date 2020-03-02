package org.ack.sys.cms.service;

import java.util.List;

import org.ack.sys.base.service.PageService;
import org.ack.sys.cms.pojo.UserRole;

public interface UserRoleService extends PageService<UserRole, Long> {
	/**
	 * 根据用户id查询角色
	 * 
	 * @param username
	 * @return
	 */
	public List<UserRole> findByUserId(Long id);

	/**
	 * 根据用户id删除
	 * 
	 * @param id
	 * @return
	 */
	public int deleteByUserId(Long id);
}
