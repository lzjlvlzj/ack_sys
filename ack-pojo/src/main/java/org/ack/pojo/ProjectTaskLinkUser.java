package org.ack.pojo;

import java.io.Serializable;

public class ProjectTaskLinkUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8443973561611481057L;
	
	private Long id; 
	private Long projectTaskId;
	private Long userId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProjectTaskId() {
		return projectTaskId;
	}
	public void setProjectTaskId(Long projectTaskId) {
		this.projectTaskId = projectTaskId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
