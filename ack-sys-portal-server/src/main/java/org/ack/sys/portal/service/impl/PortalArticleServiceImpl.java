package org.ack.sys.portal.service.impl;

import org.ack.sys.base.persist.BaseDao;
import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.BaseServiceImpl;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.persist.mapper.portal.PortalArticleMapper;
import org.ack.sys.pojo.PortalArticle;
import org.ack.sys.portal.service.PortalArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortalArticleServiceImpl extends PageServiceImpl<PortalArticle, Long> implements PortalArticleService {

    private static final Logger logger = LoggerFactory.getLogger(PortalArticleServiceImpl.class);
    @Autowired
    private PortalArticleMapper portalArticleMapper;

    @Override
    protected PageDao<PortalArticle, Long> getPageDao() {
        return portalArticleMapper;
    }

    @Override
    public List<PortalArticle> findByMenuId(Long[] ids) {
        logger.debug("根据菜单id查询文章");
        return portalArticleMapper.findByMenuId(ids);
    }
}
