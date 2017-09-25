package org.ack.service;

import java.util.List;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.Project;
import org.ack.pojo.User;

/**
 * 项目业务逻辑
 * 
 * @author ack
 *
 */
/**
 * @author GeoStar
 *
 */
public interface ProjectService extends AckMapperService<Project, Long> {

	/**
	 * 根据部门id查询项目
	 * 
	 * @param id
	 * @return
	 */
	public List<Project> findByDepartmentId(Integer id);

	/**
	 * 查询项目合作者
	 * 
	 * @param t
	 * @return
	 */
	public List<User> findAllProjectCooperator(Project t);

	/**
	 * 查询已有
	 * 
	 * @param t
	 * @return
	 */
	public List<User> findExistProjectCooperator(Project t);

	/**
	 * 设置合作用户
	 * 
	 * @param t
	 * @return
	 */
	public Integer setCooperatorAdd(Long taskId, String userIds);

	/** 
	 * @param user
	 * @return
	 */
	public List<Project> findUsableProjectList(User user);

}
