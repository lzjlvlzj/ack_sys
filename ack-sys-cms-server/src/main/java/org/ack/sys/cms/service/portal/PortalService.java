package org.ack.sys.cms.service.portal;

public interface PortalService {

	/**
	 * @return
	 */
	public int init();

	/**
	 * 生成首页
	 * 
	 * @return
	 */
	public int createIndex();

	/**
	 * 生成模块列表
	 * 
	 * @return
	 */
	public int createCategoryList();

	/**
	 * 生成文章
	 * 
	 * @return
	 */
	public int createArticle();
}
