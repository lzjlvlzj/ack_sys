package org.ack.sys.portal.service;

import org.ack.sys.base.service.PageService;
import org.ack.sys.pojo.PortalArticle;

import java.util.List;

public interface PortalArticleService extends PageService<PortalArticle,Long> {


    public List<PortalArticle> findByMenuId(Long[] ids);
}
