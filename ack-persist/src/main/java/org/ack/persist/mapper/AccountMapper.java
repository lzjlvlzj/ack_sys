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
     * 根据用户查询账号信息
     * @param clientId
     * @return
     */
    Account findByClientId(int clientId);
}
