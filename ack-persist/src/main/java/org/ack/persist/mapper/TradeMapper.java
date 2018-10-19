package org.ack.persist.mapper;

import org.ack.persist.AckMapper;
import org.ack.pojo.Trade;

public interface TradeMapper extends AckMapper<Trade, Long> {
    int insert(Trade record);

    int insertSelective(Trade record);
}



