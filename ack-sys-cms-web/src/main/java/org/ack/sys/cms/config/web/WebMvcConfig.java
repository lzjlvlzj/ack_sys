package org.ack.sys.cms.config.web;

import java.util.ArrayList;
import java.util.List;

import org.ack.sys.cms.web.interceptor.AuthorizationInterceptor;
import org.ack.sys.cms.web.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
	private static List<String> EXCLUDE_URL = new ArrayList<String>();
	static {
		/* 过滤验证码服务 */
		EXCLUDE_URL.add("/captcha.jpg");
		/* 过滤登录 */
		EXCLUDE_URL.add("/login");
		/* 过滤登出 */
		EXCLUDE_URL.add("/logout");
	}

	/* 拦截器需要初始化(拦截器执行时在bean的初始化之前) ，拦截器没有被spring管理 */
	@Bean
	public AuthorizationInterceptor authorizationInterceptor() {
		return new AuthorizationInterceptor();
	}

	@Bean
	public AuthenticationInterceptor authenticationInterceptor() {
		return new AuthenticationInterceptor();
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		/**
		 * 拦截器按照顺序执行,如果不同拦截器拦截存在相同的URL，前面的拦截器会执行，后面的拦截器将不执行
		 */
		// 登录拦截
		registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**").excludePathPatterns(EXCLUDE_URL);
		// 授权拦截
		registry.addInterceptor(authorizationInterceptor()).addPathPatterns("/**").excludePathPatterns(EXCLUDE_URL);

		super.addInterceptors(registry);
	}
}