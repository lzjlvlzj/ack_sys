package org.ack.persist.mapper;

import org.ack.pojo.Logistics;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class LogisticsMapperTest extends BaseTest {

    @Autowired
    LogisticsMapper logisticsMapper;

    @Before
    public void init() {
        logisticsMapper = sqlSession.getMapper(LogisticsMapper.class);
    }

    @Test
    public void insert() {
        Logistics logistics = new Logistics();
        logistics.setClientId(1);
        logistics.setAddress("保定市xxxx街道");
        logistics.setPhone("1385562222");
        logistics.setPostalCode("100000");
        logistics.setPostMan("李师傅");
        logistics.setVehicle("冀B51222");
        logistics.setCreateTime(new Date());

        int r =  logisticsMapper.insert(logistics);
        sqlSession.commit();
        close();
        Assert.assertEquals(1, r);
    }
}