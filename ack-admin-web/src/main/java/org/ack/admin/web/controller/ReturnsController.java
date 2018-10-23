package org.ack.admin.web.controller;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.common.message.MessageEntry;
import org.ack.persist.page.Page;
import org.ack.pojo.Client;
import org.ack.pojo.Product;
import org.ack.pojo.Returns;
import org.ack.pojo.User;
import org.ack.service.ReturnsService;
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

@Controller
@RequestMapping("/returns")
public class ReturnsController extends AckPageController<Returns, Long>{
	
	@Autowired
    private ReturnsService returnsServiceImpl;
	private static final Logger logger = LoggerFactory
			.getLogger(ReturnsController.class);

	@Override
	public AckMapperService<Returns, Long> getService() {
		return returnsServiceImpl;
	}

	@RequestMapping(value = "/list/ui")
	@AckPermission(value="returns:list")
	public String listUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "returns/returnsList";
	}
	
	@RequestMapping(value = "/page")
	@AckPermission(value="returns:list")
	@ResponseBody
	public Page<Returns> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() Returns t,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		 return super.findPage(request, response, model, null, t, currentPage, count,
				orderColumn, orderType);
	}
	
	@RequestMapping(value = "/add/ui")
	@AckPermission(value="returns:add")
	public String addUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("新建品牌");
		}
		return "returns/returnsEdit";
	}

	@RequestMapping(value = "/add")
	@AckPermission(value="returns:add")
	@ResponseBody
	public MessageEntry insert(HttpServletRequest request,
			HttpServletResponse response, Model model,
			@Valid Returns t, BindingResult result) {
		// validator
		boolean b = result.hasErrors();
		if (b) {
			FieldError fe = result.getFieldError();
			String msg = fe.getDefaultMessage();
			if (logger.isDebugEnabled()) {
				logger.debug("表单验证错误  : {}", msg);
			}
			return new MessageEntry(0, msg);
		}
		User user = getCurrentUser(request);
		int r = returnsServiceImpl.insert(t, user);
		return new MessageEntry(r , "");
	}

	@RequestMapping(value = "/id/{id}")
	@ResponseBody
	public Returns findById(HttpServletRequest request,
							HttpServletResponse response, Model model, @PathVariable Long id) {
		return returnsServiceImpl.findById(id);
	}

	@RequestMapping(value = "/del/{id}")
	@AckPermission(value = "returns:delete")
	@ResponseBody
	public Integer deleteById(HttpServletRequest request,
							  HttpServletResponse response, Model model, @PathVariable Long id) {
		return super.deleteById(request, response, model, id);
	}

	@RequestMapping("/edit/ui/{id}")
	@AckPermission(value = "returns:update")
	public String eidtUI(@PathVariable Long id){
		if (logger.isDebugEnabled()) {
			logger.debug("修改项目:{}", id);
		}
		return "returns/returnsEdit";
	}

	@RequestMapping(value = "/edit")
	@AckPermission(value = "returns:update")
	@ResponseBody
	public MessageEntry edit(HttpServletRequest request,
							  HttpServletResponse response, Model model,
							  @Valid Returns returns, BindingResult result) {
		boolean b = result.hasErrors();
		if (b) {
			FieldError fe = result.getFieldError();
			String msg = fe.getDefaultMessage();
			if (logger.isDebugEnabled()) {
				logger.debug("表单验证错误  : {}", msg);
			}
			return new MessageEntry(0, msg);
		}

		Integer r = returnsServiceImpl.update(returns);

		return new MessageEntry(r, "");
	}

	@RequestMapping(value = "/client/{phone}")
	@AckPermission(value = "returns:add or returns:update")
	@ResponseBody
	public Client findClientByPhone(@PathVariable String phone){
		return returnsServiceImpl.findClientByPhone(phone);
	}

	@RequestMapping(value = "/product/{code}")
	@AckPermission(value = "returns:add or returns:update")
	@ResponseBody
	public Product findProductByCode(@PathVariable String code){
		return returnsServiceImpl.findProductByCode(code);
	}
}
