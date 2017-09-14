package org.ack.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 角色
 * 
 * @author ack
 *
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 4695508813957749339L;
	
	private Integer id;                        // id
	private String roleName;                   // 角色名称
	private String abbreviation;               // 简写
	private Integer viewStatus;                // 是否拥有查看所有数据的权限 : 0, 没有 1 , 有
	private String comments;                   // 备注
	private Date createTime;                   // 创建时间
	private String menuIds;                    // 菜单id
	private Set<Permission> permissions;       // 权限
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public Integer getViewStatus() {
		return viewStatus;
	}
	public void setViewStatus(Integer viewStatus) {
		this.viewStatus = viewStatus;
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
	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Role){
			Role r = (Role)obj;
			boolean b = (r.getId() == id);
			return b;
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		Role r = (Role)this;
		return r.getId().hashCode();
	}
	
	@Override
	public String toString() {
		String s = "{id : " + id + ", roleName : " + roleName + "}";
		return s;
	}

}
