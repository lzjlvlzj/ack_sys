package org.ack.admin.web.Interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.auth.authenticate.Authenticate;
import org.ack.common.Content;
import org.ack.pojo.User;
import org.ack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 权限验证
 * 
 * @author ack
 *
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userServiceImpl;
	private static final Logger logger = LoggerFactory
			.getLogger(AuthenticationInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 获得当前请求url对应的method
		// DefaultServletHttpRequestHandler //出错情况下404 500
		HandlerMethod handlerMethod = null;
		if (logger.isDebugEnabled()) {
			String name = handler.getClass().getName();
			logger.debug("当前访问方法调用的handler类型 : {}", name);
		}
		boolean b = false;
		if (handler instanceof HandlerMethod) {
			handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			// 获得method上的注解
			Authenticate au = new Authenticate(userServiceImpl);
			User user = (User) request.getSession().getAttribute(Content.USER);
			b = au.checkPermission(method, user);
		} else {
			b = true;
		}
        if(!b){
        	response.sendRedirect("/error/403");
        }
		return b;
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

}
