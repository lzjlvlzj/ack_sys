package org.ack.persist.mapper;

import java.util.List;

import org.ack.persist.page.Page;
import org.ack.pojo.ProjectTask;

/**
 * @author GeoStar
 *
 */
public interface EmployeeMapper {

	public List<ProjectTask> findInterceptorPageList (Page<ProjectTask> page);

}
