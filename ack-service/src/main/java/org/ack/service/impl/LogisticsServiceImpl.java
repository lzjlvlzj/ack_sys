package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.LogisticsMapper;
import org.ack.pojo.Logistics;
import org.ack.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticsServiceImpl extends AckMapperServiceImpl<Logistics, Long>
		implements LogisticsService {
	@Autowired
	LogisticsMapper logisticsMapper;
	@Override
	protected AckMapper<Logistics, Long> getAckMapper() {
		return logisticsMapper;
	}

	@Override
	public List<Logistics> findByClientId(Integer clientId) {
		return logisticsMapper.findByClientId(clientId);
	}
}
