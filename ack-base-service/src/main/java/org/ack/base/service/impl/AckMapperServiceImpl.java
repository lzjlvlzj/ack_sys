package org.ack.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.ack.base.persist.BaseMapper;
import org.ack.base.service.AckMapperService;
import org.ack.persist.AckMapper;
import org.ack.persist.page.Page;

/**
 * 抽象服务层逻辑实现
 * 
 * @author ack
 *
 * @param <T>
 * @param <PK>
 */
public abstract class AckMapperServiceImpl<T extends Object, PK extends Serializable>
		extends BaseServiceImpl<T, PK> implements AckMapperService<T, PK> {

	/**
	 * 获取对于Mapper
	 * 
	 * @return
	 */
	protected abstract AckMapper<T, PK> getAckMapper();

	@Override
	public BaseMapper<T, PK> getMapper() {
		return getAckMapper();
	}

	@Override
	public Page<T> findPage(Page<T> page) {
		List<T> list = getAckMapper().findInterceptorPageList(page);
		page.setResult(list);
		return page;
	}
}
