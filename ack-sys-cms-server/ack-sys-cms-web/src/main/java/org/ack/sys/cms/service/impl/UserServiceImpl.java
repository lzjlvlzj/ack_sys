package org.ack.sys.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ack.sys.base.common.Content;
import org.ack.sys.base.persist.page.ColumnFilter;
import org.ack.sys.base.persist.page.Page;
import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.persist.page.PageRequest;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.base.util.MD5Util;
import org.ack.sys.base.util.StringUtils;
import org.ack.sys.cms.persist.mapper.UserMapper;
import org.ack.sys.cms.pojo.Menu;
import org.ack.sys.cms.pojo.Role;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.pojo.UserRole;
import org.ack.sys.cms.service.MenuService;
import org.ack.sys.cms.service.RoleService;
import org.ack.sys.cms.service.UserRoleService;
import org.ack.sys.cms.service.UserService;
import org.ack.sys.cms.web.template.LoginUser;
import org.ack.sys.cms.web.template.SessionUser;
import org.ack.sys.cms.web.util.WebUtil;
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
	@Autowired
	private RoleService roleServiceImpl;
	@Autowired
	private UserRoleService userRoleServiceImpl;
	

	@Override
	protected PageDao<User, Long> getPageDao() {
		return userMapper;
	}

	@Override
	public int update(User user, HttpServletRequest request) {
		logger.debug("用户修改");
		/* 检查用户要修改的数据权限是否比当前用户大，如果大则拒绝修改 */
		if (StringUtils.isNotBlank(user.getPassword())) {
			String pwd = MD5Util.md5(user.getPassword());
			user.setPassword(pwd);
		}
		return super.update(user);
	}

	public int getOperationStatus(User user, HttpServletRequest request) {
		User currentUser = WebUtil.getCurrentUser(request);
		List<Role> currentUserRoleList = findUserRoles(currentUser.getId());
		if(null == currentUserRoleList || currentUserRoleList.size() == 0) {
			logger.debug("当前登录用户{}没有角色信息", currentUser.getUsername());
			return 1;
		}
		int currentWeight = roleServiceImpl.getMinWeight(currentUserRoleList);
		List<Role> targetUserRoleList = findUserRoles(user.getId());
		if(null == targetUserRoleList || targetUserRoleList.size() == 0) {
			logger.debug("当前目标用户{}没有角色信息", currentUser.getUsername());
			return -1;
		}
		int targetWeight = roleServiceImpl.getMinWeight(targetUserRoleList);
		int r = currentWeight - targetWeight;
		return r;
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
		String password = "123456";
		String pass = MD5Util.md5(password);
		user.setPassword(pass);
		//设置非禁用
		user.setState(0);
		int r = super.insert(user);
		if (r == 1) {
			// 设置默认角色为普通员工
			Long roleId = 4L;
			UserRole userRole = new UserRole(user.getId(), roleId);
			int rt = userRoleServiceImpl.insert(userRole);
			if (rt != 1) {
				logger.debug("用户{}分配角色失败", user.getUsername());
				return 0;
			}
		}
		return r;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User findUserByUserName(String username) {
		logger.debug("username = {}", username);
		return userMapper.findUserByUserName(username);
	}

	@Override
	@Transactional
	public int batchDelete(List<User> list, HttpServletRequest request) {
		int size = list.size();
		int r = 0;
		for (int i = 0; i < size; i++) {
			User user = list.get(i);
			logger.debug("用户id : {}", user.getId());
			if ("admin".equals(user.getUsername())
					|| 1L == user.getId()) {
				user.setDeleteStatus(0);
				logger.debug("超级管理员不允许删除");
			} else {
				user.setDeleteStatus(1);
			}

			int rt = update(user, request);
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
		if (null == user || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
			logger.debug("用户登陆参数错误。");
			return 2;
		}
		if (null == dbUser) {
			logger.debug("用户{}不存在", user.getUsername());
			return 3;
		}
		if (dbUser.getDeleteStatus() == 1) {
			logger.debug("用户{}已删除", user.getUsername());
			return 3;
		}
		if (dbUser.getState() == 1) {
			logger.debug("用户{}已禁用", user.getUsername());
			return 4;
		}
		String pwd = MD5Util.md5(user.getPassword());
		if (!pwd.equals(dbUser.getPassword())) {
			logger.debug("用户{}輸入的密碼不正確.", user.getUsername());
			return 2;
		}
		return 0;
	}

	@Override
	public LoginUser login(HttpServletRequest request, HttpServletResponse response, LoginUser user, String token) {
		HttpSession session = request.getSession();
		logger.debug("sid1 = {}", session.getId());
		// 检查验证码
		String captcha = user.getCaptcha();
		if (StringUtils.isBlank(captcha)) {
			return new LoginUser(1);
		} else {
			captcha = captcha.trim();
			String sessionCaptcha = (String) session.getAttribute(Content.SESSION_KEY_KAPTCHA);
			if (!captcha.equalsIgnoreCase(sessionCaptcha)) {
				return new LoginUser(1);
			}

		}
		LoginUser loginUser = new LoginUser();
		String username = user.getUsername();
		User dbUser = findUserByUserName(username);
		int rt = checkLogin(user, dbUser);
		loginUser.setUsername(username);
		if (rt == 0) {
			loginUser.setRealName(dbUser.getRealName());
			loginUser.setAvatar(dbUser.getAvatar());
			loginUser.setToken(token);
			session.setAttribute(Content.SESSION_KEY_USER, new SessionUser(token, dbUser));
		}
		loginUser.setStatus(rt);
		return loginUser;
	}


	@Override
	public List<Role> findUserRoles(Long id) {
		return roleServiceImpl.findByUserId(id);
	}

	@Override
	public int grauntAuth(User user, HttpServletRequest request) {
		List<UserRole> userRoles = user.getUserRoles();
		if (null != userRoles && userRoles.size() > 0) {
			// 删除用户原来的角色
			int delCount = userRoleServiceImpl.deleteByUserId(user.getId());
			logger.debug("删除{}条用户角色记录", delCount);
			int incCount = 0;
			// 插入新角色
			if (null != userRoles && userRoles.size() > 0) {
				for (UserRole userRole : userRoles) {
					int n = userRoleServiceImpl.insert(userRole);
					incCount += n;
				}
				logger.debug("插入{}新用户角色条记录", incCount);
			}
		}
		return 0;
	}

	@Override
	public int resetPassword(User user, HttpServletRequest request) {
		logger.debug("为用户{}重置密码", user.getUsername());
		String password = "123456";
		user.setPassword(password);
		return update(user, request);
	}

	private boolean getExcludeStatus(Long userId) {
		boolean b = false;
		String[] ids = "1,2".split(",");
		for (int i = 0; i < ids.length; i++) {
			Long id = Long.parseLong(ids[i]);
			if (id == userId) {
				b = true;
				break;
			}
		}
		return b;
	}

	@Override
	public Page<User> findPage(PageRequest pageRequest, User user) {
		/* 用户只能查看自己所在部门的用户(不包括管理员和超级管理员) */
		boolean excludeStatus = getExcludeStatus(user.getId());
		if (!excludeStatus) {
			Long deptId = user.getDepartmentId();
			ColumnFilter dept = new ColumnFilter("departmentId", deptId.toString());
			pageRequest.getColumnFilters().put("departmentId", dept);
		}
		return findPage(pageRequest);
	}

	@Override
	public int delete(User user, HttpServletRequest request) {
		logger.debug("删除用户操作");
		user.setDeleteStatus(1);
		return update(user, request);
	}

	@Override
	public int ableUser(User user, HttpServletRequest request) {
		logger.debug("禁用启用用户操作");
		return update(user, request);
	}

}
