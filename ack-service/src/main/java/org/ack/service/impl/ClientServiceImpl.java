package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.ClientMapper;
import org.ack.pojo.Client;
import org.ack.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends AckMapperServiceImpl<Client, Integer>
		implements ClientService {
	@Autowired
	ClientMapper clientMapper;
	@Override
	protected AckMapper<Client, Integer> getAckMapper() {
		return clientMapper;
	}

}
