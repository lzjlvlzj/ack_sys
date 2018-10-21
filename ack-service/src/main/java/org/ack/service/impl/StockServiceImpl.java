package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.StockMapper;
import org.ack.pojo.Product;
import org.ack.pojo.Stock;
import org.ack.pojo.User;
import org.ack.service.ProductService;
import org.ack.service.StockService;
import org.ack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class StockServiceImpl extends AckMapperServiceImpl<Stock, Integer> implements StockService {
    private static final Logger logger = LoggerFactory
            .getLogger(StockServiceImpl.class);
    @Autowired
    StockMapper stockMapper;
    @Autowired
    ProductService productServiceImpl;
    @Autowired
    UserService userServiceImpl;



    @Override
    protected AckMapper<Stock, Integer> getAckMapper() {
        return stockMapper;
    }

    @Override
    public Product findProductByCode(String code) {
        return productServiceImpl.findProductByCode(code);
    }

    @Override
    public Set<User> findInspectors() {
        Integer roleId = 12;//质检员
        Set<User> userSet = userServiceImpl.findUserByRoleId(roleId);
        return userSet;
    }

    public int update(Stock stock){
        stock.setUpdateTime(new Date());
        //更新总量
        Product product = productServiceImpl.findById(stock.getProductId());
        logger.info("产品{}更新前总数量为:{}",product.getId(), product.getAmount());
        // 原来数量与现在的差值
        Long dif = stock.getAmount() - stock.getOldAmount();
        Long amount = product.getAmount() + dif;
        logger.info("产品{}更新后总数量为:{}",product.getId(), amount);
        product.setAmount(amount);
        int r = productServiceImpl.update(product);
        if(r == 1){
            r = stockMapper.update(stock);
            if(r == 1){
                logger.info("产品{}数量更新成功",stock.getProductId());
            }
        } else{
            logger.info("产品{}数量更新失败",stock.getProductId());
        }
        return r;
    }

    @Override
    public int insert(Stock stock, User user) {
        stock.setUserId(user.getId());
        stock.setCreateTime(new Date());
        //更新总量
        Product product = productServiceImpl.findById(stock.getProductId());
        logger.info("产品{}更新前总数量为:{}",product.getId(), product.getAmount());
        Long amount = product.getAmount() + stock.getAmount();
        logger.info("产品{}更新后总数量为:{}",product.getId(), amount);
        product.setAmount(amount);
        int r = productServiceImpl.update(product);
        if(r == 1){
           r = stockMapper.insert(stock);
           if(r == 1){
               logger.info("产品{}数量更新成功",stock.getProductId());
           }
        } else{
            logger.info("产品{}数量更新失败",stock.getProductId());
        }

        return r;
    }
}
