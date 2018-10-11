package org.ack.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户订单
 * */
public class Order implements Serializable {
    private static final long serialVersionUID = 8397283465457448695L;

    private Long id;                // 数据库id
    private Long number;            // 订单号
    private Integer productId;      // 商品id
    private Integer clientId;       // 用户id
    private Integer amount;         // 商品数量
    private BigDecimal sum;         // 总金额
    private String remark;          // 备注
    private Date createTime;        // 创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
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
        return "Order{" +
                "id=" + id +
                ", number=" + number +
                ", productId=" + productId +
                ", clientId=" + clientId +
                ", amount=" + amount +
                ", sum=" + sum +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
