package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.BrandMapper;
import org.ack.pojo.Brand;
import org.ack.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl extends AckMapperServiceImpl<Brand, Integer>
		implements BrandService {
	@Autowired
	BrandMapper clientMapper;
	@Override
	protected AckMapper<Brand, Integer> getAckMapper() {
		return clientMapper;
	}

}
