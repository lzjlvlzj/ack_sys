package org.ack.persist.mapper;

import org.ack.persist.page.Page;
import org.ack.pojo.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountMapperTest extends BaseTest{
    @Autowired
    private AccountMapper accountMapper;

    @Before
    public void init(){
        accountMapper = sqlSession.getMapper(AccountMapper.class);
    }

    @Test
    public void testFindInterceptorPageList() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", "6");
        Page<Account> page = new Page<>();
        page.setCondition(map);
        List<Account> list = accountMapper.findInterceptorPageList(page);
        for(Account c : list){
            System.out.println(c);
        }

    }

    @Test
    public void testFindById(){
        Account account = accountMapper.findById(1);
        System.out.println(account);
        System.out.println(account.getClient());
        System.out.println(account.getClient().getName());
        Assert.assertNotNull(account);
    }


    @Test
    public void testInsert(){
        Account account = new Account();
        account.setClientId(1);
        account.setCreateTime(new Date());
        account.setFlowCase("账号初始化");
        account.setRemark("账号初始化");
        account.setUserId(7L);
        BigDecimal initVal = new BigDecimal(0.00);
        account.setBalance(initVal);
        account.setFlow(initVal);

        int r = accountMapper.insert(account);
        sqlSession.commit();
        close();
        Assert.assertEquals(1, r);

    }
}