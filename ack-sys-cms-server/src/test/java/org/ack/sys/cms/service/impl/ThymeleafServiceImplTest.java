package org.ack.sys.cms.service.impl;

import org.ack.sys.cms.service.ThymeleafService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThymeleafServiceImplTest {
    @Autowired(required = false)
    private ThymeleafService thymeleafServiceImpl;

    @Test
    public void createHtmlTest() {
        thymeleafServiceImpl.createHtml(1L);
    }

    @Test
    public void delete() {
        thymeleafServiceImpl.deleteHtml(1L);
    }
}
