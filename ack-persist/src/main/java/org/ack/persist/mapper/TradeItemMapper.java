package org.ack.persist.mapper;

import org.ack.persist.AckMapper;
import org.ack.pojo.TradeItem;

import java.util.List;

public interface TradeItemMapper extends AckMapper<TradeItem, Long> {

    int insert(TradeItem record);

    int insertSelective(TradeItem record);

    List<TradeItem> findByTradeId(Long id);
}