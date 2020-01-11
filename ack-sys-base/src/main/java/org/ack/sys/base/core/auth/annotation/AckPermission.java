package org.ack.sys.base.core.auth.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义 权限注解
 * 
 * @author ack
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AckPermission {
	/**
	 * 默认拥有权限值
	 * */
	public String value() default "";
}
