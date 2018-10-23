package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.ReturnsMapper;
import org.ack.pojo.*;
import org.ack.service.AccountService;
import org.ack.service.ClientService;
import org.ack.service.ProductService;
import org.ack.service.ReturnsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class ReturnsServiceImpl extends AckMapperServiceImpl<Returns, Long>
		implements ReturnsService {

	private static final Logger logger = LoggerFactory
			.getLogger(ReturnsServiceImpl.class);
	@Autowired
	ReturnsMapper returnsMapper;
	@Autowired
	ClientService clientServiceImpl;
	@Autowired
	ProductService productServiceImpl;
	@Autowired
	AccountService accountServiceImpl;

	@Override
	protected AckMapper<Returns, Long> getAckMapper() {
		return returnsMapper;
	}

	@Override
	public Client findClientByPhone(String phone) {
		return clientServiceImpl.findByPhone(phone);
	}

	@Override
	public Product findProductByCode(String code) {
		return productServiceImpl.findProductByCode(code);
	}


	@Override
	public int update(Returns returns) {
		Integer clientId = returns.getClientId();
		Account account = accountServiceImpl.findByClientId(clientId);
		Product product = productServiceImpl.findById(returns.getProductId());
		int r = -1;
		//计算出之前退货总价格 和 现在差值
		logger.info("更新退货{}的前的产品券{}", returns.getId(), returns.getCoin());
		double con = product.getUnitPrice().doubleValue() * returns.getAmount();
		double co = con - returns.getCoin().doubleValue();
		BigDecimal offsetCoin = new BigDecimal(co);
		offsetCoin = offsetCoin.setScale(2, BigDecimal.ROUND_HALF_UP);
		logger.info("更新退货{}的后的产品券{}", returns.getId(), offsetCoin);
		// 计算数量的差值
		logger.info("更新退货{}的前的数量{}", returns.getId(), returns.getOldAmount());
		Integer newAmount = returns.getAmount() - returns.getOldAmount();
		logger.info("更新退货{}的后的数量{}", returns.getId(), newAmount);

		//更新产品表数量
		r = updateProduct(returns, product, newAmount);
		if(r == 1){
			logger.info("更新产品{}的数量成功", returns.getProductId());
		} else {
			logger.info("更新产品{}的数量失败", returns.getProductId());
		}
		//更新账户信息
		r = updateAccount(returns, account, offsetCoin);
		if(r == 1){
			logger.info("更新客户{}的账户信息成功", returns.getClientId());
		} else {
			logger.info("更新客户{}的账户信息失败", returns.getClientId());
		}
		//更新退货表
		BigDecimal newCoin = new BigDecimal(con);
		newCoin = newCoin.setScale(2, BigDecimal.ROUND_HALF_UP);
		returns.setCoin(newCoin);
		r = returnsMapper.update(returns);
		return r;
	}

	public int insert(Returns returns , User user) {
		logger.info("修改退货客户{}信息,产品{}",returns.getClientId(), returns.getProductId());
		returns.setUserId(user.getId());

		Integer clientId = returns.getClientId();
		Account account = accountServiceImpl.findByClientId(clientId);
		Product product = productServiceImpl.findById(returns.getProductId());
		double co = product.getUnitPrice().doubleValue() * returns.getAmount();
		BigDecimal coin = new BigDecimal(co);
		int r = -1;
		//更新产品表数量
		r = updateProduct(returns, product, returns.getAmount());
		if(r == 1){
			logger.info("更新产品{}的数量成功", returns.getProductId());
		} else {
			logger.info("更新产品{}的数量失败", returns.getProductId());
		}
		//更新账户信息
		r = updateAccount(returns, account, coin);
		if(r == 1){
			logger.info("更新客户{}的账户信息成功", returns.getClientId());
		} else {
			logger.info("更新客户{}的账户信息失败", returns.getClientId());
		}
		//插入退货表
		returns.setCoin(coin);
		returns.setCreateTime(new Date());
		r = returnsMapper.insert(returns);
		return r;
	}

	/**
	 * 更新账户信息
	 * @param returns
	 * @return
	 */
	private int updateAccount(Returns returns, Account account , BigDecimal money) {
		logger.info("账户{}的产品券更新前为{}", account.getId(), account.getCoin());
		// 计算产品coin
		logger.info("账户{}的要加产品券为{}", account.getId(), money);
		double moneyCoin = account.getCoin().doubleValue() + money.doubleValue();
		BigDecimal coin = new BigDecimal(moneyCoin);
		coin = coin.setScale(2, BigDecimal.ROUND_HALF_UP);
		logger.info("账户{}的产品券更新后为{}", account.getId(), coin.doubleValue());
        account.setCoin(coin);
        int r = accountServiceImpl.update(account);
		return r;
	}

	/**
	 * 更新产品信息
	 * @param returns
	 * @return
	 */
	private int updateProduct(Returns returns, Product product, Integer count) {
		logger.info("产品{}的产品数量更新前为{}", returns.getProductId(), product.getAmount());
		//计算amount
		long amount = product.getAmount();
		amount = amount + count;
		product.setAmount(amount);
		int r = productServiceImpl.update(product);
		return r;
	}
}
