package org.ack.sys.pojo;

import java.util.List;

import org.ack.sys.base.pojo.BasePojo;

import javax.validation.constraints.NotBlank;

public class PortalMenu extends BasePojo {
    @NotBlank(message = "{portal.menu.name.notblank}")
    private String name;

    private String icon;
    @NotBlank(message = "{portal.menu.url.notblank}")
    private String url;
    @NotBlank(message = "{portal.menu.bgurl.notblank}")
    private String bgUrl;

    private String remark;
    
    private Long parentId;
    
    private List<PortalMenu> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getBgUrl() {
        return bgUrl;
    }

    public void setBgUrl(String bgUrl) {
        this.bgUrl = bgUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<PortalMenu> getChildren() {
		return children;
	}

	public void setChildren(List<PortalMenu> children) {
		this.children = children;
	}

	@Override
    public String toString() {
        return "PortalMenu{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", remark='" + remark + '\'' +
                ", creator=" + creator +
                ", modifier=" + modifier +
                ", id=" + id +
                ", creator=" + creator +
                ", createName='" + createName + '\'' +
                ", modifier=" + modifier +
                ", createTime=" + createTime +
                '}';
    }
}