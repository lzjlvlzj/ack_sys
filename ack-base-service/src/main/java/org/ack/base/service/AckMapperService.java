package org.ack.base.service;

import java.io.Serializable;

import org.ack.persist.page.Page;

/**
 * 主要是针对MyBatis服务进行封装
 * 
 * @author ack
 *
 * @param <T>
 * @param <PK>
 */
public interface AckMapperService<T extends Object, PK extends Serializable>
		extends BaseService<T, PK> {

	/**
	 * 拦截器分页查询
	 * 
	 * @param page
	 * @return
	 */
	public Page<T> findPage(Page<T> page);
}
