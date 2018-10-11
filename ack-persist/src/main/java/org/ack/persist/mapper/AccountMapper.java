package org.ack.persist.mapper;

import org.ack.persist.AckMapper;
import org.ack.pojo.Account;

/**
 * 客户数据库接口
 *
 * @author ack
 */
public interface AccountMapper extends AckMapper<Account, Integer> {
    /**
     * 充值
     * @param account
     * @return
     */
    int recharge(Account account);
}
