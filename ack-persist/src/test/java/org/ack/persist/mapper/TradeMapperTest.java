package org.ack.persist.mapper;

import org.ack.persist.page.Page;
import org.ack.pojo.Trade;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradeMapperTest extends BaseTest {
    @Autowired
    TradeMapper tradeMapper;

    @Before
    public void setUp() {
        tradeMapper = sqlSession.getMapper(TradeMapper.class);
    }

    @Test
    public void findInterceptorPageList(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clientId", "1");
        Page<Trade> page = new Page<>();
        page.setCondition(map);
        List<Trade> list = tradeMapper.findInterceptorPageList(page);
        for (Trade trade : list) {
            System.out.println(trade);
        }
    }

    @Test
    public void insert() {


    }
}