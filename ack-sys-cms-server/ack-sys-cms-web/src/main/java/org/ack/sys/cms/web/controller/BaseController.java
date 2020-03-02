package org.ack.sys.cms.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.ack.sys.cms.pojo.User;
import org.ack.sys.cms.web.util.WebUtil;

public class BaseController {
	public User getCurrentUser(HttpServletRequest request) {
		return WebUtil.getCurrentUser(request);
	}
}
