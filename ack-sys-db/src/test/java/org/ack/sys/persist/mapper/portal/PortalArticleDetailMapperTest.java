package org.ack.sys.persist.mapper.portal;

import org.ack.sys.persist.BaseTest;
import org.ack.sys.pojo.PortalArticleDetail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortalArticleDetailMapperTest extends BaseTest {

    PortalArticleDetailMapper portalArticleDetailMapper;


    @BeforeEach
    void init(){
        portalArticleDetailMapper = sqlSession.getMapper(PortalArticleDetailMapper.class);
    }

    @AfterEach
    void after() {
        close();
    }

    @Test
    void insert() {
        String text = "bbbb";
        PortalArticleDetail pad = new PortalArticleDetail();
        pad.setContent(text);
        int rt = portalArticleDetailMapper.insert(pad);
        assertEquals(1,rt);
        commit();
        System.out.println(pad);
    }
}