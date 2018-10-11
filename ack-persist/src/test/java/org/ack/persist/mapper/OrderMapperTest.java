package org.ack.persist.mapper;

import org.ack.persist.page.Page;
import org.ack.pojo.Order;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapperTest extends BaseTest{

    @Autowired
    private OrderMapper orderMapper;
    @Before
    public void init() {
        orderMapper = sqlSession.getMapper(OrderMapper.class);

    }

    @Test
    public void testFindInterceptorPageList() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "李女士");
        Page<Order> page = new Page<>();
        page.setCondition(map);
        List<Order> list = orderMapper.findInterceptorPageList(page);
        for(Order c : list){
            System.out.println(c);
        }

    }

    @Test
    public void testInsertMany() {


    }

    @Test
    public void testInsert() {
        Order order = new Order();
        Date d = new Date();
        order.setAmount(10);
        order.setClientId(1);
        order.setProductId(1);
        order.setNumber(d.getTime());
        BigDecimal decimal = new BigDecimal(4034.0);
        order.setSum(decimal);
        order.setRemark("凝胶客户");
        order.setCreateTime(d);

        int r = orderMapper.insert(order);
        sqlSession.commit();
        close();
        Assert.assertEquals(1, r);
    }

}
