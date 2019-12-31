package org.ack.sys.cms.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ack.sys.base.common.Content;
import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.web.template.SessionUser;

public class BaseController {
	public User getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SessionUser user = (SessionUser) session.getAttribute(Content.SESSION_KEY_USER);
		if (null != user) {
			return user.getUser();
		}
		return null;
	}
}
