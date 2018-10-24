package org.ack.persist.mapper;

import org.ack.pojo.Returns;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


public class ReturnsMapperTest extends BaseTest{
    @Autowired
    ReturnsMapper returnsMapper;
    @Before
    public void init() {
        returnsMapper = sqlSession.getMapper(ReturnsMapper.class);
    }





    @Test
    public void insert() {
        Returns returns = new Returns();
        returns.setClientId(31);
        returns.setAmount(3);
        returns.setUserId(8L);
        returns.setProductId(1);
        returns.setRemark("客户店体转让");
        returns.setCreateTime(new Date());

        int r = returnsMapper.insert(returns);
        //sqlSession.commit();
        close();
        Assert.assertEquals(1, r);
    }
}
