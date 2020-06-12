package org.ack.sys.persist;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.ack.sys.persist.mapper.portal.PortalMenuMapper;
import org.ack.sys.pojo.PortalMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testFindAll() {
    	List<PortalMenu> list = portalMenuMapper.findAll();
    	assertNotNull(list);
    	System.out.println(list.size());
    }
    @Test
    void testInsert(){
        Long id = 1L;
        Date date = new Date();
        PortalMenu pm = new PortalMenu();
        pm.setName("地理位置");
        pm.setUrl("/position");
        pm.setRemark("地理位置");
        pm.setCreator(id);
        pm.setCreateTime(date);
        pm.setModifier(id);
        pm.setModifyTime(date);
        pm.setDeleteStatus(0);
        pm.setParentId(30L);

        int r = portalMenuMapper.insert(pm);
        assertEquals(1,r);
        System.out.println(r);
        commit();

    }
}
