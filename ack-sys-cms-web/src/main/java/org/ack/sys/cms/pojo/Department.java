package org.ack.sys.cms.pojo;

import java.util.List;

import org.ack.sys.base.pojo.BasePojo;

public class Department extends BasePojo {

    private String name;
    private Long parentId;
    private List<Department> children;

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

	public List<Department> getChildren() {
		return children;
	}

	public void setChildren(List<Department> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Department [name=" + name + ", parentId=" + parentId + ", id=" + id + ", creator=" + creator
				+ ", modifier=" + modifier + ", createTime=" + createTime + ", modifyTime=" + modifyTime
				+ ", deleteStatus=" + deleteStatus + "]";
	}
	
	

}