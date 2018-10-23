package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.common.TradeNumber;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.TradeMapper;
import org.ack.pojo.*;
import org.ack.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class TradeServiceImpl extends AckMapperServiceImpl<Trade, Long> implements TradeService {

    private static final Logger logger = LoggerFactory
            .getLogger(TradeServiceImpl.class);
    @Autowired
    TradeMapper tradeMapper;
    @Autowired
    TradeLogisticsService tradeLogisticsServiceImpl;
    @Autowired
    TradeItemService tradeItemServiceImpl;
    @Autowired
    AccountService accountServiceImpl;
    @Autowired
    ProductService productServiceImpl;

    @Override
    protected AckMapper<Trade, Long> getAckMapper() {
        return tradeMapper;
    }

    @Override
    public Integer insert(Trade trade, User user) {
        Date date = new Date();
        trade.setUserId(user.getId());
        logger.info("给客户{}创建订单", trade.getClientId());
        trade.setCreateTime(date);
        String number = TradeNumber.getTradeNumber(date);
        logger.info("给客户{}创建订单号{}", trade.getClientId(), number);
        trade.setNumber(number);
        trade.setStatus(0);
        int r = super.insert(trade);
        if(r == 1){
            logger.info("订单创建成功");
        } else {
            logger.info("订单创建失败");
        }
        logger.info("订单创建成功,插入物流信息");
        Logistics logistics = trade.getLogistics();
        TradeLogistics tradeLogistics = new TradeLogistics();
        tradeLogistics.setLogisticsId(logistics.getId());
        tradeLogistics.setTradeId(trade.getId());
        tradeLogistics.setCreateTime(date);
        r = tradeLogisticsServiceImpl.insert(tradeLogistics);
        if(r == 1){
            logger.info("订单-物流创建成功");
        } else {
            logger.info("订单-物流创建失败");
        }
        logger.info("订单-物流,插入产品明细");
        List<TradeItem> list = trade.getTradeItems();
        for(TradeItem tradeItem : list){
            tradeItem.setTradeId(trade.getId());
            tradeItem.setCreateTime(date);
            BigDecimal uniPrice = tradeItem.getUnitPrice();
            uniPrice = uniPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
            tradeItem.setUnitPrice(uniPrice);
            double price = uniPrice.doubleValue() * tradeItem.getAmount();
            BigDecimal totalPrice = new BigDecimal(price);
            totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
            tradeItem.setTotalPrice(totalPrice);

            r = tradeItemServiceImpl.insert(tradeItem);
            if(r == 1){
                logger.info("订单明细创建成功");
            } else {
                logger.info("订单明细创建失败");
            }
        }
        return r;
    }

    @Override
    public Trade findTradeDetail(Long id) {
        //查询订单信息
        Trade trade = tradeMapper.findById(id);
        List<TradeItem> list = tradeItemServiceImpl.findByTradeId(id);
        trade.setTradeItems(list);
        return trade;
    }

    @Override
    public Trade updateTradeInfoAndPrint(Long id) {
        logger.info("查询打印订单{}信息", id);
        Trade trade = findTradeDetail(id);
        //更新账号信息
        Account account = trade.getAccount();
        account = accountServiceImpl.findById(account.getId());
        // 设置coin
        Account newAccount = new Account();
        newAccount.setId(account.getId());
        double coin = account.getCoin().doubleValue();
        logger.info("账户{}打印前产品券{}" , account.getId(), coin);
        double totalPrice = 0.0;
        List<TradeItem> list = trade.getTradeItems();
        int r = 0;
        for(TradeItem item : list){
            totalPrice = totalPrice + item.getTotalPrice().doubleValue();
            //更新产品数量
            r = updateProduct(item);
            if(r == 1){
                logger.info("打印更新产品{}数量成功", item.getProductId());
            } else {
                logger.info("打印更新产品{}数量失败", item.getProductId());
            }
        }
        BigDecimal total = new BigDecimal(coin - totalPrice);
        newAccount.setCoin(total);
        logger.info("账户{}打印后产品券应该是:{}" , account.getId(), total);
        r = accountServiceImpl.update(newAccount);
        if(r == 1){
            trade.setAccount(newAccount);
            //更新trade状态
            Trade td = new Trade();
            td.setId(trade.getId());
            td.setStatus(2);
            r = tradeMapper.update(td);
            if(r == 1){
                logger.info("打印更新成功");
            } else {
                logger.info("打印更新失败");
            }
        } else {
            logger.info("打印更新失败");
        }
        return trade;
    }

    /**
     * 根据产品明细更新产品数量
     * @param tradeItem
     * @return
     */
    private int updateProduct(TradeItem tradeItem) {
        Product product = tradeItem.getProduct();
        product = productServiceImpl.findById(product.getId());
        Long amount = product.getAmount();
        logger.info("打印前产品{}的数量为{}", product.getId(), amount);
        Long newAmount = amount - tradeItem.getAmount();
        logger.info("打印后产品{}的数量为{}", product.getId(), newAmount);
        //更新
        product.setAmount(newAmount);
        int r = productServiceImpl.update(product);
        return r;
    }
}
