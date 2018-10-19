package org.ack.service;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.TradeItem;

import java.util.List;

public interface TradeItemService extends AckMapperService<TradeItem, Long>{
    List<TradeItem> findByTradeId(Long id);
}
