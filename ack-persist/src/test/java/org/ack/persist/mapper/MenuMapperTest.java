package org.ack.persist.mapper;

import java.util.Date;
import java.util.Set;

import org.ack.pojo.Menu;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MenuMapperTest extends BaseTest {

	MenuMapper menusMapper;

	@Before
	public void init() {
		menusMapper = sqlSession.getMapper(MenuMapper.class);
	}
	
	@Test
	public void testFindMenuByPId(){
		//menusMapper.findb
		Set<Menu> menus  = menusMapper.findMenuByPId(0);
		for(Menu m : menus){
			System.out.println(m);
		}
	}

	@Test
	public void testFindByIds() {
		String[] ids = { "1","2","3","4","13","14","15","16" };
		Set<Menu> menus = menusMapper.findByIds(ids);
		for (Menu m : menus) {
			System.out.println(m);
		}
	}

	@Test
	public void testInsert() {
		Menu m = new Menu();
		m.setMenuName("部门管理");
		m.setUrl("/dept/list/ui");
		m.setMenuType(0);
		m.setParentId(-1);
		m.setComments("部门管理");
		m.setCreateTime(new Date());

		int r = menusMapper.insert(m);
		sqlSession.commit();
		close();
		Assert.assertEquals(1, r);
	}

}
