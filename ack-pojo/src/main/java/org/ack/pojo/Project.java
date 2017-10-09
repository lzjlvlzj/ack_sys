package org.ack.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

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
	
	private Long id;                           // 数据id
	private Long projectId;                    // insert return value
	
	@NotBlank(message="{project.name.null}")
	@Size(min=1, max=128, message="{project.name.length.illegal}")
	private String name;                       // 工程名
	
	@NotNull(message="{project.departmentId.null}")
	private Integer departmentId;              // 部门id
	
	private String departmentName;             // 部门名称(冗余数据)
	
	@NotNull(message="{project.managerId.null}")
	private Long managerId;                    // 项目负责人id
	
	private String managerName;                // 项目负责人名称(冗余数据)
	
	private Integer type;                      // 工程状态 0 : 非公开, 1 : 公开
	
	private Integer status;                    // 工程状态 0 : 打开, 1 : 关闭
	
	private Date startTime;                    // 项目开始时间
	
	private Date endTime;                      // 项目结束时间
	
	private List<Department> cooperativeSectors;// 合作部门 
	
	@Size(min=1, max=200, message="{project.comments.length.illegal}")
	private String remark;                     // 备注
	
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public List<Department> getCooperativeSectors() {
		return cooperativeSectors;
	}
	public void setCooperativeSectors(List<Department> cooperativeSectors) {
		this.cooperativeSectors = cooperativeSectors;
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
