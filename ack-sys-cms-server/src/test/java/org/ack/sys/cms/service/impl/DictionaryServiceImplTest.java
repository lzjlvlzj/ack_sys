package org.ack.sys.cms.service.impl;

import org.ack.sys.cms.service.DictionaryService;
import org.ack.sys.pojo.Dictionary;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DictionaryServiceImplTest {
    @Autowired
    private DictionaryService dictionaryServiceImpl;

    @Test
    void findPage() {
    }

    @Test
    void testFindPage() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void insert() {
        Dictionary dic = new Dictionary();
        dic.setKey("dept_key");
        dic.setValue("/dept/img");
        dic.setType(1);
        int r = dictionaryServiceImpl.insert(dic);
        assertEquals(1, r);
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
}