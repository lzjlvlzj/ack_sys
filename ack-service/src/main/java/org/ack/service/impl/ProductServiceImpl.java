package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.ProductMapper;
import org.ack.pojo.Brand;
import org.ack.pojo.Product;
import org.ack.service.BrandService;
import org.ack.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends AckMapperServiceImpl<Product, Integer>
		implements ProductService {
	@Autowired
	ProductMapper productMapper;
	@Autowired
	BrandService brandServiceImpl;
	@Override
	protected AckMapper<Product, Integer> getAckMapper() {
		return productMapper;
	}

	@Override
	public List<Brand> findAllBrand() {
		return brandServiceImpl.findAll();
	}
}
