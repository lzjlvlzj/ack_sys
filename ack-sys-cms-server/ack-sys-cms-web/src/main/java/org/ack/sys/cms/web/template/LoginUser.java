package org.ack.sys.cms.web.template;

import java.io.Serializable;


public class LoginUser implements Serializable {
	private static final long serialVersionUID = 6967493146289676845L;
	private String username;
	private String password;
	private String realName;
	private String captcha;
	private String avatar;
	private String token;
	private int status = 3;   // 0 ： 登录成功,  1: 验证码不正确, 2 : 用户名密码不正确, 3: 用户存在
	
	public LoginUser() {
		
	}
	
	public LoginUser(int status) {
		this.status = status;
		this.username = null;
		this.password = null;
		this.captcha = null;
		this.avatar = null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "LoginUser [username=" + username + ", password=" + password + ", captcha=" + captcha + "]";
	}

}
