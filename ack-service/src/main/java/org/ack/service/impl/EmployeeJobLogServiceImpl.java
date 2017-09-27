package org.ack.service.impl;

import java.util.List;
import java.util.Map;

import org.ack.base.service.impl.AckMapperServiceImpl;
import org.ack.persist.AckMapper;
import org.ack.persist.mapper.EmployeeJobLogMapper;
import org.ack.persist.page.Page;
import org.ack.pojo.EmployeeJobLog;
import org.ack.pojo.Project;
import org.ack.pojo.User;
import org.ack.service.EmployeeJobLogService;
import org.ack.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeJobLogServiceImpl extends
		AckMapperServiceImpl<EmployeeJobLog, Long> implements
		EmployeeJobLogService {

	private static final Logger logger = LoggerFactory
			.getLogger(EmployeeJobLogServiceImpl.class);
	@Autowired
	private EmployeeJobLogMapper employeeJobMapper;
	@Autowired
	ProjectService projectServiceImpl;

	@Override
	protected AckMapper<EmployeeJobLog, Long> getAckMapper() {
		return employeeJobMapper;
	}

	@Override
	public Long insertCache(EmployeeJobLog employeeJobLog) {
		int n = employeeJobMapper.insert(employeeJobLog);
		if (n > 0) {
			long id = employeeJobLog.getId();
			if (logger.isDebugEnabled()) {
				logger.debug("插入的主键id : {}", id);
			}
			return id;
		}
		return 0L;
	}

	@Override
	public List<Project> findProjectList(User user) {
		return projectServiceImpl.findUsableProjectList(user);
	}

	@Override
	public List<EmployeeJobLog> findExportList(Map<String, Object> condition) {
		if (logger.isDebugEnabled()) {
			logger.debug("查询导出excel数据");
		}
		Page<EmployeeJobLog> page = new Page<EmployeeJobLog>();
		page.setCondition(condition);
		page.setCurrentPage(1);
		page.setPageSize(31 * 100);// 最大月份  * 人数
		page.setOrderColumn("departmentId,realName");
		page.setOrderType("desc");
		List<EmployeeJobLog> list = employeeJobMapper.findInterceptorPageList(page);
		return list;
	}

}
