package org.ack.sys.base.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ack.sys.base.persist.BaseDao;
import org.ack.sys.base.persist.page.ColumnFilter;
import org.ack.sys.base.persist.page.Page;
import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.persist.page.PageRequest;
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
	@Override
	public Page<T> findPage(PageRequest pageRequest) {
		Page<T> page = new Page<T>();
		page.setCurrentPage(pageRequest.getCurrentPage());
		page.setPageSize(pageRequest.getPageSize());
		Map<String, Object> condition = new HashMap<String, Object>();
		Map<String, ColumnFilter> map = pageRequest.getColumnFilters();
		Set<String> set = map.keySet();
		for(String key : set) {
			ColumnFilter cf = map.get(key);
			String value = cf.getValue();
			condition.put(key, value);
		}
		return findPage(page);
	}
}
