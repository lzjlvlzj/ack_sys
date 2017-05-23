package org.ack.base.persist;


/**
 * 持久层基础接口封装
 * 
 * @author ack
 *
 * @param <T>
 * @param <PK>
 */
public interface BaseDao<T extends Object> {
	
	/**
	 * 查询
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
}
