package org.ack.sys.cms.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.ack.sys.base.pojo.BasePojo;

/**菜单
 * @author ack
 *
 */
/**
 * @author ack
 *
 */
public class Menu extends BasePojo implements Serializable {

	private static final long serialVersionUID = 5674586623836846991L;
	private String name;                      // 菜单名称
	private String url;                       // vue的路由地址
	private String icon;
	private String perms;
	private Integer type;                     // 0 : 目录; 1:菜单; 2:按钮
	private Integer level;
	private Integer orderNum;
	private Long parentId;
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

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", url=" + url + ", perms=" + perms + ", type=" + type + ", level="
				+ level + ", creator=" + creator + ", createTime=" + createTime + ", modifier=" + modifier +  "]";
	}

}
