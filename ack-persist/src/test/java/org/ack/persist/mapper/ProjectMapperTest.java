package org.ack.persist.mapper;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ack.persist.page.Page;
import org.ack.pojo.Department;
import org.ack.pojo.Project;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProjectMapperTest extends BaseTest{

	ProjectMapper projectMapper;
	
	
	@Before
	public void init() {
		projectMapper = sqlSession.getMapper(ProjectMapper.class);
	}
	
	@Test
	public void testInsertReturnId(){
		Project p = new Project();
		p.setName("数据生成2");
		p.setDepartmentId(17);
		p.setManagerId(13L);
		p.setStatus(0);
		p.setType(0);
		p.setStartTime(new Date());
		p.setEndTime(new Date());
		p.setRemark("this is a test33!");
		
		projectMapper.insertReturnId(p);
		System.out.println("-------------------");
		System.out.println(p.getProjectId());
		
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
		Page<Project> page = new Page<Project>();
		Map<String, Object> map = new HashMap<String, Object>();
		page.setCondition(map);
		List<Project> list = projectMapper.findInterceptorPageList(page);
		for(Project p : list){
			System.out.println(p);
		}
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testFindById() {
		Long id = 2L;
		Project p = projectMapper.findById(id);
		List<Department> depts = p.getCooperativeSectors();
		Assert.assertEquals(2, depts.size());
	}

	@Test
	public void testInsert() {
		Project p = new Project();
		p.setName("数据生成2");
		p.setDepartmentId(19);
		p.setManagerId(13L);
		p.setStatus(0);
		p.setStartTime(new Date());
		p.setEndTime(new Date());
		p.setRemark("this is a test2!");
		
		int n = projectMapper.insert(p);
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
