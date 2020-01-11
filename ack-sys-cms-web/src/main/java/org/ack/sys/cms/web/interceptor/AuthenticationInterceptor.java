package org.ack.sys.cms.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ack.sys.base.common.Content;
import org.ack.sys.base.util.HttpUtil;
import org.ack.sys.base.util.StringUtils;
import org.ack.sys.cms.web.template.SessionUser;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @program: service-center
 * @description: 拦截器
 * @author: Mr.superbeyone
 * @create: 2018-11-08 09:37
 **/
public class AuthenticationInterceptor extends HandlerInterceptorAdapter   {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader("token");
		String json = "{\"code\":401, \"msg\":\"未登录\", \"data\":\"\"}";
		if(StringUtils.isBlank(token)) {
			HttpUtil.responseJson(response, 401, json);
			return false;
		}
		HttpSession session = request.getSession();
		SessionUser user = (SessionUser)session.getAttribute(Content.SESSION_KEY_USER);
		if(null == user) {
			HttpUtil.responseJson(response, json);
			return false;
		}
		if(!token.equals(user.getToken())) {
			HttpUtil.responseJson(response, json);
			return false;
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}
