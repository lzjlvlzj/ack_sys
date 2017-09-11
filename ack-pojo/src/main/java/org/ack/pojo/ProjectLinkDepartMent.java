package org.ack.pojo;

import java.io.Serializable;

/** 
 * @author ack
 *
 */
public class ProjectLinkDepartMent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7219146728611074455L;
	
	private Long id;
	private Long projectId;
	private Integer departmentId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

}
