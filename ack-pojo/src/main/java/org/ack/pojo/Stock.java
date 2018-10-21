package org.ack.pojo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Stock {
    private Long id;


    private String code;//仓库编码预留字段

    private Integer productId;
    @NotBlank(message="{stock.product.code.null}")
    @Size(min=1, max=128, message="{stock.product.code.length.illegal}")
    private String productCode;

    @NotNull(message = "{stock.product.amount.null}")
    @Min(1L)
    @Max(99999L)
    private Long amount;
    private Long oldAmount;

    @NotNull(message = "{stock.inspector.null}")
    private Long inspectorId;

    private Long tradeId;

    private Long userId;

    private String remark;

    private Date updateTime;

    private Date createTime;

    private Product product;
    private User inspector;
    private User user;
    private Trade trade;
    private Brand brand;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(Long inspectorId) {
        this.inspectorId = inspectorId;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getInspector() {
        return inspector;
    }

    public void setInspector(User inspector) {
        this.inspector = inspector;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(Long oldAmount) {
        this.oldAmount = oldAmount;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", productId=" + productId +
                ", productCode='" + productCode + '\'' +
                ", amount=" + amount +
                ", inspectorId=" + inspectorId +
                ", tradeId=" + tradeId +
                ", userId=" + userId +
                ", remark='" + remark + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}