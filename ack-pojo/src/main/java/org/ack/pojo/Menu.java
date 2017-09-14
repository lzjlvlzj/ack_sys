package org.ack.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 菜单表
 * 
 * @author ack
 *
 */
public class Menu implements Serializable,Comparable<Menu> {

	private static final long serialVersionUID = 907686414602177841L;
	
	private Integer id;                      // 数据id
	private String menuName;                 // 菜单名称 
	private String url;                      // 页面url
	private Integer menuType;                // 菜单类型 0 : 普通功能, 1 : button
	private String css;                      // 样式
	private String domId;                    // 菜单domid
	private Integer parentId;                // 菜单父id
	private Integer menuLevel;               // 菜单等级
	private String comments;                 // 菜单
	private String permission;				 // 权限字符串
	private Date createTime;                 // 菜单创建时间
	private List<Menu> childMenus;           // 子菜单
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getMenuType() {
		return menuType;
	}
	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	public String getDomId() {
		return domId;
	}
	public void setDomId(String domId) {
		this.domId = domId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public List<Menu> getChildMenus() {
		return childMenus;
	}
	public void setChildMenus(List<Menu> childMenus) {
		this.childMenus = childMenus;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Menu){
			Menu m = (Menu) obj;
			boolean b = (m.getId() == id);
			return b;
		}
		return super.equals(obj);
	}
	@Override
	public int hashCode() {
		Menu m = (Menu)this;
		return m.getId().hashCode();
	}
	
	@Override
	public String toString() {
		String s = "{" + 
				"id : " + id + 
				",menuName : " + menuName + 
				",url : " + url +
				"}";
		return s;
	}
	@Override
	public int compareTo(Menu o) {
		Integer oid = o.getId();
		if(oid > id){
			return -1;
		} 
		if(oid < id){
			return 1;
		}
		return 0;
	}

}
