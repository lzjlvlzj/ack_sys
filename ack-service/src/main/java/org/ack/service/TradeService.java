package org.ack.service;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.Trade;
import org.ack.pojo.User;

public interface TradeService extends AckMapperService<Trade, Long>{
    Integer insert(Trade trade, User user);

    Trade findTradeDetail(Long id);

    Trade updateTradeInfoAndPrint(Long id);
}
