package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.ProductMapper;
import org.ack.pojo.Product;
import org.ack.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends AckMapperServiceImpl<Product, Long>
		implements ProductService {
	@Autowired
	ProductMapper productMapper;
	@Override
	protected AckMapper<Product, Long> getAckMapper() {
		return productMapper;
	}

}
