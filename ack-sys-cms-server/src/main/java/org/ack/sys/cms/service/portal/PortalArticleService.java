package org.ack.sys.cms.service.portal;

import org.ack.sys.base.common.ResponseResult;
import org.ack.sys.base.service.PageService;
import org.ack.sys.pojo.PortalArticle;
import org.springframework.web.multipart.MultipartFile;

public interface PortalArticleService extends PageService<PortalArticle, Long> {

	int publish(Long id);

    ResponseResult upload(MultipartFile file, int i);
}
