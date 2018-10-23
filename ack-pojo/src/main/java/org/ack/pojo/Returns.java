package org.ack.pojo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

public class Returns {
    private Long id;

    private Integer clientId;

    private Integer productId;
    @NotNull(message="{returns.product.amount.null}")
    private Integer amount;

    private Integer oldAmount;

    private Long userId;

    private String remark;

    private Date createTime;
    @NotBlank(message="{returns.client.phone.null}")
    @Size(min=1, max=18, message="{returns.client.phone.length.illegal}")
    private String clientPhone;
    @NotBlank(message="{returns.product.code.null}")
    @Size(min=1, max=64, message="{returns.product.code.length.illegal}")
    private String productCode;
    private BigDecimal coin;

    private Client client;
    private User operator;         // 记录添加人
    private User seller;           // 相关的美导老师
    private Product product;
    private Brand brand;           // 品牌
    private Account account;       // 账户

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getOperator() {
        return operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getCoin() {
        return coin;
    }

    public void setCoin(BigDecimal coin) {
        this.coin = coin;
    }

    public Integer getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(Integer oldAmount) {
        this.oldAmount = oldAmount;
    }

    @Override
    public String toString() {
        return "Returns{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", productId=" + productId +
                ", amount=" + amount +
                ", userId=" + userId +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}