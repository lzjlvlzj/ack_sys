package org.ack.sys.portal.service.impl;

import org.ack.sys.pojo.PortalMenu;
import org.ack.sys.portal.service.PortalMenuService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class PortalMenuServiceImplTest {
    @Autowired
    private PortalMenuService portalMenuServiceImpl;

    @Test
    public void findSortedMenu() {
        PortalMenu  menu = portalMenuServiceImpl.findSortedMenu();
        assertNotNull(menu);
        System.out.println(menu);
    }
}