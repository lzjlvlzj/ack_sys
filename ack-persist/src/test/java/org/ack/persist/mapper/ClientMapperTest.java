package org.ack.persist.mapper;

import org.ack.persist.page.Page;
import org.ack.pojo.Client;
import org.ack.pojo.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientMapperTest  extends BaseTest{

    @Autowired
    private ClientMapper clientMapper;
    @Before
    public void init() {
        clientMapper = sqlSession.getMapper(ClientMapper.class);

    }
    @Test
    public void testFindClientByUser(){
        User user = new User();
        user.setId(6L);
        List<Client> list = clientMapper.findClientByUser(user);
        for(Client c : list){
            System.out.println(c);
        }

    }


    @Test
    public void testFindInterceptorPageList() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "李女士");
        Page<Client> page = new Page<>();
        page.setCondition(map);
        List<Client> list = clientMapper.findInterceptorPageList(page);
        for(Client c : list){
            System.out.println(c);
        }

    }

    @Test
    public void testInsertMany() {
        for(int i = 0 ; i < 20; i++){
            Client client = new Client();
            client.setName("李女士" + i);
            client.setAddress("保定市xxxx" + i);
            client.setPhone("15201514454");
            client.setQq("123456");
            client.setWeiXin("15201511121");
            client.setRemark("凝胶客户");
            client.setCreateTime(new Date());
            clientMapper.insert(client);
        }
        sqlSession.commit();
        close();

    }

    @Test
    public void testInsert() {
        Client client = new Client();
        client.setName("李女士");
        client.setAddress("保定市xxxx");
        client.setPhone("15201514454");
        client.setQq("123456");
        client.setWeiXin("15201511121");
        client.setRemark("凝胶客户");
        client.setCreateTime(new Date());

        int r = clientMapper.insert(client);
        sqlSession.commit();
        close();
        Assert.assertEquals(1, r);
    }

}
