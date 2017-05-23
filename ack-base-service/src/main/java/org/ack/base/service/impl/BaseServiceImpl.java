package org.ack.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.ack.base.persist.BaseMapper;
import org.ack.base.service.BaseService;


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

	public abstract BaseMapper<T, PK> getMapper();

	@Override
	public T findById(PK id) {
		return getMapper().findById(id);
	}

	@Override
	public List<T> findByIds(PK[] ids) {
		return getMapper().findByIds(ids);
	}

	@Override
	public int update(T t) {
		return getMapper().update(t);
	}

	@Override
	public int insert(T t) {
		return getMapper().insert(t);
	}

	@Override
	public int delete(T t) {
		return getMapper().delete(t);
	}

	@Override
	public List<T> findAll() {
		return getMapper().findAll();
	}

	@Override
	public int deleteById(PK id) {
		return getMapper().deleteById(id);
	}

	@Override
	public int deleteByIds(String[] ids) {
		return 0;
	}

	@Override
	public int deleteAll() {
		return 0;
	}

	
}
