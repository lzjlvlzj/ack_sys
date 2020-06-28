package org.ack.sys.portal.service.impl;

import org.ack.sys.base.persist.BaseDao;
import org.ack.sys.base.service.impl.BaseServiceImpl;
import org.ack.sys.persist.mapper.portal.PortalArticleMapper;
import org.ack.sys.pojo.PortalArticle;
import org.ack.sys.portal.service.PortalArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortalArticleServiceImpl extends BaseServiceImpl<PortalArticle, Long> implements PortalArticleService {

    private static final Logger logger = LoggerFactory.getLogger(PortalArticleServiceImpl.class);
    @Autowired
    private PortalArticleMapper portalArticleMapper;

    @Override
    public BaseDao<PortalArticle, Long> getDao() {
        return portalArticleMapper;
    }
}
