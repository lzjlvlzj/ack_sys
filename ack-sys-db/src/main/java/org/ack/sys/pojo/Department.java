package org.ack.sys.pojo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.ack.sys.base.pojo.BasePojo;

public class Department extends BasePojo {

	@NotBlank(message = "{dept.name.notblank}")
    private String name;
	private String icon;
	@NotNull(message = "{dept.img.notblank}")
	private String img;
	@NotNull(message = "{dept.parentid.notblank}")
    private Long parentId;
	private String parentName;
    private List<Department> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	@Override
	public String toString() {
		return "Department [name=" + name + ", parentId=" + parentId + ", id=" + id + ", creator=" + creator
				+ ", modifier=" + modifier + ", createTime=" + createTime + ", modifyTime=" + modifyTime
				+ ", deleteStatus=" + deleteStatus + "]";
	}
	
	

}