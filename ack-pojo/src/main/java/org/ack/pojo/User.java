package org.ack.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 用户实体封装
 * 
 * @author ack
 *
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = 6892971682937366001L;

	private Long id;                       // 用户id
	private String loginName;              // 登录名称
	private String salt;                   // 密码盐值
	private String surname;                // 姓
	private String name;                   // 名字
	private Integer status;                // 用户状态 0 ： 可用 , 1 禁用
	private String password;               // 密码
	private String roleIds;                // 角色id逗号分隔
	private Date createTime;               // 创建时间
	private String comments;               // 备注
	private Integer departmentId;          // 
	private Department department;         // 
	private Set<Role> roles;               // 角色
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		String user = "{"
				+ "id : " + id
				+ ", loginName : " + loginName
				+ ", password : " + password
				+ "}";
		return user;
	}

}
