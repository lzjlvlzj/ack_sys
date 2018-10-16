package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.StockMapper;
import org.ack.pojo.Stock;
import org.ack.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl extends AckMapperServiceImpl<Stock, Integer> implements StockService {
    @Autowired
    StockMapper stockMapper;
    @Override
    protected AckMapper<Stock, Integer> getAckMapper() {
        return stockMapper;
    }
}
