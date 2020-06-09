package org.ack.sys.pojo;

import org.ack.sys.base.pojo.BasePojo;

public class PortalMenu extends BasePojo {

    private String name;

    private String icon;

    private String url;

    private String remark;

    private Integer creator;

    private Integer modifier;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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