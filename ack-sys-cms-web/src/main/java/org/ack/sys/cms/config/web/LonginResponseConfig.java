package org.ack.sys.cms.config.web;

import org.ack.sys.base.core.BaseMessage;

public enum LonginResponseConfig implements BaseMessage {
	LOGIN_CAPTCHA_INVALIDATE(400, "验证码不正确"), 
	LOGIN_USERNAME_PASSWORD_INVALIDATE(400, "用户名称或密码不正确"),
	LOGIN_USER_NOT_EXISIT(400, "用户名不存在"),
	LOGIN_NOT_AUTHORITY(401, "未登录");

	private int code;
	private String msg;
	private Object data;

	LonginResponseConfig(int code, String message) {
		this.code = code;
		this.msg = message;
        this.data = "";
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

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
