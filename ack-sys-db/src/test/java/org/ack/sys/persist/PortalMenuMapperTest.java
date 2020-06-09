package org.ack.sys.persist;

import org.ack.sys.persist.mapper.PortalMenuMapper;
import org.ack.sys.pojo.PortalMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PortalMenuMapperTest extends BaseTest {

    PortalMenuMapper portalMenuMapper;
    @BeforeEach
    public void init() {
        portalMenuMapper = sqlSession.getMapper(PortalMenuMapper.class);
        System.out.println(11);
    }

    @AfterEach
    void after() {
        close();
    }
    @Test
    void testInsert(){
        Long id = 1L;
        Date date = new Date();
        PortalMenu pm = new PortalMenu();
        pm.setName("首页");
        pm.setUrl("/");
        pm.setRemark("首页");
        pm.setCreator(id);
        pm.setCreateTime(date);
        pm.setModifier(id);
        pm.setModifyTime(date);
        pm.setDeleteStatus(0);

        int r = portalMenuMapper.insert(pm);
        assertEquals(1,r);
        System.out.println(r);
        commit();

    }
}
