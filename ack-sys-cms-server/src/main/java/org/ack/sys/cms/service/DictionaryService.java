package org.ack.sys.cms.service;

import org.ack.sys.base.service.PageService;
import org.ack.sys.pojo.Dictionary;

public interface DictionaryService extends PageService<Dictionary, Long> {
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
	 * 根据key删除字典信息
	 * 
	 * @param dict
	 * @return
	 */
	public Dictionary deleteByKey(Dictionary dict);
}
