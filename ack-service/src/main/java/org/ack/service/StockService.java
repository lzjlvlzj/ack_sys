package org.ack.service;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.Product;
import org.ack.pojo.Stock;
import org.ack.pojo.User;

import java.util.Set;

public interface StockService extends AckMapperService<Stock, Integer>{

    Product findProductByCode(String id);

    Set<User> findInspectors();

    int insert(Stock t, User user);
}
