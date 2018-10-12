package org.ack.admin.web.controller;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.persist.page.Page;
import org.ack.pojo.Account;
import org.ack.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/account")
public class AccountController extends AckPageController<Account, Integer>{

	@Autowired
	private AccountService accountServiceImpl;

	private static final Logger logger = LoggerFactory
			.getLogger(AccountController.class);

	@Override
	public AckMapperService<Account, Integer> getService() {
		return accountServiceImpl;
	}

	@RequestMapping(value = "/list/ui")
	@AckPermission(value="account:list")
	public String listUI(HttpServletRequest request,
						 HttpServletResponse response, Model model) {
		return "account/accountList";
	}

	@RequestMapping(value = "/page")
	@AckPermission(value="account:list")
	@ResponseBody
	public Page<Account> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() Account t,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		return super.findPage(request, response, model, null, t, currentPage, count,
				orderColumn, orderType);
	}

	@RequestMapping(value = "/add/ui")
	@AckPermission(value="account:add")
	public String addUI(HttpServletRequest request,
						HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("新建账单");
		}
		return "account/accountEdit";
	}

	@RequestMapping(value = "/add")
	@AckPermission(value="account:add")
	@ResponseBody
	@Override
	public Integer insert(HttpServletRequest request,
						  HttpServletResponse response, Model model, Account t) {
		return super.insert(request, response, model, t);
	}
}
