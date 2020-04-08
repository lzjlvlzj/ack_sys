package org.ack.sys.base.persist;

import java.util.List;
import java.util.Map;

public interface BaseDao<T, PK> {
	/**
	 * 查詢
	 *
	 * @param t
	 * @return
	 */
	public T find(T t);

	/**
	 * 插入
	 *
	 * @param t
	 * @return
	 */
	public int insert(T t);

	/**
	 * 根据id修改
	 *
	 * @param t
	 * @return
	 */
	public int update(T t);

	/**
	 * 删除
	 *
	 * @param t
	 * @return
	 */
	public int delete(T t);

	/**
	 * 删除
	 *
	 * @param t
	 * @return
	 */
	public Integer deleteById(PK id);

	/**
	 * 根据id查询
	 *
	 * @param id
	 * @return T
	 */
	public T findById(PK id);

	/**
	 * 根據具體字段查詢
	 * 
	 * @param map
	 * @return
	 */
	public T findBySelected(Map<String, String> map);

	/**
	 * 查询所有
	 *
	 * @return
	 */
	public List<T> findAll();

	/**
	 * 根据id查询
	 *
	 * @param id
	 * @return T
	 */
	public List<T> findByIds(PK[] id);

	/**
	 * 删除
	 *
	 * @param t
	 * @return
	 */
	public Integer deleteByIds(String[] id);

	/**
	 * 批量修改
	 * 
	 * @param list
	 * @return
	 */
	public Integer batchUpdate(List<T> list);

	/**
	 * 有则修改没有则插入
	 * 
	 * @param t
	 * @return
	 */
	public Integer save(T t);
}
