package org.ack.sys.cms.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.ack.sys.base.pojo.BasePojo;
import org.hibernate.validator.constraints.Length;

/**
 * 用户
 * @author ack
 * */
public class User extends BasePojo implements Serializable {
	
	private static final long serialVersionUID = -787753920861872090L;
	@NotBlank(message = "{user.name.notblank}")
	private String username;
	@NotEmpty(message = "密码不能为空")
    @Length(min = 1, max = 12, message = "密码长度为1-12位。")
	private String password;
	@NotEmpty(message = "新密码不能为空")
    @Length(min = 1, max = 12, message = "密码长度为6-12位。")
	private String newPassword;
	private String realName;
	@NotBlank(message = "邮箱不能为空")
	private String email;
	private int type = -1;
	private int sex = -1;   // 0 : 女 ; 1 ： 男 ; 3 ： 保密
	private Date birthday;
	private int state = -1;
	private String qq;
	@NotBlank(message = "{user.mobile.notblank}")
	private String mobile;
	private String address;
	private String avatar;
	private Long departmentId;
	private Department department;
	private List<Role> roles;
	private String departmentName;
	private String deptName;
	private List<UserRole> userRoles = new ArrayList<>();
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getDeptName() {
		if(null != this.getDepartment()) {
			deptName = this.getDepartment().getName();
		}
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRols(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", realName=" + realName
				+ ", qq=" + qq + ", mobile=" + mobile + ", createTime=" + createTime + "]";
	}
	
}
