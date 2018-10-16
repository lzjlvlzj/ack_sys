package org.ack.service.impl;

import org.ack.pojo.Flow;
import org.ack.service.FlowService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:serviceSpringContext.xml")
public class FlowServiceImplTest{
    @Autowired
    FlowService flowServiceImpl;

    @Test
    public void insert(){
        Flow flow = new Flow();
        flow.setUserId(6L);
        flow.setClientId(1);
        flow.setCause("second");
        flow.setCoinFlow(new BigDecimal(100.00));
        flow.setFlow(new BigDecimal(200.00));
        flow.setRemark("test");
        flow.setCreateTime(new Date());

        int r = flowServiceImpl.insert(flow);
        Assert.assertEquals(1, r);

    }

}