package org.ack.sys.cms.aop;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.ack.sys.base.common.Content;
import org.ack.sys.base.util.HttpUtil;
import org.ack.sys.base.util.ReflectUtil;
import org.ack.sys.pojo.User;
import org.ack.sys.cms.web.template.SessionUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 自动添加创建者，创建时间，修改人，修改时间
 * 
 * @author ack
 *
 */
@Aspect
@Component
public class DaoAspect {
	private final static Logger logger = LoggerFactory.getLogger(DaoAspect.class);
	private static final String CREATOR = "creator";
	private static final String CREATE_TIME = "createTime";
	private static final String MODIFIER = "modifier";
	private static final String MODIFY_TIME = "modifyTime";

	/* 插入的切入点 */
	@Pointcut("execution(* org.ack.sys.cms.persist.mapper.*.insert*(..))")
	public void insertPointCut() {

	}

	/* 修改的切入点 */
	@Pointcut("execution(* org.ack.sys.cms.persist.mapper.*.update*(..))")
	public void updatePointCut() {

	}

	@Around("insertPointCut()")
	public Object aroundInsert(ProceedingJoinPoint point) throws Throwable {
		HttpServletRequest request = HttpUtil.getHttpServletRequest();
		if (null == request) {
			logger.debug("request is null.");
			return point.proceed();
		}
		User user = getUser(request);
		if(null != user) {
			Date date = new Date();
			Object[] args = point.getArgs();
			if (null != args && args.length > 0) {
				for (int i = 0; i < args.length; i++) {
					Object obj = args[i];
					ReflectUtil.setFieldValue(obj, CREATOR, user.getId());
					ReflectUtil.setFieldValue(obj, CREATE_TIME, date);
					ReflectUtil.setFieldValue(obj, MODIFIER, user.getId());
					ReflectUtil.setFieldValue(obj, MODIFY_TIME, date);
					System.out.println(obj);
				}
			}
		}
		

		return point.proceed();
	}

	private User getUser(HttpServletRequest request) {
		String key = Content.SESSION_KEY_USER;
		SessionUser sessionUser = (SessionUser) HttpUtil.getSessionStore(request, key);
		if(null == sessionUser) {
			return null;
		}
		User user = sessionUser.getUser();
		return user;
	}

	@Around("updatePointCut()")
	public Object aroundUpdate(ProceedingJoinPoint point) throws Throwable {
		Object result = point.proceed();
		return result;
	}

}
