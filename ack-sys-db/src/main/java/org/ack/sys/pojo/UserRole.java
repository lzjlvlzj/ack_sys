package org.ack.sys.pojo;

import org.ack.sys.base.pojo.BasePojo;

public class UserRole extends BasePojo{
    private Long userId;

    private Long roleId;
    
    public UserRole() {
    	
    }
    public UserRole(Long userId, Long roleId) {
    	this.userId = userId;
    	this.roleId = roleId;
    }
	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}