package org.ack.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目实体
 * 
 * @author ack
 *
 */
public class Project implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6315298765202641582L;
	
	private Long id;                          // 数据id
	private String name;                      // 工程名
	private Integer departmentId;             // 部门id
	private String departmentName;            // 部门名称(冗余数据)
	private Long managerId;                   // 项目负责人id
	private String managerName;               // 项目负责人名称(冗余数据)
	private Integer status;                   // 工程状态 0 : 打开, 1 : 关闭
	private Date startTime;                   // 项目开始时间
	private Date endTime;                     // 项目结束时间
	private String remark;                    // 备注
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		String s = "{" + 
				"id : " + id +
				", name : " + name +
				", departmentId : " + departmentId +
				", managerId : " + managerId +
				", status : " + status +
				", startTime : " + startTime + 
				", endTime : " + endTime + 
				"}";
		
		return s;
	}
    
}
