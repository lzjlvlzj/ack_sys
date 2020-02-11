package org.ack.sys.cms.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.ack.sys.base.pojo.BasePojo;

/**菜单
 * @author ack
 *
 */
public class Menu extends BasePojo implements Serializable {

	private static final long serialVersionUID = 5674586623836846991L;
	@NotBlank(message = "{menu.name.notblank}")
	private String name;                      // 菜单名称
	private String url;                       // vue的路由地址
	private String icon;
	private String perms;
	private Integer type = -1;                     // 0 : 目录; 1:菜单; 2:按钮
	private Integer level = -1;
	private Integer orderNum = -1;
	@NotNull(message = "{menu.parentid.notblank}")
	private Long parentId;
	private String parentName;
	private List<Menu> children;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	
	@Override
	public int hashCode() {
		Menu m = (Menu)this;
		if(null == m.getId()){
			return super.hashCode();
		}
		return m.getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Menu){
			Menu m = (Menu) obj;
			if(null == m.getId() || null == id){
				return super.equals(obj);
			}
			boolean b = (m.getId() == id);
			return b;
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", url=" + url + ", perms=" + perms + ", type=" + type + ", level="
				+ level + ", creator=" + creator + ", createTime=" + createTime + ", modifier=" + modifier +  "]";
	}

}
