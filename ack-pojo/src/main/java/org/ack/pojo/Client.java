package org.ack.pojo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class Client implements Serializable {
    private static final long serialVersionUID = 6970998417324640061L;
    private Integer id;
    @NotBlank(message="{client.name.null}")
    @Size(min=1, max=128, message="{client.name.length.illegal}")
    private String name;
    private String address;
    @NotBlank(message="{client.phone.null}")
    @Size(min=1, max=128, message="{client.name.phone.illegal}")
    private String phone;
    private String qq;
    private String weiXin;
    private String remark;
    private Date createTime;
    @NotNull(message="{client.userId.null}")
    private Long userId;
    private User user;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                ", weiXin='" + weiXin + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
