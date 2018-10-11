package org.ack.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 账簿
 * */
public class Account implements Serializable {
    private static final long serialVersionUID = -4188836418617337481L;
    private Integer id;                  // 数据库id
    private Integer clientId;            // 客户id
    private Long productId;              // 产品id
    private Integer amount;              // 产品数量
    private BigDecimal sum;              // 账号总金额
    private BigDecimal flow;             // 资金流向: 进账为正,出账为负
    private String flowCase;             // 资金流转原因
    private String remark;               // 备注
    private Date createTime;             // 创建时间

    private User user;                   // 负责人
    private Client client;
    private Product product;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public BigDecimal getFlow() {
        return flow;
    }

    public void setFlow(BigDecimal flow) {
        this.flow = flow;
    }

    public String getFlowCase() {
        return flowCase;
    }

    public void setFlowCase(String flowCase) {
        this.flowCase = flowCase;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", productId=" + productId +
                ", amount=" + amount +
                ", sum=" + sum +
                ", flow=" + flow +
                ", flowCase='" + flowCase + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
