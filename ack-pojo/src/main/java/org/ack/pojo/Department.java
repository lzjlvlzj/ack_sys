package org.ack.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 部门
 * 
 * @author ack
 *
 */
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7663884625088424839L;
	
	private Integer id; // 数据库id
	
	@NotBlank(message = "{dept.name.null}")
	@Size(min = 1, max = 32, message = "{dept.name.length.illegal}")
	private String departmentName;                  // 部门名称
	
	@NotNull(message = "{dept.parentId.null}")
	private Integer parentId;                       // 上级部门
	
	@Size(min = 0, max = 200, message = "{dept.comments.length.illegal}")
	private String comments;                        // 备注
	
	private Date createTime;                        // 创建时间
	
	public Department(){
		
	}
	public Department(String departmentName, Integer parentId){
		this.departmentName = departmentName;
		this.parentId = parentId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		String s = "{" + 
				"id : " + id + 
				", departmentName : " + departmentName
				+ "}";
		return s;
	}

}
