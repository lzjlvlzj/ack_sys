package org.ack.persist.mapper;

import org.ack.persist.page.Page;
import org.ack.pojo.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;

public class ProductMapperTest extends BaseTest{
	
	@Autowired
	ProductMapper productMapper;
	
	@Before
	public void init(){
		productMapper = sqlSession.getMapper(ProductMapper.class);
	}

	@Test
	public void testFindInterceptorPageList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "洗面奶");
		Page<Product> page = new Page<Product>();
		page.setCondition(map);
		
		List<Product> list = productMapper.findInterceptorPageList(page);
		int n = list.size();
		System.out.println(n);
	}

	@Test
	public void testDeleteById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		Product p = new Product();
		p.setId(2);
		
		p = productMapper.find(p);
		
		System.out.println(p);
		
		Assert.assertNotNull(p);
	}

	@Test
	public void testInsert() {
		Product p = new Product();
		p.setName("凝胶");
		BigDecimal dec = new BigDecimal(403.4);
		p.setUnitPrice(dec);
		p.setUrl("aaa.jpg");
		p.setType(0);
		p.setRemark("测试");
		p.setCreateTime(new Date());
		
		int r = productMapper.insert(p);
		sqlSession.commit();
		close();
		Assert.assertEquals(1, r);
	}
	@Test
	public void testInsert2() {
		for(int i = 0; i < 23; i++){
			Product p = new Product();
			p.setName("凝胶" + i);
			BigDecimal dec = new BigDecimal(403.4);
			p.setUnitPrice(dec);
			p.setUrl("aaa.jpg");
			p.setType(0);
			p.setRemark("测试" +i);
			p.setCreateTime(new Date());
			productMapper.insert(p);
			sqlSession.commit();
			
		}
		close();
		
	}

	@Test
	public void testUpdate() {
		Product p = new Product();
		p.setId(2);
		p.setName("凝胶");
		BigDecimal dec = new BigDecimal(413.4);
		p.setUnitPrice(dec);
		p.setUrl("abb.jpg");
		p.setType(0);
		p.setRemark("测试");
		p.setCreateTime(new Date());
		
		int r = productMapper.update(p);
		sqlSession.commit();
		close();
		Assert.assertEquals(1, r);
	}

	@Test
	public void testDelete() {
		Product p = new Product();
		p.setId(25);
		
		int r = productMapper.delete(p);
		sqlSession.commit();
		close();
		Assert.assertEquals(1, r);
	}

}
