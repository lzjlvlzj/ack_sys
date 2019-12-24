package org.ack.sys.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.ack.sys.base.persist.BaseDao;
import org.ack.sys.base.persist.Page;
import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.PageService;


/**
 * 抽象服务层逻辑实现
 * 
 * @author ack
 *
 * @param <T>
 * @param <PK>
 */
public abstract class PageServiceImpl<T extends Object, PK extends Serializable>
		extends BaseServiceImpl<T, PK> implements PageService<T, PK> {

	/**
	 * 获取对于Mapper
	 * 
	 * @return
	 */
	protected abstract PageDao<T, PK> getPageDao();

	@Override
	public BaseDao<T, PK> getDao() {
		return getPageDao();
	}

	@Override
	public Page<T> findPage(Page<T> page) {
		List<T> list = getPageDao().findInterceptorPageList(page);
		page.setResult(list);
		return page;
	}
}
