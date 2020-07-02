package org.ack.sys.cms.service.portal;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.pojo.PortalArticleDetail;
import org.springframework.stereotype.Service;

@Service
public class PortalArticleDetailServiceImpl extends PageServiceImpl<PortalArticleDetail, Long> implements PortalArticleDetailService {
    @Override
    protected PageDao<PortalArticleDetail, Long> getPageDao() {
        return null;
    }
}
