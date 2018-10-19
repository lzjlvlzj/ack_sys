package org.ack.persist.mapper;

import org.ack.persist.AckMapper;
import org.ack.pojo.TradeLogistics;

public interface TradeLogisticsMapper extends AckMapper<TradeLogistics, Long> {
    int deleteByPrimaryKey(Long id);

    int insert(TradeLogistics record);

    int insertSelective(TradeLogistics record);

    TradeLogistics selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeLogistics record);

    int updateByPrimaryKey(TradeLogistics record);
}