package org.ack.base.persist;

import java.io.Serializable;
import java.util.List;

/**
 * MyBatis基础Mapper接口
 * <p>
 * 基础接口(不包括分页)
 * 
 * @author ack
 *
 * @param <T>
 *            要操作的对象
 * @param <PK>
 *            类型
 */
public interface BaseMapper<T extends Object, PK extends Serializable> extends
		BaseDao<T> {
	/**
	 * 删除
	
	 * @param t
	 * @return
	 */
	public Integer deleteById(PK id);

	/**
	 * 删除
	 * 
	 * @param t
	 * @return
	 */
	public Integer deleteByIds(String[] id);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return T
	 */
	public T findById(PK id);

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

}
