package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.FlowMapper;
import org.ack.pojo.Account;
import org.ack.pojo.Flow;
import org.ack.service.AccountService;
import org.ack.service.FlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 充值记录
 */
@Service
public class FlowServiceImpl extends AckMapperServiceImpl<Flow, Integer>
		implements FlowService {
	private static final Logger logger = LoggerFactory
			.getLogger(FlowServiceImpl.class);

	@Autowired
	FlowMapper flowMapper;

	@Autowired
	AccountService accountServiceImpl;

	@Override
	protected AckMapper<Flow, Integer> getAckMapper() {
		return flowMapper;
	}

	@Override
	public int insert(Flow flw) {
		Integer clientId = flw.getClientId();
		int r = super.insert(flw);
		if(r != 1){
			logger.error("客户{}充值失败", clientId);
			return 0;
		}

		BigDecimal flow = flw.getFlow();
		BigDecimal coinFlow = flw.getCoinFlow();
		// 根据客户id查询账户信息
		Account act = accountServiceImpl.findByClientId(clientId);
		//因为冲账所以是'加'操作
		BigDecimal originalBalance = act.getBalance();
		BigDecimal originalCoin = act.getCoin();
		//充值前余额和点券
		logger.info("客户:[{}]充值前的余额为:{},点券为:{}", clientId, originalBalance, originalCoin);
		//充值金额和点券数
		logger.info("客户:[{}]本次充值的金额额为:{}, 充值的点券为:{}", clientId, flow, coinFlow);
		//相加后金额
		BigDecimal newBalance = originalBalance.add(flw.getFlow());
		//相加后的点券
		BigDecimal newCoinFlow = originalCoin.add(coinFlow);
		act.setBalance(newBalance);
		act.setCoin(newCoinFlow);
		r = accountServiceImpl.update(act);
		if(r != 1){
			logger.error("客户{}账户充值失败", clientId);
			return 0;
		}
		act = accountServiceImpl.findByClientId(clientId);
		//充值完成后点券金额
		logger.info("客户:[{}]充值后的余额为:{},点券为:{}", clientId, act.getBalance(), act.getCoin());
		return r;
	}
}
