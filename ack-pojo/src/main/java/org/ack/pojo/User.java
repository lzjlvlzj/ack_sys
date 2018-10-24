package org.ack.pojo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
	@NotBlank(message="{user.loginName.null}")
	@Size(min=1, max=128, message="{user.loginName.length.illegal}")
	private String loginName;              // 登录名称
	private String salt;                   // 密码盐值
	@NotBlank(message="{user.surname.null}")
	@Size(min=1, max=128, message="{user.surname.length.illegal}")
	private String surname;                // 姓
	@NotBlank(message="{user.name.null}")
	@Size(min=1, max=128, message="{user.name.length.illegal}")
	private String name;                   // 名字
	private Integer status;                // 用户状态 0 ： 可用 , 1 禁用
	private String oldPassword;            // 老密码
	private String password;               // 密码
	private String phone;                  // 电话
	private String address;                // 地址
	private String roleIds;                // 角色id逗号分隔
	private Date createTime;               // 创建时间
	private String comments;               // 备注
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
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id) &&
				Objects.equals(loginName, user.loginName);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, loginName);
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
