package org.ack.auth.authenticate;

import java.lang.reflect.Method;
import java.util.Set;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.pojo.User;
import org.ack.service.UserService;
import org.ack.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 鉴权
 * 
 * @author ack
 *
 */
@Component
public class Authenticate {

	private static final Logger logger = LoggerFactory
			.getLogger(Authenticate.class);
    @Autowired
	UserService userServiceImpl;
	User user;

	public Authenticate(UserService userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public User getUser() {
		return user;
	}
	
	

	public String getPermissionString(Method method) {
		AckPermission ap = method.getAnnotation(AckPermission.class);
		if (null == ap) {
			return null;
		}
		return ap.value();
	}
	
	public boolean checkPermission(Method method, User user){
		String currentPermission = getPermissionString(method);
		if(null == currentPermission){
			return true;
		}
		Set<String> permissions = userServiceImpl.getPermissionString(user);
		for(String p : permissions){
		   if(p.equals(currentPermission)){
			   return true;
		   }	
		}
	    return false;	
	}

	/**
	 * 0 : 登录成功 , 1 : 用户名错误, 2 :
	 * 
	 * @param user
	 * @return
	 */
	public int checkUser(User u) {
		int b = 0;
		if (null == u) {
			return 9;
		}
		if (StringUtils.isEmpty(u.getLoginName())) {
			return 9;
		}
		if (StringUtils.isEmpty(u.getPassword())) {
			return 9;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("需要验证的用户 :", u);
		}
		user = userServiceImpl.findUserByLoginName(u.getLoginName());
		if (null == user) {
			return 1;
		}
		String s = u.getPassword() + user.getSalt();
		String p = MD5Util.md5(s.getBytes());
		if (!p.equals(user.getPassword())) {
			return 2;
		}
		return b;
	}

}
