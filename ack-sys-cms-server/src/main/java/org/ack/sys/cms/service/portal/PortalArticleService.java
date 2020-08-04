package org.ack.sys.cms.service.portal;

import org.ack.sys.base.service.PageService;
import org.ack.sys.pojo.PortalArticle;

public interface PortalArticleService extends PageService<PortalArticle, Long> {

	int publish(Long id);

}
