package org.ack.persist.mapper;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ack.persist.page.Page;
import org.ack.pojo.EmployeeJobLog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeeJobLogMapperTest extends BaseTest{

	private EmployeeJobLogMapper employeeJobLogMapper;
	@Before
	public void init() {
		employeeJobLogMapper = sqlSession.getMapper(EmployeeJobLogMapper.class);
	}
	
	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindPageList() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindInterceptorPageList() {
		Page<EmployeeJobLog> page = new Page<EmployeeJobLog>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", 20L);
		//map.put("departmentId", 18);
		map.put("cacheStatus", "0");
		page.setCondition(map);
		List<EmployeeJobLog> list = employeeJobLogMapper.findInterceptorPageList(page);
		System.out.println(list.size());
	}

	@Test
	public void testDeleteById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteByIds() {
		String[] ids = {"7","8"};
		int n = employeeJobLogMapper.deleteByIds(ids);
		System.out.println(n);
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByIds() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertCache() {
		EmployeeJobLog log = new EmployeeJobLog();
		log.setUserId(20L);
		log.setProjectId(2L);
		log.setDepartmentId(18);
		log.setColor("#378006");
		log.setContent("spring session 研究");
		log.setEndTime(new Date());
		log.setCacheStatus(0);
		
		int n = employeeJobLogMapper.insert(log);
		sqlSession.commit();
		System.out.println(log.getId());
		System.out.println(n);
	}
	
	@Test
	public void testInsert() {
		EmployeeJobLog log = new EmployeeJobLog();
		log.setUserId(20L);
		log.setProjectId(2L);
		log.setDepartmentId(18);
		log.setColor("#378006");
		log.setContent("spring session 研究");
		log.setEndTime(new Date());
		log.setCacheStatus(0);
		
		int n = employeeJobLogMapper.insert(log);
		Assert.assertEquals(1, n);
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
