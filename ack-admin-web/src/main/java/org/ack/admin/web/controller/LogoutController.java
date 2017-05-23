package org.ack.admin.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**登出
 * @author ack
 *
 */
@Controller
public class LogoutController {
	
	@RequestMapping(value = "/logout")
	public String ackLogin(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		HttpSession session  = request.getSession(false);
		if(null != session){
			session.invalidate();
		}
		return "redirect:/login/ui";
	}
}
