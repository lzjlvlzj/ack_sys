package org.ack.sys.persist.mapper.portal;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.pojo.PortalArticleMeta;

public interface PortalArticleMetaMapper extends PageDao<PortalArticleMeta, Long> {
    int deleteByPrimaryKey(Integer id);

    int insert(PortalArticleMeta record);

    int insertSelective(PortalArticleMeta record);

    PortalArticleMeta selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PortalArticleMeta record);

    int updateByPrimaryKey(PortalArticleMeta record);
}