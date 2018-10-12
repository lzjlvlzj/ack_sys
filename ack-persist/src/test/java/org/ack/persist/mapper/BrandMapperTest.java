package org.ack.persist.mapper;

import static org.junit.Assert.fail;

import java.util.Date;

import org.ack.pojo.Brand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BrandMapperTest extends BaseTest{
	
	@Autowired
	private BrandMapper brandMapper;
	
	@Before
	public void init() {
		brandMapper = sqlSession.getMapper(BrandMapper.class);
	}

	@Test
	public void testFindInterceptorPageList() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		Brand brand = new Brand();
		
		brand.setName("会满血");
		brand.setAddress("保定");
		brand.setPhone("152014546");
		brand.setRemark("慧漫香");
		brand.setCreateTime(new Date());
		
		int r = brandMapper.insert(brand);
		sqlSession.commit();
        close();
        Assert.assertEquals(1, r);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
