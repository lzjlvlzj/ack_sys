package org.ack.sys.base.core.auth;

import java.lang.reflect.Method;
import java.util.Set;

import org.ack.sys.base.core.auth.annotation.AckPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Authorizer implements Authorization {
	private static final Logger logger = LoggerFactory.getLogger(Authorizer.class);
	/**
	 * 校验当前方法上的权限字符串
	 * 
	 * @param method
	 * @param permissions
	 * @return
	 */
	public boolean hasPermission(Method method, Set<String> permissions) {
		String strs = getPermissionString(method);
		if (null == strs) {
			return true;
		}
		boolean b = match(strs, permissions);
		return b;
	}

	/**
	 * 获得权限字符串
	 * 
	 * @param method
	 * @return
	 */
	public String getPermissionString(Method method) {
		AckPermission ap = method.getAnnotation(AckPermission.class);
		if (null == ap) {
			return null;
		}
		String value = ap.value();
		return value;
	}

	/**
	 *
	 */
	@Override
	public boolean match(String currentPermission, Set<String> permissions) {
		logger.debug("当前权限字符串:{}", currentPermission);
		String logic = AuthLogic.AND.getValue() + "|" + AuthLogic.OR.getValue();
		String[] strs = currentPermission.trim().split(logic);
		if (null == strs || strs.length == 0) {
			return false;
		}
		int flag = 0;// 出现次数
		for (String pm : strs) {
			pm = pm.trim();
			for (String p : permissions) {
				if (pm.equals(p)) {
					flag++;
				}
			}
		}
		if (currentPermission.indexOf(AuthLogic.AND.getValue()) >= 0) {
			if (flag >= 2) {
				return true;
			}
		} else if (currentPermission.indexOf(AuthLogic.OR.getValue()) >= 0) {
			if (flag >= 1) {
				return true;
			}
		} else {
			if (flag >= 1) {
				return true;
			}
		}
		return false;
	}
}
