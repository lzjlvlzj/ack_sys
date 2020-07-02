package org.ack.sys.portal.service.impl;

import org.ack.sys.base.persist.page.Page;
import org.ack.sys.pojo.PortalArticle;
import org.ack.sys.portal.service.PortalArticleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PortalArticleServiceImplTest {
    @Autowired
    private PortalArticleService portalArticleServiceImpl;
    @Test
    void getDao() {
    }

    @Test
    void findPage() {

    }

    @Test
    void testFindPage() {
        Page<PortalArticle> page = new Page<PortalArticle>();
        page.setPageSize(3);
        //Date date = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("menuId",4L);
        //map.put("startTime", date);
        //map.put("endTime", date);
        page.setCondition(map);
        page = portalArticleServiceImpl.findPage(page);
        assertNotNull(page.getResult());
        System.out.println(page.getResult().size());
    }

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
    void getPageDao() {
    }

    @Test
    void findByMenuId() {
    }
}