package org.ack.persist.mapper;

import org.ack.pojo.Flow;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

public class FlowMapperTest extends BaseTest {

    @Autowired
    private FlowMapper flowMapper;

    @Before
    public void setUp() throws Exception {
        flowMapper = sqlSession.getMapper(FlowMapper.class);
    }

    @Test
    public void insert() {
        Flow flow = new Flow();
        flow.setCreateTime(new Date());
        flow.setRemark("first");
        flow.setFlow(new BigDecimal(1000.00));
        flow.setCoinFlow(new BigDecimal(2000.00));
        flow.setCause("teset");
        flow.setClientId(1);
        flow.setUserId(6L);
        int r = flowMapper.insert(flow);
        sqlSession.commit();
        close();
        Assert.assertEquals(1, r);

    }
}