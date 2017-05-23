package org.ack.persist;

import java.io.Serializable;
import java.util.List;

import org.ack.base.persist.BaseMapper;
import org.ack.persist.page.Page;

/**
 * mybatis mapper 基础接口封装
 * 
 * @author ack
 *
 * @param <T>
 *            实体类
 * @param <PK>
 *            id
 */
public interface AckMapper<T extends Object, PK extends Serializable> extends
		BaseMapper<T, PK> {

	/**
	 * <b>分页数量 </b>
	 * <p>
	 * 这个和findPageList()一起用,用与查询分页信息,但是必须把分类拦截器注释掉.
	 * 
	 * @return
	 */
	public int count(Page<T> page);

	/**
	 * <b>查询分页结果 </b>
	 * <p>
	 * 这个和count()一起用,用与查询分页信息,但是必须把分类拦截器注释掉.
	 * 
	 * @param map
	 * @return list
	 */
	public List<T> findPageList(Page<T> page);

	/**
	 * <b>拦截器分页 </b>
	 * <p>
	 * 使用mybatis拦截器进行分页
	 * 
	 * @param page
	 * @return page
	 */
	public List<T> findInterceptorPageList(Page<T> page);

}
