package org.ack.service.impl;


import org.ack.pojo.TradeItem;
import org.ack.service.TradeItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:serviceSpringContext.xml")
public class TradeItemServiceImplTest {

    @Autowired
    TradeItemService tradeItemServiceImpl;

    @Test
    public void findByTradeId(){
       List<TradeItem> list =  tradeItemServiceImpl.findByTradeId(13L);
       System.out.println(list.size());
    }

}
