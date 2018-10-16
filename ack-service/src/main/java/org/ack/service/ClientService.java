package org.ack.service;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.Account;
import org.ack.pojo.Client;
import org.ack.pojo.Flow;
import org.ack.pojo.User;

import java.util.Set;

public interface ClientService extends AckMapperService<Client, Integer>{

    /**
     * 查询所以负责人
     * @return List
     */
    Set<User> findWheelMan();

    int insert(Client t, User user);

    /***
     * 充值
     * @param flow
     * @return
     */
    Account recharge(Flow flow);
}
