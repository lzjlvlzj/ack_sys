package org.ack.sys.base.config;

import org.ack.sys.base.core.BaseMessage;

public enum ErrorMessageConfig implements BaseMessage{
	LOGIN_CAPTCHA_INVALIDATE(400, "验证码不正确"), 
    LOGIN_USERNAME_PASSWORD_INVALIDATE(400, "用户名称或密码不正确"),
	LOGIN_NOT_AUTHORITY(401, "未登录"),
	SYS_UNSPECIFIED(500, "服务器内部错误"),
	SYS_UNAUTHORIZED(403, "没有权限访问该服务"),
    SYS_NO_SERVICE(404, "访问的资源不存在");
    private int code ;
	private String msg;
	ErrorMessageConfig(int code, String message) {
	   this.code = code;
	   this.msg = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String message) {
		this.msg = message;
	}
	@Override
	public Object getData() {
		return null;
	}
	
}
