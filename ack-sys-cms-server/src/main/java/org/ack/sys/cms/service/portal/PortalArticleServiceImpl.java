package org.ack.sys.cms.service.portal;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.base.service.impl.PageServiceImpl;
import org.ack.sys.persist.mapper.portal.PortalArticleMapper;
import org.ack.sys.pojo.PortalArticle;
import org.ack.sys.pojo.PortalArticleDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortalArticleServiceImpl extends PageServiceImpl<PortalArticle, Long> implements PortalArticleService {

    private static final Logger logger = LoggerFactory.getLogger(PortalArticleServiceImpl.class);
    @Autowired
    private PortalArticleDetailService portalArticleDetailServiceImpl;
    @Autowired
    private PortalArticleMapper portalArticleMapper;

    @Override
    protected PageDao<PortalArticle, Long> getPageDao() {
        return portalArticleMapper;
    }

    @Override
    public int insert(PortalArticle article) {
        //插入文章内容
        PortalArticleDetail pad = article.getPortalArticleDetail();
        int r = portalArticleDetailServiceImpl.insert(pad);
        if (r == 1) {
            article.setPageView(0);
            article.setDeleteStatus(0);
            article.setDetailId(pad.getId());
            /*插入文章meta信息*/
            r = portalArticleMapper.insert(article);
        }
        return r;
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
