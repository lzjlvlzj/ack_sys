package org.ack.pojo;

import java.io.Serializable;

public class ProjectLinkUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8443973561611481057L;
	
	private Long id; 
	private Long projectId;
	private Long userId;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
