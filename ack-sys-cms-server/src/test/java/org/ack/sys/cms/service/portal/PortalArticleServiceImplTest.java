package org.ack.sys.cms.service.portal;

import org.ack.sys.pojo.PortalArticle;
import org.ack.sys.pojo.PortalArticleDetail;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PortalArticleServiceImplTest {

    @Autowired
    private PortalArticleService portalArticleServiceImpl;

    @Test
    void findById() {
    }

    @Test
    void findByIds() {
    }

    @Test
    void update() {
    }

    @Test
    void insert() {
        long menuId = 5L;
        long userId = 1L;
        Date date = new Date();
        PortalArticle pa = new PortalArticle();
        pa.setTitle("测试新闻2");
        pa.setAuthor("张三");
        pa.setMenuId(menuId);
        pa.setPageView(100);
        pa.setRemark("测试");
        pa.setSource("源创");
        pa.setCreateTime(date);
        pa.setCreator(userId);
        pa.setUrl("/notice1111");
        pa.setModifyTime(date);
        pa.setModifier(userId);
        pa.setDeleteStatus(0);
        PortalArticleDetail pad = new PortalArticleDetail();
        pad.setContent("aaaaaa");
        pa.setPortalArticleDetail(pad);

        int r = portalArticleServiceImpl.insert(pa);
        assertEquals(1, r);

    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteByIds() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void batchUpdate() {
    }

    @Test
    void batchDelete() {
    }

    @Test
    void save() {
    }

    @Test
    void getDao() {
    }

    @Test
    void testInsert() {
    }

    @Test
    void testUpdate() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void publish() {
    }
}