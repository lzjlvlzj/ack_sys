package org.ack.persist.mapper;

import org.ack.pojo.Stock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class StockMapperTest extends BaseTest {
    @Autowired
    StockMapper stockMapper;
    @Before
    public void init(){
        stockMapper =  sqlSession.getMapper(StockMapper.class);

    }

    @Test
    public void insert(){
        Stock stock = new Stock();
        stock.setAmount(1000L);
        stock.setCreateTime(new Date());
        stock.setInspectorId(8L);
        stock.setProductId(1);
        stock.setRemark("first");
        stock.setUserId(6L);

        int r = stockMapper.insert(stock);
        sqlSession.commit();
        close();
        Assert.assertEquals(1, r);
    }
}