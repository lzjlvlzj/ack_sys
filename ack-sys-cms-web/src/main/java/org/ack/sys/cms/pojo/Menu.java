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
	private Integer id;
	private String name;                      // 菜单名称
	private String url;                       // vue的路由地址
	private String icon;
	private String perms;
	private Integer type;                     // 0 : 目录; 1:菜单; 2:按钮
	private Integer level;
	private Integer orderNum;
	private Integer creator;
	private Date createTime;
	private Integer modifier;
	private Date updateTime;
	private Integer parentId;
	private List<Menu> children;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
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
				+ level + ", creator=" + creator + ", createTime=" + createTime + ", modifier=" + modifier
				+ ", updateTime=" + updateTime + "]";
	}

}
