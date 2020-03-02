package org.ack.sys.cms.persist.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;

import org.ack.sys.cms.persist.BaseTest;
import org.ack.sys.cms.pojo.Dictionary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DictionaryMapperTest extends BaseTest{
	
	DictionaryMapper dictionaryMapper;
	
	@BeforeEach
	public void init() {
		dictionaryMapper = sqlSession.getMapper(DictionaryMapper.class);
	}
	
	@AfterEach
	void after() {
		close();
	}

	@Test
	void testFindPageList() {
		fail("Not yet implemented");
	}

	@Test
	void testFindInterceptorPageList() {
		fail("Not yet implemented");
	}

	@Test
	void testFind() {
		Long id = 1L;
		Dictionary dict = dictionaryMapper.findById(id);
		System.out.println(dict);
		assertNotNull(dict);
	}

	@Test
	void testInsert() {
		Date date = new Date();
		Long id = 1L;
		Dictionary dict = new Dictionary();
		dict.setKey("avatar_uri");
		dict.setValue("avatar_uri");
		dict.setCreator(id);
		dict.setCreateTime(date);
		dict.setModifier(id);
		dict.setModifyTime(date);
		dict.setDeleteStatus(0);
		
		int r = dictionaryMapper.insert(dict);
		assertEquals(1, r);
		commit();
	}

	@Test
	void testUpdate() {
		Dictionary dict = new Dictionary();
		dict.setId(1L);
		dict.setValue("avatar_uri111");
		int r = dictionaryMapper.update(dict);
		assertEquals(1, r);
		commit();
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteById() {
		fail("Not yet implemented");
	}

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testFindBySelected() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testFindByIds() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteByIds() {
		fail("Not yet implemented");
	}

	@Test
	void testBatchUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

}
