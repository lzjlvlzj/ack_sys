package org.ack.sys.cms.persist.mapper;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.cms.pojo.Dictionary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictionaryMapper extends PageDao<Dictionary, Long> {

	/**
	 * 根据key查询字典信息
	 * 
	 * @param key
	 * @return
	 */
	public Dictionary findByKey(String key);

	/**
	 * 根据key修改字典信息
	 * 
	 * @param dict
	 * @return
	 */
	public Dictionary updateByKey(Dictionary dict);

	/**
	 * 根据key进行删除
	 * 
	 * @param dict
	 * @return
	 */
	public Dictionary deleteByKey(Dictionary dict);
}