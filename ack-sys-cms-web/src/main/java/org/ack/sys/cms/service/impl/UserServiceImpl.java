package org.ack.sys.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ack.sys.base.common.Content;
import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.base.util.MD5Util;
import org.ack.sys.base.util.StringUtils;
import org.ack.sys.cms.persist.mapper.UserMapper;
import org.ack.sys.cms.pojo.Menu;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.service.MenuService;
import org.ack.sys.cms.service.UserService;
import org.ack.sys.cms.web.template.LoginUser;
import org.ack.sys.cms.web.template.SessionUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用戶邏輯
 * 
 * @author ack
 *
 */
@Service
public class UserServiceImpl extends PageServiceImpl<User, Long> implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	@Value(value = "${user.avatar}")
	private String deaultAvatar;
	@Autowired
	private MenuService menuServiceImpl;

	@Override
	protected PageDao<User, Long> getPageDao() {
		return userMapper;
	}

	@Override
	@Transactional
	public int insert(User user) {
		// 查询用户名否存在
		User dbUser = findUserByUserName(user.getUsername());
		if (null != dbUser) {
			logger.debug("用户{}已存在", user.getUsername());
			return -1;
		}
		// 检查邮箱
		dbUser = userMapper.findUserByEmail(user.getEmail());
		if (null != dbUser) {
			logger.debug("用户邮箱{}已存在", user.getEmail());
			return -2;
		}
		// 检查qq
		dbUser = userMapper.findUserByQq(user.getQq());
		if (null != dbUser) {
			logger.debug("用户邮箱{}已存在", user.getQq());
			return -3;
		}
		// 检查手机
		dbUser = userMapper.findUserByMobile(user.getMobile());
		if (null != dbUser) {
			logger.debug("用户手机{}已存在", user.getMobile());
			return -4;
		}
		user.setAvatar(deaultAvatar);
		String pass = MD5Util.md5(user.getPassword());
		user.setPassword(pass);
		return super.insert(user);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User findUserByUserName(String username) {
		logger.debug("username = {}", username);
		return userMapper.findUserByUserName(username);
	}

	@Override
	@Transactional
	public int batchDelete(List<User> list) {
		int size = list.size();
		int r = 0;
		for (int i = 0; i < size; i++) {
			User user = list.get(i);
			logger.debug("用户id : {}", user.getId());
			user.setDeleteStatus(1);
			int rt = update(user);
			r = r + rt;
		}
		logger.debug("需要修改的数据为{}条,实际修改{}条", size, r);
		return r;
	}

	@Override
	@Transactional
	public int deleteById(Long id) {
		User user = new User();
		user.setId(id);
		user.setDeleteStatus(1);
		return update(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<String> findUserPermissions(Long id) {
		List<Menu> menuList = menuServiceImpl.findNoReapetListByUserId(id);
		List<String> list = new ArrayList<String>();
		for (Menu menu : menuList) {
			list.add(menu.getPerms());
		}
		return list;
	}

	@Override
	public User findUserByEmail(String email) {
		return null;
	}

	@Override
	public User findUserByQq(String qq) {
		return null;
	}

	@Override
	public User findUserByMobile(String mobile) {
		return null;
	}

	private int checkLogin(LoginUser user, User dbUser) {
		if(null == user 
				|| StringUtils.isBlank(user.getUsername()) 
				|| StringUtils.isBlank(user.getPassword())) {
			logger.debug("用户登陆参数错误。");
			return 2;
		}
		if(null == dbUser) {
			logger.debug("用户{}不存在", user.getUsername());
			return 3;
		} 
		String pwd = MD5Util.md5(user.getPassword());
		if(!pwd.equals(dbUser.getPassword())) {
			logger.debug("用户{}輸入的密碼不正確.", user.getUsername());
			return 2;
		} 
		return 0;
	}

	@Override
	public int login(HttpServletRequest request, HttpServletResponse response, LoginUser user, String token) {
		HttpSession session = request.getSession();
		// 检查验证码
		String captcha = user.getCaptcha();
		if (StringUtils.isBlank(captcha)) {
			return 1;
		} else {
			captcha = captcha.trim();
			String sessionCaptcha = (String) session.getAttribute(Content.SESSION_KEY_KAPTCHA);
			if (!captcha.equalsIgnoreCase(sessionCaptcha)) {
				return 1;
			}

		}
		String username = user.getUsername();
		User dbUser = findUserByUserName(username);
		int rt = checkLogin(user, dbUser);
		if(rt == 0) {
			session.setAttribute(Content.SESSION_KEY_USER, new SessionUser(token, dbUser));
		}
		return rt;
	}

}
