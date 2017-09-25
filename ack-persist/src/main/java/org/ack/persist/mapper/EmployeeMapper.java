package org.ack.persist.mapper;

import java.util.List;

import org.ack.persist.page.Page;
import org.ack.pojo.Project;

/**
 * @author GeoStar
 *
 */
public interface EmployeeMapper {

	public List<Project> findInterceptorPageList (Page<Project> page);

}
