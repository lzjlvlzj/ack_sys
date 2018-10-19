package org.ack.persist.mapper;

import org.ack.persist.AckMapper;
import org.ack.pojo.TradeItem;

public interface TradeItemMapper extends AckMapper<TradeItem, Long> {

    int insert(TradeItem record);

    int insertSelective(TradeItem record);

}