package org.ack.sys.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.ack.sys.base.persist.BaseDao;
import org.ack.sys.base.service.BaseService;





/**
 * 服务层基础实现(不包括分页)
 * 
 * @author ack
 *
 * @param <T>
 * @param <PK>
 */
public abstract class BaseServiceImpl<T extends Object, PK extends Serializable>
		implements BaseService<T, PK> {

	public abstract BaseDao<T, PK> getDao();

	@Override
	public T findById(PK id) {
		return getDao().findById(id);
	}

	@Override
	public List<T> findByIds(PK[] ids) {
		return getDao().findByIds(ids);
	}

	@Override
	public int update(T t) {
		return getDao().update(t);
	}

	@Override
	public int insert(T t) {
		return getDao().insert(t);
	}

	@Override
	public int delete(T t) {
		return getDao().delete(t);
	}

	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}

	@Override
	public int deleteById(PK id) {
		return getDao().deleteById(id);
	}

	@Override
	public int deleteByIds(String[] ids) {
		return 0;
	}

	@Override
	public int deleteAll() {
		return 0;
	}

	@Override
	public int batchUpdate(List<T> list) {
		return getDao().batchUpdate(list);
	}
	
	@Override
	public int batchDelete(List<T> list) {
		return getDao().batchUpdate(list);
	}
	
	

	
}
