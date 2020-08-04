package org.ack.sys.cms.service.portal;

import org.ack.sys.pojo.PortalMenu;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PortalMenuServiceImplTest {

    @Autowired
    private PortalMenuService portalMenuServiceImpl;
    @Test
    void findPage() {
    }
    @Test
    void testFindAll(){
        List<PortalMenu> list = portalMenuServiceImpl.findAll();
        assertNotNull(list);
        System.out.println(list.size());
    }

    @Test
    void update() {
        PortalMenu portalMenu = new PortalMenu();
        portalMenu.setId(2L);
        portalMenu.setIcon("fa fa-home");
        int r = portalMenuServiceImpl.update(portalMenu);
        assertEquals(1,r);
    }

    @Test
    void insert() {

    }

    @Test
    void delete() {
        Long id = 10L;
        PortalMenu portalMenu = new PortalMenu();
        portalMenu.setId(id);
        int r = portalMenuServiceImpl.delete(portalMenu);
        assertEquals(1,r);
    }

    @Test
    void save() {
    }

    @Test
    void upload() {
    }
}