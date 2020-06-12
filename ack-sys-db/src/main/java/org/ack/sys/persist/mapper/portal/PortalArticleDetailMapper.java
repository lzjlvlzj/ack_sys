package org.ack.sys.persist.mapper.portal;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.pojo.PortalArticleDetail;

public interface PortalArticleDetailMapper extends PageDao<PortalArticleDetail, Long> {
	int deleteByPrimaryKey(Integer id);

	int insert(PortalArticleDetail record);

	int insertSelective(PortalArticleDetail record);

	PortalArticleDetail selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(PortalArticleDetail record);

	int updateByPrimaryKeyWithBLOBs(PortalArticleDetail record);
}