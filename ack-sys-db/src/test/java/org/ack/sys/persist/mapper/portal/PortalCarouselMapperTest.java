package org.ack.sys.persist.mapper.portal;

import org.ack.sys.persist.BaseTest;
import org.ack.sys.pojo.PortalCarousel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class PortalCarouselMapperTest extends BaseTest {
    PortalCarouselMapper portalCarouselMapper;

    @BeforeEach
    void init() {
        portalCarouselMapper = sqlSession.getMapper(PortalCarouselMapper.class);
    }
    @AfterEach
    void after(){
        close();
    }

    @Test
    void testFindByPosition(){
        int position = 0;
        List<PortalCarousel> list = portalCarouselMapper.findByPosition(position);
        assertNotNull(list);
        System.out.println(list.size());
    }

    @Test
    void insert() {
        Long id = 1L;
        Date date = new Date();
        PortalCarousel portalCarousel = new PortalCarousel();
        portalCarousel.setPosition(0);
        portalCarousel.setRemark("首页大图轮播");
        portalCarousel.setUrl("/img/pt/c-3.jpg");
        portalCarousel.setDeleteStatus(0);
        portalCarousel.setCreator(id);
        portalCarousel.setCreateTime(date);
        portalCarousel.setModifier(id);
        portalCarousel.setModifyTime(date);
        int r = portalCarouselMapper.insert(portalCarousel);
        assertEquals(r, 1);
        commit();
    }
}