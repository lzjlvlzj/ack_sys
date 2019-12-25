package org.ack.sys.base.pojo;

import java.util.Date;

/**
 * 基礎实体类
 * 
 * @author ack
 *
 */
public class BasePojo {
	protected Long id;
	protected Long creator;
	protected Long modifier;
	protected Date createTime;
	protected Date modifyTime;
	protected Integer deleteStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Long getModifier() {
		return modifier;
	}

	public void setModifier(Long modifier) {
		this.modifier = modifier;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyDate) {
		this.modifyTime = modifyDate;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	
	

}
