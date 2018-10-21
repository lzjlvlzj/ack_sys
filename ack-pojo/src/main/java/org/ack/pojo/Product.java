package org.ack.pojo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
	
	private Integer id;               // 数据库id
	@NotBlank(message="{product.name.null}")
	@Size(min=1, max=128, message="{product.name.length.illegal}")
	private String name;              // 商品名称
	private Integer type;             // 商品类型 0 : 正常; 1 : 赠品
	@NotNull(message="{product.unitPrice.null}")
	private BigDecimal unitPrice;     // 单价
	private String url;               // 商品图片
	private String unit;              // 商品单位
	private String spec;              // 商品规格
	@NotBlank(message="{product.code.null}")
	@Size(min=1, max=128, message="{product.code.length.illegal}")
	private String code;              // 商品编码
	private Long amount;              // 商品数量
	private String remark;            // 备注
	private Date createTime;          // 创建时间
	@NotNull(message="{product.brand.null}")
	private Integer brandId;          // 品牌名称

	private Brand brand;              // 品牌封装
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type=" + type +
				", unitPrice=" + unitPrice +
				", url='" + url + '\'' +
				", unit='" + unit + '\'' +
				", spec='" + spec + '\'' +
				", code='" + code + '\'' +
				", amount=" + amount +
				", remark='" + remark + '\'' +
				", createTime=" + createTime +
				", brandId=" + brandId +
				", brand=" + brand +
				'}';
	}
}
