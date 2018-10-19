package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.TradeLogisticsMapper;
import org.ack.pojo.TradeLogistics;
import org.ack.service.TradeLogisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeLogisticsServiceImpl extends AckMapperServiceImpl<TradeLogistics, Long> implements TradeLogisticsService {

    private static final Logger logger = LoggerFactory
            .getLogger(TradeLogisticsServiceImpl.class);
    @Autowired
    TradeLogisticsMapper tradeLogisticsMapper;
    @Override
    protected AckMapper<TradeLogistics, Long> getAckMapper() {
        return tradeLogisticsMapper;
    }

}
