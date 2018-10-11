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
            System.out.println(c.getUser().getSurname());
        }

    }

    @Test
    public void testFindById(){
        Account account = accountMapper.findById(1);
        System.out.println(account);
        System.out.println(account.getClient());
        System.out.println(account.getProduct());

        Assert.assertNotNull(account);
    }


    @Test
    public void testInsert(){
        Account account = new Account();
        account.setProductId(1L);
        account.setAmount(10);
        BigDecimal flow = new BigDecimal(2000.0);
        account.setFlow(flow);
        BigDecimal sum = new BigDecimal(30000.0);
        account.setSum(sum);
        account.setClientId(1);
        account.setFlowCase("洗面奶客户");
        account.setRemark("老客户");
        account.setCreateTime(new Date());

        int r = accountMapper.insert(account);
        sqlSession.commit();
        close();
        Assert.assertEquals(1, r);

    }
}