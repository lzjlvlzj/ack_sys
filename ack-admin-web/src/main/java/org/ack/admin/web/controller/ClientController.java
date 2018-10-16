package org.ack.admin.web.controller;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.common.message.MessageEntry;
import org.ack.persist.page.Page;
import org.ack.pojo.Account;
import org.ack.pojo.Client;
import org.ack.pojo.Flow;
import org.ack.pojo.User;
import org.ack.service.ClientService;
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
import java.util.Set;

@Controller
@RequestMapping("/client")
public class ClientController extends AckPageController<Client, Integer>{
	
	@Autowired
    private ClientService clientServiceImpl;
	private static final Logger logger = LoggerFactory
			.getLogger(ClientController.class);

	@Override
	public AckMapperService<Client, Integer> getService() {
		return clientServiceImpl;
	}

	@RequestMapping(value = "/list/ui")
	@AckPermission(value="client:list")
	public String listUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "client/clientList";
	}
	
	@RequestMapping(value = "/page")
	@AckPermission(value="client:list")
	@ResponseBody
	public Page<Client> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() Client t,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		return super.findPage(request, response, model, null, t, currentPage, count,
				orderColumn, orderType);
	}
	
	@RequestMapping(value = "/add/ui")
	@AckPermission(value="client:add")
	public String addUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("新建客户");
		}
		return "client/clientEdit";
	}
	
	@RequestMapping(value = "/add")
	@AckPermission(value="client:add")
	@ResponseBody
	@Override
	public MessageEntry insert(HttpServletRequest request,
							   HttpServletResponse response, Model model,
							   @Valid Client t, BindingResult result) {
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
		int r = clientServiceImpl.insert(t, user);
		return new MessageEntry(r , "");
	}

	@RequestMapping(value = "/id/{id}")
	@ResponseBody
	public Client findById(HttpServletRequest request,
							HttpServletResponse response, Model model, @PathVariable Integer id) {
		return clientServiceImpl.findById(id);
	}

	@RequestMapping(value = "/del/{id}")
	@AckPermission(value = "client:delete")
	@ResponseBody
	public Integer deleteById(HttpServletRequest request,
							  HttpServletResponse response, Model model, @PathVariable Integer id) {
		return super.deleteById(request, response, model, id);
	}

	@RequestMapping("/edit/ui/{id}")
	@AckPermission(value = "client:update")
	public String eidtUI(@PathVariable Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("修改项目:{}", id);
		}
		return "client/clientEdit";
	}

	@RequestMapping(value = "/edit")
	@AckPermission(value = "client:update")
	@ResponseBody
	public MessageEntry edit(HttpServletRequest request,
							 HttpServletResponse response, Model model,
							 @Valid Client client, BindingResult result) {
		boolean b = result.hasErrors();
		if (b) {
			FieldError fe = result.getFieldError();
			String msg = fe.getDefaultMessage();
			if (logger.isDebugEnabled()) {
				logger.debug("表单验证错误  : {}", msg);
			}
			return new MessageEntry(0, msg);
		}

		Integer r = clientServiceImpl.update(client);

		return new MessageEntry(r, "");
	}
	

	@RequestMapping(value = "/wheelman")
	@AckPermission(value="client:add or client:update")
	@ResponseBody
	public Set<User> findWheelMan(){
		if (logger.isDebugEnabled()) {
			logger.debug("查询所以负责人");
		}
		return clientServiceImpl.findWheelMan();
	}

	/**
	 * 给客户充钱
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recharge/ui")
	@AckPermission(value="client:recharge")
	public String rechargeUI(HttpServletRequest request,
							 HttpServletResponse response, Model model,
							 @Valid Account account, BindingResult result) {
		if (logger.isDebugEnabled()) {
			logger.debug("给客户:{}充钱页面", account.getClientId());
		}
		return "client/clientRecharge";
	}
	/**
	 * 给客户充钱
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recharge")
	@AckPermission(value="client:recharge")
	@ResponseBody
	public MessageEntry recharge(HttpServletRequest request,
								 HttpServletResponse response, Model model,
								 @Valid Flow flow, BindingResult result) {
		if (logger.isDebugEnabled()) {
			logger.debug("给客户:{}充钱", flow.getClientId());
		}
		boolean b = result.hasErrors();
		if (b) {
			FieldError fe = result.getFieldError();
			String msg = fe.getDefaultMessage();
			if (logger.isDebugEnabled()) {
				logger.debug("表单验证错误  : {}", msg);
			}
			return new MessageEntry(0, msg, null);
		}
		User user= getCurrentUser(request);
		flow.setUserId(user.getId());
		Account account =  clientServiceImpl.recharge(flow);

		return new MessageEntry(1, "", account);
	}
}
