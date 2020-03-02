package org.ack.sys.cms.pojo;

import javax.validation.constraints.NotBlank;

import org.ack.sys.base.pojo.BasePojo;

/**
 * 数据字典
 * 
 * @author ack
 *
 */
public class Dictionary extends BasePojo {
	@NotBlank(message = "{dict.key.notblank}")
	private String key;
	@NotBlank(message = "{dict.value.notblank}")
	private String value;
	private Integer type = -1;
	private String remark;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value == null ? null : value.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Dictionary [key=" + key + ", value=" + value + ", type=" + type + ", remark=" + remark + ", id=" + id
				+ ", creator=" + creator + ", createName=" + createName + "]";
	}
	

}