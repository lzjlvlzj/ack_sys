package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.common.TradeNumber;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.TradeMapper;
import org.ack.pojo.*;
import org.ack.service.TradeItemService;
import org.ack.service.TradeLogisticsService;
import org.ack.service.TradeService;
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
}
