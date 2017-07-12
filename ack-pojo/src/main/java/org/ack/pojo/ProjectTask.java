package org.ack.pojo;

import java.io.Serializable;
import java.util.Date;

/**项目任务细节
 * 
 * @author GeoStar
 *
 */
public class ProjectTask implements Serializable {

	private static final long serialVersionUID = 276438650309506540L;
	
	private Long id;                         // 数据库id
	private Long projectId;                  // 项目id
	private String task;                     // 项目明细相
	private Long workerId;                   // 责任人id
	private Integer status;                  // 状态 0 : 完成 , 1 : 进行中
	private Integer priority;                // 优先级 1 ,2 ,3 ,4 ... 
	private Date startTime;                  // 开始时间
	private Date endTime;                    // 结束时间
	
	private Project project;                 // 项目
	private User worker;                     // 责任人
	
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
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Long getWorkerId() {
		return workerId;
	}
	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
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
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public User getWorker() {
		return worker;
	}
	public void setWorker(User worker) {
		this.worker = worker;
	}
	
    @Override
    public String toString() {
    	String s = "{id : " + id + 
    			", projectId : " + projectId + 
    			", workerId : " + workerId + 
    			", task : " + task + 
    			", status : " + status + 
    			", priority : " + priority + 
    			", startTime : " + startTime +
    			"}";
    	return s;
    }
}
