package org.ack.sys.cms.service.portal;

import org.ack.sys.base.service.BaseService;
import org.ack.sys.pojo.PortalArticle;

public interface PortalArticleService extends BaseService<PortalArticle, Long> {

	int insert(PortalArticle article);

	int update(PortalArticle article);

	int delete(PortalArticle article);

	int publish(Long id);

}
