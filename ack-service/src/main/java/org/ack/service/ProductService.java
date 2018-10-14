package org.ack.service;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.Brand;
import org.ack.pojo.Product;

import java.util.List;

public interface ProductService extends AckMapperService<Product, Integer>{
    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> findAllBrand();
}
