package org.ack.persist.mapper;

import org.ack.persist.page.Page;
import org.ack.pojo.Stock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class StockMapperTest extends BaseTest {
    @Autowired
    StockMapper stockMapper;
    @Before
    public void init(){
        stockMapper =  sqlSession.getMapper(StockMapper.class);

    }

    @Test
    public void testFindInterceptorPageList(){
        Map<String,Object> map = new HashMap<>();
        Page<Stock> page = new Page<>();
        page.setCondition(map);

        List<Stock> stockLsit = stockMapper.findInterceptorPageList(page);
        for(Stock stock : stockLsit){
            System.out.println(stock);
        }
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