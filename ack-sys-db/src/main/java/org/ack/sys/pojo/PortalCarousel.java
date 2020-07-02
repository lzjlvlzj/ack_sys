package org.ack.sys.pojo;

import org.ack.sys.base.pojo.BasePojo;

public class PortalCarousel extends BasePojo {

    private String url;

    private Integer position;

    private String remark;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "PortalCarousel{" +
                "url='" + url + '\'' +
                ", position=" + position +
                ", remark='" + remark + '\'' +
                ", id=" + id +
                ", creator=" + creator +
                ", createTime=" + createTime +
                ", deleteStatus=" + deleteStatus +
                '}';
    }
}