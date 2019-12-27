package org.ack.sys.base.persist.page;

import java.util.List;

import org.ack.sys.base.persist.BaseDao;



public interface PageDao<T, PK> extends BaseDao<T, PK> {
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
