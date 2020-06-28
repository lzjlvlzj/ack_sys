package org.ack.sys.cms.service.portal;

import org.ack.sys.base.persist.BaseDao;
import org.ack.sys.base.service.impl.BaseServiceImpl;
import org.ack.sys.persist.mapper.portal.PortalArticleMapper;
import org.ack.sys.pojo.PortalArticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortalArticleServiceImpl extends BaseServiceImpl<PortalArticle, Long> implements PortalArticleService {

	private static final Logger logger = LoggerFactory.getLogger(PortalArticleServiceImpl.class);
	@Autowired
	private PortalArticleDetailService portalArticleDetailServiceImpl;
	@Autowired
	private PortalArticleMapper portalArticleMapper;

	@Override
	public BaseDao<PortalArticle, Long> getDao() {
		return null;
	}

	@Override
	public int insert(PortalArticle article) {
		return 0;
	}



	@Override
	public int update(PortalArticle article) {
		return 0;
	}

	@Override
	public int delete(PortalArticle article) {
		return 0;
	}

	@Override
	public int publish(Long id) {
		logger.info("重新生成首页");
		logger.info("重新生成列表页");
		logger.info("生成文章列表");
		return 0;
	}
}
