package org.ack.admin.web.controller;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.common.message.MessageEntry;
import org.ack.persist.page.Page;
import org.ack.pojo.Trade;
import org.ack.pojo.User;
import org.ack.service.TradeService;
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
@RequestMapping("/trade")
public class TradeController extends AckPageController<Trade, Long>{
	
	@Autowired
    private TradeService tradeServiceImpl;
	private static final Logger logger = LoggerFactory
			.getLogger(TradeController.class);

	@Override
	public AckMapperService<Trade, Long> getService() {
		return tradeServiceImpl;
	}

	@RequestMapping(value = "/list/ui")
	@AckPermission(value="trade:list")
	public String listUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "trade/tradeList";
	}
	
	@RequestMapping(value = "/page")
	@AckPermission(value="trade:list")
	@ResponseBody
	public Page<Trade> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() Trade t,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		return super.findPage(request, response, model, null, t, currentPage, count,
				orderColumn, orderType);
	}
	
	@RequestMapping(value = "/add/ui")
	@AckPermission(value="trade:add")
	public String addUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("新建客户");
		}
		return "trade/tradeEdit";
	}
	
	@RequestMapping(value = "/add")
	@AckPermission(value="trade:add")
	@ResponseBody
	@Override
	public MessageEntry insert(HttpServletRequest request,
							   HttpServletResponse response, Model model,
							   @Valid Trade t, BindingResult result) {
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
		int r = tradeServiceImpl.insert(t, user);
		return new MessageEntry(r , "");
	}

	@RequestMapping(value = "/id/{id}")
	@ResponseBody
	public Trade findById(HttpServletRequest request,
							HttpServletResponse response, Model model, @PathVariable Long id) {
		return tradeServiceImpl.findById(id);
	}

	@RequestMapping(value = "/del/{id}")
	@AckPermission(value = "trade:delete")
	@ResponseBody
	public Integer deleteById(HttpServletRequest request,
							  HttpServletResponse response, Model model, @PathVariable Long id) {
		return super.deleteById(request, response, model, id);
	}

	@RequestMapping("/edit/ui/{id}")
	@AckPermission(value = "trade:update")
	public String eidtUI(@PathVariable Long id){
		if (logger.isDebugEnabled()) {
			logger.debug("修改项目:{}", id);
		}
		return "trade/tradeEdit";
	}

	@RequestMapping(value = "/edit")
	@AckPermission(value = "trade:update")
	@ResponseBody
	public MessageEntry edit(HttpServletRequest request,
							 HttpServletResponse response, Model model,
							 @Valid Trade trade, BindingResult result) {
		boolean b = result.hasErrors();
		if (b) {
			FieldError fe = result.getFieldError();
			String msg = fe.getDefaultMessage();
			if (logger.isDebugEnabled()) {
				logger.debug("表单验证错误  : {}", msg);
			}
			return new MessageEntry(0, msg);
		}

		Integer r = tradeServiceImpl.update(trade);

		return new MessageEntry(r, "");
	}
	




}
