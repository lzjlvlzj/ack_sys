package org.ack.sys.cms.persist.mapper;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.cms.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper extends PageDao<Menu, Long>{

}
