package org.ack.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class EmployeeJobLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 595559182086488080L;
	private Long id;                        // 数据库id
	@NotNull
	private Long userId;                    // 用户id
	private String realName;                // 用户名称
	private Long projectTaskId;             // 项目任务id
	private Long projectId;                 // 项目id
	private String projectName;             // 项目名称
	@NotNull
	private Integer departmentId;           // 部门id
	private String departmentName;          // 部门名称
	@NotBlank
	private String content;                 // 工作内容
	@NotBlank
	private String color = "";              // css 样式
	@NotNull
	private Integer cacheStatus;            // 是否是缓存 0 : 是, 1 : 不是 (是缓存的不会被当做日志查询出来)
	private Date startTime;                 // 开始时间
	private Date endTime;                   // 结束时间
	private Date createTime;                // 缓存创建时间
	
	private User user;
	private ProjectTask projectTask;
	private Department department;
	private Project project;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getProjectTaskId() {
		return projectTaskId;
	}
	public void setProjectTaskId(Long projectTaskId) {
		this.projectTaskId = projectTaskId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getCacheStatus() {
		return cacheStatus;
	}
	public void setCacheStatus(Integer cacheStatus) {
		this.cacheStatus = cacheStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ProjectTask getProjectTask() {
		return projectTask;
	}
	public void setProjectTask(ProjectTask projectTask) {
		this.projectTask = projectTask;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	

}
