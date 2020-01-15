package org.ack.sys.cms.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.ack.sys.base.pojo.BasePojo;

/**
 * 角色
 * 
 * @author ack
 *
 */
public class Role extends BasePojo implements Serializable {

	private static final long serialVersionUID = 6061619168823283893L;
	@NotBlank(message = "{role.name.notblank}")
	private String name;
	private String remark;
	private List<RoleMenu> roleMenus = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<RoleMenu> getRoleMenus() {
		return roleMenus;
	}

	public void setRoleMenus(List<RoleMenu> roleMenus) {
		this.roleMenus = roleMenus;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", remark=" + remark + "]";
	}
	
}
