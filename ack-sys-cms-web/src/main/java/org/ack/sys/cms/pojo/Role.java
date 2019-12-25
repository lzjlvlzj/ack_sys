package org.ack.sys.cms.pojo;

import java.io.Serializable;

import org.ack.sys.base.pojo.BasePojo;

/**
 * 角色
 * 
 * @author ack
 *
 */
public class Role extends BasePojo implements Serializable {

	private static final long serialVersionUID = 6061619168823283893L;

	private String name;
	private String remark;

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
}
