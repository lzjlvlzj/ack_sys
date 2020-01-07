package org.ack.sys.cms.web.template;

import org.ack.sys.cms.pojo.User;

/**
 * @author ack
 *
 */
public class SessionUser {
	private String token;
	private User user;
	
	public SessionUser(String token, User user) {
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
