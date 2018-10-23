package org.ack.service;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.Client;
import org.ack.pojo.Product;
import org.ack.pojo.Returns;
import org.ack.pojo.User;

public interface ReturnsService extends AckMapperService<Returns, Long>{

    Client findClientByPhone(String phone);

    Product findProductByCode(String code);

    int insert(Returns returns , User user);
}
