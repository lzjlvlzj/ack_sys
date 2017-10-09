package org.ack.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 角色
 * 
 * @author ack
 *
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 4695508813957749339L;
	
	private Integer id;                        // id
	
	@NotBlank(message="{role.name.null}")
	@Size(min=1, max=32, message="{role.name.length.illegal}")
	private String roleName;                   // 角色名称
	
	@NotNull(message="{role.weight.null}")
	private Integer weight;                    // 权重(权重高的可以任命权重低的角色)
	
	@NotBlank
	@Size(min=1, max=32, message="{role.name.null}")
	@Pattern(regexp="^[0-9A-Z_]+", message="")
	private String abbreviation;               // 简写
	
	private Integer viewStatus;                // 是否拥有查看所有数据的权限 : 0, 没有 1 , 有
	
	@Size(min=1, max=200, message="{role.comments.length.illegal}")
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
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Role){
			Role r = (Role)obj;
			if(null == r.getId() || null == id){
				return super.equals(obj);
			}
			boolean b = (r.getId() == id);
			return b;
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		Role r = (Role)this;
		if(null == r.getId()){
			return super.hashCode();
		}
		return r.getId().hashCode();
	}
	
	@Override
	public String toString() {
		String s = "{id : " + id + ", roleName : " + roleName + "}";
		return s;
	}

}
