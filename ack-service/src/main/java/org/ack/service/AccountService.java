package org.ack.service;

import org.ack.base.service.AckMapperService;
import org.ack.pojo.Account;

public interface AccountService extends AckMapperService<Account, Integer>{

    Account findByClientId(Integer clientId);

}
