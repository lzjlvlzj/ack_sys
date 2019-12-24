package org.ack.sys.cms.web.template;

import org.ack.sys.cms.pojo.User;

public class LoginUser extends User {
	private static final long serialVersionUID = 6967493146289676845L;
	private String username;
	private String password;
	private String captcha;

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

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	@Override
	public String toString() {
		return "LoginUser [username=" + username + ", password=" + password + ", captcha=" + captcha + "]";
	}

}
