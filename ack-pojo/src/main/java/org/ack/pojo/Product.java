package org.ack.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品实体
 * 
 * @author ack
 *
 */
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3945731745209434L;
	
	private Long id;                  // 数据库id
	private String name;              // 商品名称
	private Integer type;             // 商品类型
	private BigDecimal unitPrice;     // 单价
	private String url;               // 商品图片
	private String remark;            // 备注
	private Date createTime;          // 创建时间
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "{id:" + id + ", name:" + name 
				+ ", type:" + type + ", unitPrice:" + unitPrice + ", url:"
				+ url + ", remark:" + remark + ", createTime:" + createTime
				+ "}";
	}
	

}
