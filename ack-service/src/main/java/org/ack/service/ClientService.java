package org.ack.service;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.*;

import java.util.List;
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

    /***
     * 根据客户id查询该客户物流地址
     * @param id
     * @return
     */
    List<Logistics> findLogisticsByClientId(Integer id);

    /**
     * 填加销售单子
     * @param trade
     * @param user
     * @return
     */
    Integer insertTrade(Trade trade, User user);

    /**
     * 根据电话查询客户
     * @param clientPhone
     * @return
     */
    Client findByPhone(String clientPhone);
}
