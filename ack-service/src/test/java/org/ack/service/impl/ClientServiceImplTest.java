package org.ack.service.impl;

import org.ack.pojo.Client;
import org.ack.pojo.User;
import org.ack.service.ClientService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:serviceSpringContext.xml")
public class ClientServiceImplTest {
    @Autowired
    ClientService clientServiceImpl;
    @Test
    public void insert() {
        Client client = new Client();
        client.setName("张女士");
        client.setAddress("保定市xxxx");
        client.setPhone("15201514422");
        client.setQq("123456");
        client.setWeiXin("15201511121");
        client.setRemark("凝胶客户");
        client.setCreateTime(new Date());
        client.setUserId(6L);
        User user = new User();
        user.setId(7L);

        int r = clientServiceImpl.insert(client,user);
        Assert.assertEquals(1, r);

    }
}