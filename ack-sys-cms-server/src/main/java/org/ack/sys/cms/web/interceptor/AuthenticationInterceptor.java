package org.ack.sys.cms.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ack.sys.base.common.Content;
import org.ack.sys.base.util.HttpUtil;
import org.ack.sys.base.util.StringUtils;
import org.ack.sys.cms.web.template.SessionUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @program: service-center
 * @description: 拦截器
 * @author: Mr.superbeyone
 * @create: 2018-11-08 09:37
 **/
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String json = "{\"code\":401, \"msg\":\"未登录\", \"data\":\"\"}";
        HttpSession session = request.getSession();
        SessionUser user = (SessionUser) session.getAttribute(Content.SESSION_KEY_USER);
        if (null == user) {
            logger.debug("session中没有用户");
            HttpUtil.responseJson(response, 401, json);
            return false;
        }
        //String token = request.getHeader("token");
        String token = getToken(request);
        if (StringUtils.isBlank(token)) {
            logger.debug("header中没有token");
            HttpUtil.responseJson(response, 401, json);
            return false;
        }
        if (!token.equals(user.getToken())) {
            logger.debug("header中token与session中token不一致");
            HttpUtil.responseJson(response, 401, json);
            return false;
        }
        return super.preHandle(request, response, handler);
    }

    private String getToken(HttpServletRequest request) {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (null == cookies || cookies.length == 0) {
            token = request.getHeader("token");
        }
        for (Cookie cookie : cookies) {
            String key = cookie.getName();
            if ("token".equalsIgnoreCase(key)) {
                token = cookie.getValue();
                break;
            }
        }
        return token;
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
