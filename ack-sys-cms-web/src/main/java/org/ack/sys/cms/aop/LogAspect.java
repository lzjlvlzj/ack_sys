package org.ack.sys.cms.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.ack.sys.base.common.ResponseResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * 日志代理
 * */
@Component
@Aspect
public class LogAspect {
   /*1. 声明一个类为切面服务
    *2. 选择一个切入点(包,方法,注解等) 
    *3. 编写切入的内容 
    * */
	private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@Pointcut("execution(* org.ack.sys.cms.service..*.*(..))")
	public void LogPointCut() {
		
	}
	
	@Around(value = "LogPointCut()")
	public Object arround(ProceedingJoinPoint pjp) {
        try {
            logger.debug("1、Around：方法环绕开始.....");
            long t1 = System.currentTimeMillis();
            Object o =  pjp.proceed();
            long t2 = System.currentTimeMillis();
            long result = t2 - t1;
            logger.debug("执行时间: " + result + "ms.");
            logger.debug("3、Around：方法环绕结束，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            logger.error(pjp.getSignature() + " 出现异常： ", e);
            return new ResponseResult(200, "出现异常：" + e.getMessage());
        }
    } 
	 /**
     * 方法执行前
     */
    @Before(value = "LogPointCut()")
    public void before(JoinPoint joinPoint){
        logger.debug("2、Before：方法执行开始...");
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        if(logger.isDebugEnabled()) {
        	// 记录下请求内容
            logger.debug("URL : " + request.getRequestURL().toString());
            logger.debug("HTTP_METHOD : " + request.getMethod());
            logger.debug("IP : " + request.getRemoteAddr());
            logger.debug("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            logger.debug("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        }

    }

    /**
     * 方法执行结束，不管是抛出异常或者正常退出都会执行
     */
    @After(value = "LogPointCut()")
    public void after(JoinPoint joinPoint){
        logger.debug("4、After：方法最后执行.....");
    }

    /**
     * 方法执行结束，增强处理
     */
    @AfterReturning(returning = "ret", pointcut = "LogPointCut()")
    public void doAfterReturning(Object ret){
        // 处理完请求，返回内容
        logger.debug("5、AfterReturning：方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing(value = "LogPointCut()")
    public void throwss(JoinPoint joinPoint){
        logger.error("AfterThrowing：方法异常时执行.....");
    }
}
