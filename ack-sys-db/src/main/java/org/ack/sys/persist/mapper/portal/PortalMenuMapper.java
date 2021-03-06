package org.ack.sys.persist.mapper.portal;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.pojo.PortalMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PortalMenuMapper extends PageDao<PortalMenu, Long> {

    int insert(PortalMenu record);

    int insertSelective(PortalMenu record);


    int updateByPrimaryKeySelective(PortalMenu record);

    int updateByPrimaryKey(PortalMenu record);
}