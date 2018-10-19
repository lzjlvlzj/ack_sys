package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.LogisticsMapper;
import org.ack.pojo.Client;
import org.ack.pojo.Logistics;
import org.ack.service.ClientService;
import org.ack.service.LogisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogisticsServiceImpl extends AckMapperServiceImpl<Logistics, Long>
		implements LogisticsService {
	private static final Logger logger = LoggerFactory
			.getLogger(LogisticsServiceImpl.class);
	@Autowired
	LogisticsMapper logisticsMapper;
	@Autowired
	ClientService clientServiceImpl;
	@Override
	protected AckMapper<Logistics, Long> getAckMapper() {
		return logisticsMapper;
	}

	@Override
	public List<Logistics> findByClientId(Integer clientId) {
		return logisticsMapper.findByClientId(clientId);
	}

	public int insert(Logistics logistics){
        //查询电话
		Client client = clientServiceImpl.findByPhone(logistics.getClientPhone());
		logger.info("给客户{}添加物流信息", client.getName());
		logistics.setClientId(client.getId());
		logistics.setCreateTime(new Date());
		return logisticsMapper.insert(logistics);
	}
}
