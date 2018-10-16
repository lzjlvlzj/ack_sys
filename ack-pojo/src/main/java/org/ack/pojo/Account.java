package org.ack.pojo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 账簿
 * */
public class Account implements Serializable {
    private static final long serialVersionUID = -4188836418617337481L;
    private Integer id;                  // 数据库id
    @NotNull(message="{account.client.null}")
    private Integer clientId;            // 客户id
    private BigDecimal balance;          // 账号余金额
    private BigDecimal coin;             // 虚拟钱数
    private String remark;               // 备注
    private Date createTime;             // 创建时间
    private Long userId;                 // 记录添加人id
    private User user;                   // 记录创建人
    private Client client;               // 客户

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


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getCoin() {
        return coin;
    }

    public void setCoin(BigDecimal coin) {
        this.coin = coin;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", balance=" + balance +
                ", coin=" + coin +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", user=" + user +
                ", client=" + client +
                '}';
    }
}
