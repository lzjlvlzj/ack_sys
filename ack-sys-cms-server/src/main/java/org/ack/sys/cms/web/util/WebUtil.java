package org.ack.sys.cms.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ack.sys.base.common.Content;
import org.ack.sys.pojo.User;
import org.ack.sys.cms.web.template.SessionUser;

public class WebUtil {

	public static User getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SessionUser user = (SessionUser) session.getAttribute(Content.SESSION_KEY_USER);
		if (null != user) {
			return user.getUser();
		}
		return null;
	}
}
