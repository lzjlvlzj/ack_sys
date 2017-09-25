package org.ack.service.impl;

import static org.junit.Assert.*;

import java.util.Date;

import org.ack.pojo.EmployeeJobLog;
import org.ack.service.EmployeeJobLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:serviceSpringContext.xml")
public class EmployeeJobLogServiceImplTest {
	
	@Autowired
	private EmployeeJobLogService employeeJobLogServiceImpl;

	@Test
	public void testFindProjectTaskList() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByIds() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		EmployeeJobLog log = new EmployeeJobLog();
		log.setUserId(20L);
		log.setProjectId(2L);
		log.setDepartmentId(18);
		log.setColor("#378006");
		log.setContent("spring session 研究");
		log.setCreateTime(new Date());
		log.setCacheStatus(0);
		
		Long n = employeeJobLogServiceImpl.insertCache(log);
		System.out.println(n);
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteByIds() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAll() {
		fail("Not yet implemented");
	}

}
