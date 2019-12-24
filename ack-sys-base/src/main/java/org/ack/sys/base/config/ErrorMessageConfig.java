package org.ack.sys.base.config;

public enum ErrorMessageConfig {
	LOGIN_CAPTCHA_INVALIDATE(400, "验证码不正确"), 
    LOGIN_USERNAME_PASSWORD_INVALIDATE(400, "用户名称或密码不正确");
    private int code ;
	private String message;
	ErrorMessageConfig(int code, String message) {
	   this.code = code;
	   this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
