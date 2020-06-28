package org.ack.sys.persist.mapper.portal;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.pojo.PortalArticle;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PortalArticleMapper extends PageDao<PortalArticle, Long> {
    int deleteByPrimaryKey(Integer id);

    int insert(PortalArticle record);

    int insertSelective(PortalArticle record);

    PortalArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PortalArticle record);

    int updateByPrimaryKey(PortalArticle record);
}