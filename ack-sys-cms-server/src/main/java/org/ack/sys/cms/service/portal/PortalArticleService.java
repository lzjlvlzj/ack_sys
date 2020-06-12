package org.ack.sys.cms.service.portal;

import org.ack.sys.pojo.PortalArticle;

public interface PortalArticleService {

	int insert(PortalArticle article);

	int update(PortalArticle article);

	int delete(PortalArticle article);

	int publish(Long id);

}
