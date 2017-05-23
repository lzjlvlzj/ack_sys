package org.ack.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**权限信息
 * 
 * @author ack
 *
 */
public class Permission implements Serializable{
	
	private static final long serialVersionUID = 3640628650157376724L;
	
	private Integer id;                                  // 数据id
	private String permissionName;                       // 权限名称
	private String flag;                                 // 权限标识符
	private String comments;                             // 权限备注
	private Date createTime;                             // 创建时间   
	private Set<Menu> menus;                             // 菜单
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
    
	public Set<Menu> getMenus() {
		return menus;
	}
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	@Override
	public String toString() {
		String s = "{" + 
				"id : " + id + 
				", permissionName : " + permissionName + 
				", flag : " + flag 
				+ "}";
		return s;
	}
}
