package org.ack.sys.cms.service.impl;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.cms.persist.mapper.DictionaryMapper;
import org.ack.sys.cms.pojo.Dictionary;
import org.ack.sys.cms.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl extends PageServiceImpl<Dictionary, Long> implements DictionaryService {
	
	private static final Logger logger = LoggerFactory.getLogger(DictionaryServiceImpl.class);
	@Autowired
	private DictionaryMapper dictionaryMapper;

	@Override
	protected PageDao<Dictionary, Long> getPageDao() {
		return dictionaryMapper;
	}

	@Override
	public Dictionary findById(Long id) {
		return super.findById(id);
	}

	@Override
	public int insert(Dictionary t) {
		t.setDeleteStatus(0);
		Dictionary dict = findByKey(t.getKey());
		if (null != dict) {
			logger.debug("数据字典：{}的记录已经存在了", t.getKey());
			return -1;
		}
		return super.insert(t);
	}
	
    @Override
    public int update(Dictionary t) {
    	Dictionary dict = findByKey(t.getKey());
		if (null == dict) {
			logger.debug("数据字典：{}的记录不经存在", t.getKey());
			return -1;
		}
    	return super.update(t);
    }
    
    @Override
    public int delete(Dictionary t) {
    	t.setDeleteStatus(1);
    	return super.update(t);
    }
    
	@Override
	public Dictionary findByKey(String key) {
		return dictionaryMapper.findByKey(key);
	}

	@Override
	public Dictionary updateByKey(Dictionary dict) {
		return dictionaryMapper.updateByKey(dict);
	}

	@Override
	public Dictionary deleteByKey(Dictionary dict) {
		return dictionaryMapper.deleteByKey(dict);
	}

}
