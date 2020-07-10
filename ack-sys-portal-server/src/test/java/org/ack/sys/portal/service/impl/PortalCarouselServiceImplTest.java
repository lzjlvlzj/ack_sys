package org.ack.sys.portal.service.impl;

import org.ack.sys.pojo.PortalCarousel;
import org.ack.sys.portal.service.PortalCarouselService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PortalCarouselServiceImplTest {


    @Autowired
    PortalCarouselService portalCarouselServiceImpl;

    @Test
    void findByPosition() {
        List<PortalCarousel> list = portalCarouselServiceImpl.findByPosition(0);
        assertNotNull(list);
        System.out.println(list.size());
    }
}