package org.ack.service.impl;

import org.ack.pojo.Trade;
import org.ack.pojo.TradeItem;
import org.ack.pojo.TradeLogistics;
import org.ack.pojo.User;
import org.ack.service.TradeItemService;
import org.ack.service.TradeLogisticsService;
import org.ack.service.TradeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:serviceSpringContext.xml")
public class TradeServiceImplTest {
    @Autowired
    TradeService tradeServiceImpl;
    @Autowired
    TradeItemService tradeItemServiceImpl;
    @Autowired
    TradeLogisticsService tradeLogisticsServiceImpl;
    @Test
    public void insert(){
        Trade trade = new Trade();
        trade.setUserId(6L);
        trade.setClientId(30);
        trade.setCreateTime(new Date());
        trade.setNumber("20181018-1111");
        trade.setStatus(0);
        User user = new User();
        user.setId(6L);
        //添加交易记录
        tradeServiceImpl.insert(trade, user);

        List<TradeItem> tradeItemList = new ArrayList<>();

        TradeItem tradeItem1 = new TradeItem();
        tradeItem1.setProductId(1);
        tradeItem1.setUnitPrice(new BigDecimal(125.00));
        tradeItem1.setAmount(2L);
        tradeItem1.setTotalPrice(new BigDecimal(125.00 * 2));
        tradeItem1.setCreateTime(new Date());
        tradeItem1.setTradeId(trade.getId());
        tradeItem1.setType(0);
        tradeItemList.add(tradeItem1);
        //产品明细记录
        tradeItemServiceImpl.insert(tradeItem1);
        //货运物流
        TradeLogistics tradeLogistics = new TradeLogistics();

        tradeLogistics.setLogisticsId(1L);
        tradeLogistics.setTradeId(trade.getId());
        tradeLogistics.setCreateTime(new Date());
        tradeLogistics.setRemark("first");


        tradeLogisticsServiceImpl.insert(tradeLogistics);





    }

}