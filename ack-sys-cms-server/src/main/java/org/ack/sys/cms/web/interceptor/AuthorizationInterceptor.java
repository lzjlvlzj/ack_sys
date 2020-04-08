package org.ack.sys.cms.web.interceptor;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.sys.base.core.auth.Authorization;
import org.ack.sys.base.core.auth.Authorizer;
import org.ack.sys.base.util.HttpUtil;
import org.ack.sys.pojo.User;
import org.ack.sys.cms.service.UserService;
import org.ack.sys.cms.web.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);
	@Autowired
	private UserService userServiceImpl;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = null;
		if (logger.isDebugEnabled()) {
			String name = handler.getClass().getName();
			logger.debug("当前访问方法调用的handler类型 : {}", name);
		}
		boolean b = false;
		if (handler instanceof HandlerMethod) {
			handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			if(handler instanceof HandlerMethod) {
				// 获得method上的注解
				Authorization au = new Authorizer();
				User user = WebUtil.getCurrentUser(request);
				if(user == null) {
					logger.error("用户未登录，权限为空.");
					return false;
				}
				List<String> list = userServiceImpl.findUserPermissions(user.getId());
				Set<String> set = new HashSet<>(list);
				b = au.hasPermission(method, set);
			}
		} else {
			b = true;
		}
		if (!b) {
			String json = "{\"code\":403, \"msg\":\"没有权限拒绝访问\", \"data\":\"没有权限拒绝访问\"}";
			HttpUtil.responseJson(response, 403, json);
		}
		return b;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

}
