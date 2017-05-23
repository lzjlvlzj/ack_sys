package org.ack.base.service;

import java.io.Serializable;
import java.util.List;

/**
 * 服务层基础接口
 * 
 * @author ack
 *
 * @param <T>
 * @param <PK>
 */
public interface BaseService<T extends Object, PK extends Serializable> {
	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public T findById(PK id);

	/**
	 * 根据多个id查询
	 * 
	 * @param ids
	 * @return
	 */
	public List<T> findByIds(PK[] ids);

	/**
	 * 修改
	 * 
	 * @param t
	 */
	public int update(T t);

	/**
	 * 插入
	 * 
	 * @param t
	 */
	public int insert(T t);

	/**
	 * 刪除
	 * 
	 * @param t
	 */
	public int delete(T t);

	/**
	 * 查询所有结果集
	 * 
	 * @return
	 */
	public List<T> findAll();

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public int deleteById(PK id);

	/**
	 * 根据id数字删除
	 * 
	 * @param deptIds
	 * @return
	 */
	public int deleteByIds(String[] ids);

	/**
	 * 全部删除
	 */
	public int deleteAll();

}
