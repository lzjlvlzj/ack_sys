package org.ack.sys.cms.pojo;

import org.ack.sys.base.pojo.BasePojo;

public class Department extends BasePojo {

    private String name;
    private Long parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "Department [name=" + name + ", parentId=" + parentId + ", id=" + id + ", creator=" + creator
				+ ", modifier=" + modifier + ", createTime=" + createTime + ", modifyTime=" + modifyTime
				+ ", deleteStatus=" + deleteStatus + "]";
	}
	
	

}