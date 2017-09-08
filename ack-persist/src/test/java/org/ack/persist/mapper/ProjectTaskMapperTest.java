package org.ack.persist.mapper;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ack.persist.page.Page;
import org.ack.pojo.Project;
import org.ack.pojo.ProjectTask;
import org.ack.pojo.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProjectTaskMapperTest extends BaseTest{

	ProjectTaskMapper projectTaskMapper;
	
	@Before
	public void init(){
		projectTaskMapper = sqlSession.getMapper(ProjectTaskMapper.class);
	}
	
	@Test
	public void testFindInterceptorPageList() {
		Page<ProjectTask> page = new Page<ProjectTask>();
		Map<String, Object> map = new HashMap<String, Object>();
		page.setCondition(map);
		List<ProjectTask> list = projectTaskMapper.findInterceptorPageList(page);
		for(ProjectTask pt : list){
			Project p = pt.getProject();
			User u = pt.getWorker();
			System.out.println(pt);
			System.out.println(p);
			System.out.println(u);
		}
	}
	
	@Test
	public void testInsert() {
		ProjectTask pt = new ProjectTask();
		pt.setWorkerId(20L);
		pt.setProjectId(2L);
		pt.setTask("session共享研究");
		pt.setStatus(1);
		pt.setPriority(2);
		pt.setStartTime(new Date());
		
		int n = projectTaskMapper.insert(pt);
		Assert.assertEquals(1, n);
		
		sqlSession.commit();
		
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
