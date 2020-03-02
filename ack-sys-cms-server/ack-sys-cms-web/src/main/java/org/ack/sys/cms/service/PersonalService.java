package org.ack.sys.cms.service;

import javax.servlet.http.HttpServletRequest;

import org.ack.sys.base.service.BaseService;
import org.ack.sys.cms.pojo.User;
import org.springframework.web.multipart.MultipartFile;

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

	/**
	 * 头像上传
	 * 
	 * @param file
	 * @return
	 */
	public String uploadAvatar(MultipartFile file, HttpServletRequest request);

}
