package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.TradeItemMapper;
import org.ack.pojo.TradeItem;
import org.ack.service.TradeItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeItemServiceImpl extends AckMapperServiceImpl<TradeItem, Long> implements TradeItemService {

    private static final Logger logger = LoggerFactory
            .getLogger(TradeItemServiceImpl.class);
    @Autowired
    TradeItemMapper tradeItemMapper;
    @Override
    protected AckMapper<TradeItem, Long> getAckMapper() {
        return tradeItemMapper;
    }


}
