package org.ack.sys.portal.service.impl;

import org.ack.sys.portal.pojo.Card;
import org.ack.sys.portal.service.CardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardServiceImplTest {
    @Autowired
    private CardService cardServiceImpl;

    @Test
    public void testFindCardList(){
       List<Card> list =  cardServiceImpl.findCard();
       assertNotNull(list);
       int size = list.size();
       System.out.println(size);
    }

}