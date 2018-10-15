package org.ack.admin.web.controller;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.common.message.MessageEntry;
import org.ack.persist.page.Page;
import org.ack.pojo.Account;
import org.ack.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

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

	@RequestMapping(value = "/id/{id}")
	@ResponseBody
	public Account findById(HttpServletRequest request,
						   HttpServletResponse response, Model model, @PathVariable Integer id) {
		return accountServiceImpl.findById(id);
	}

	@RequestMapping(value = "/del/{id}")
	@AckPermission(value = "account:delete")
	@ResponseBody
	public Integer deleteById(HttpServletRequest request,
							  HttpServletResponse response, Model model, @PathVariable Integer id) {
		return super.deleteById(request, response, model, id);
	}

	@RequestMapping("/edit/ui/{id}")
	@AckPermission(value = "account:update")
	public String eidtUI(@PathVariable Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("修改项目:{}", id);
		}
		return "account/accountEdit";
	}

	@RequestMapping(value = "/edit")
	@AckPermission(value = "account:update")
	@ResponseBody
	public MessageEntry edit(HttpServletRequest request,
							 HttpServletResponse response, Model model,
							 @Valid Account account, BindingResult result) {
		boolean b = result.hasErrors();
		if (b) {
			FieldError fe = result.getFieldError();
			String msg = fe.getDefaultMessage();
			if (logger.isDebugEnabled()) {
				logger.debug("表单验证错误  : {}", msg);
			}
			return new MessageEntry(0, msg);
		}
		account.setCreateTime(new Date());
		Integer r = accountServiceImpl.update(account);

		return new MessageEntry(r, "");
	}

}
