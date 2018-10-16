package org.ack.pojo;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class Flow {
    private Long id;
    @NotNull(message="{flow.client.null}")
    private Integer clientId;
    @NotNull(message="{flow.flow.null}")
    private BigDecimal flow;
    @NotNull(message="{flow.coin.null}")
    private BigDecimal coinFlow;
    @NotNull(message="{flow.cause.null}")
    private String cause;

    private Long userId;

    private String remark;

    private Date createTime;

    private Client client;

    private User user;

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

    public BigDecimal getFlow() {
        return flow;
    }

    public void setFlow(BigDecimal flow) {
        this.flow = flow;
    }

    public BigDecimal getCoinFlow() {
        return coinFlow;
    }

    public void setCoinFlow(BigDecimal coinFlow) {
        this.coinFlow = coinFlow;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Flow{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", flow=" + flow +
                ", coinFlow=" + coinFlow +
                ", cause='" + cause + '\'' +
                ", userId=" + userId +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}