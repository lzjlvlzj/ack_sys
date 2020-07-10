package org.ack.sys.persist.mapper.portal;

import org.ack.sys.base.persist.page.PageDao;
import org.ack.sys.pojo.PortalCarousel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PortalCarouselMapper extends PageDao {

    List<PortalCarousel> findByPosition(int position);

    int deleteByPrimaryKey(Integer id);

    int insert(PortalCarousel record);

    int insertSelective(PortalCarousel record);

    PortalCarousel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PortalCarousel record);

    int updateByPrimaryKey(PortalCarousel record);

}