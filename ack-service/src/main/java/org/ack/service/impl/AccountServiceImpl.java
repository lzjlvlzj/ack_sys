package org.ack.service.impl;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.AccountMapper;
import org.ack.pojo.Account;
import org.ack.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends AckMapperServiceImpl<Account, Integer> implements AccountService {
    @Autowired
    AccountMapper accountMapper;
    @Override
    protected AckMapper<Account, Integer> getAckMapper() {
        return accountMapper;
    }
}
