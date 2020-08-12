package org.ack.sys.persist.mapper.portal;

import org.ack.sys.base.persist.page.Page;
import org.ack.sys.persist.BaseTest;
import org.ack.sys.pojo.PortalArticle;
import org.ack.sys.pojo.PortalArticleDetail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PortalArticleMapperTest extends BaseTest {

    PortalArticleMapper portalArticleMapper;
    PortalArticleDetailMapper portalArticleDetailMapper;

    @BeforeEach
    public void init() {
        portalArticleMapper = sqlSession.getMapper(PortalArticleMapper.class);
        portalArticleDetailMapper = sqlSession.getMapper(PortalArticleDetailMapper.class);
    }

    @AfterEach
    void after() {
        close();
    }

    @Test
    void testFindByTitle(){
        String title = "重大先问";
        int count = portalArticleMapper.findByTitle(title);
        assertEquals(1, count);
    }

    @Test
    void testFindInterceptorPageList(){
        Page<PortalArticle> page = new Page<PortalArticle>();
        page.setPageSize(3);
        //Date date = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        //map.put("name","北京协和医院2020年五一门诊工作安排");
        //map.put("startTime", date);
        //map.put("endTime", date);
        page.setCondition(map);
        List<PortalArticle> list = portalArticleMapper.findInterceptorPageList(page);
        assertNotNull(list);
        System.out.println(list.size());
    }

    @Test
    void testFindByMenuId(){
        Long[] ids = new Long[3];
        ids[0] = 4L;
        ids[1] = 5L;
        ids[2] = 6L;
        List<PortalArticle> list = portalArticleMapper.findByMenuId(ids);
        assertNotNull(list);
        System.out.println(list.size());
    }
    @Test
    void insert() {
        long menuId = 5L;
        long userId = 1L;
        Date date = new Date();
        PortalArticle pa = new PortalArticle();
        pa.setTitle("这个是个测试");
        pa.setAuthor("张三");
        pa.setMenuId(menuId);
        pa.setPageView(100);
        pa.setRemark("测试");
        pa.setSource("源创");
        pa.setCreateTime(date);
        pa.setCreator(userId);
        pa.setUrl("/notice");
        pa.setModifyTime(date);
        pa.setModifier(userId);
        pa.setDeleteStatus(0);


        PortalArticleDetail pad = new PortalArticleDetail("bbbbbbbssss2222");
        portalArticleDetailMapper.insert(pad);
        pa.setDetailId(pad.getId());
        int r  = portalArticleMapper.insert(pa);
        assertEquals(1, r);
        commit();
    }
}