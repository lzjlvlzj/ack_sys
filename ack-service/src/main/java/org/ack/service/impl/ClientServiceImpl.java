package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.ClientMapper;
import org.ack.pojo.Account;
import org.ack.pojo.Client;
import org.ack.pojo.Flow;
import org.ack.pojo.User;
import org.ack.service.AccountService;
import org.ack.service.ClientService;
import org.ack.service.FlowService;
import org.ack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class ClientServiceImpl extends AckMapperServiceImpl<Client, Integer>
		implements ClientService {
	private static final Logger logger = LoggerFactory
			.getLogger(ClientServiceImpl.class);

	@Autowired
	ClientMapper clientMapper;
	@Autowired
	UserService userServiceImpl;
	@Autowired
	AccountService accountServiceImpl;
	@Autowired
	FlowService flowServiceImpl;

	@Override
	protected AckMapper<Client, Integer> getAckMapper() {
		return clientMapper;
	}

	@Override
	public Set<User> findWheelMan() {
		/**角色id固定更改数据库注意*/
		Set<User> userSet = new HashSet<>();
        //查询销售
		int roleId = 6;
		Set<User> set1 = userServiceImpl.findUserByRoleId(roleId);
		roleId = 10;
		Set<User> set2 = userServiceImpl.findUserByRoleId(roleId);
		userSet.addAll(set1);
		userSet.addAll(set2);
		return userSet;
	}

	public int insert(Client client, User user) {
		int r = super.insert(client);
		//创建客户信息
		if(r == 1){
			if(logger.isDebugEnabled()){
				logger.debug("创建客户:{}成功", client.getName());
			}
			Account account = new Account();
			account.setClientId(client.getId());
			account.setCreateTime(new Date());
			account.setRemark("账号初始化");
            account.setUserId(user.getId());
			BigDecimal initVal = new BigDecimal(0.00);
            account.setBalance(initVal);
            account.setCoin(initVal);
			r = accountServiceImpl.insert(account);
			if(r == 1){
				if(logger.isDebugEnabled()){
					logger.debug("创建客户:{}帐户成功", client.getName());
				}
			} else {
				if(logger.isDebugEnabled()){
					logger.debug("创建客户:{}帐户失败", client.getName());
				}
			}

		} else {
			r =0;
			if(logger.isDebugEnabled()){
				logger.debug("创建客户:{}失败", client.getName());
			}
		}
		//创建客户账号信息
		return r;
	}

	@Override
	public Account recharge(Flow flw) {
		Integer clientId = flw.getClientId();
		if(logger.isDebugEnabled()){
			logger.debug("给客户:{}充值", clientId);
		}
		//新建一条充值记录
		Flow newFlow = new Flow();
		newFlow.setClientId(clientId);
		newFlow.setCause(flw.getCause());
		newFlow.setCoinFlow(flw.getCoinFlow());
		newFlow.setFlow(flw.getFlow());
		newFlow.setRemark(flw.getRemark());
		newFlow.setCreateTime(new Date());
		newFlow.setUserId(flw.getUserId());
		int r = flowServiceImpl.insert(newFlow);
		if(r == 1){
			logger.info("给客户:{}充值成功", clientId);
		} else{
			logger.info("给客户:{}充值失败", clientId);
		}
		Account account = accountServiceImpl.findByClientId(clientId);
		return account;
	}
}
