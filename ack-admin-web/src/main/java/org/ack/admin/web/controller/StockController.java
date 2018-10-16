package org.ack.admin.web.controller;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.common.message.MessageEntry;
import org.ack.persist.page.Page;
import org.ack.pojo.Stock;
import org.ack.service.StockService;
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
@RequestMapping("/stock")
public class StockController extends AckPageController<Stock, Integer>{
	
	@Autowired
    private StockService stockServiceImpl;
	private static final Logger logger = LoggerFactory
			.getLogger(StockController.class);

	@Override
	public AckMapperService<Stock, Integer> getService() {
		return stockServiceImpl;
	}

	@RequestMapping(value = "/list/ui")
	@AckPermission(value="trademark:list")
	public String listUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "stock/stockList";
	}
	
	@RequestMapping(value = "/page")
	@AckPermission(value="trademark:list")
	@ResponseBody
	public Page<Stock> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() Stock t,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		 return super.findPage(request, response, model, null, t, currentPage, count,
				orderColumn, orderType);
	}
	
	@RequestMapping(value = "/add/ui")
	@AckPermission(value="trademark:add")
	public String addUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("新建仓储");
		}
		return "stock/stockEdit";
	}

	@RequestMapping(value = "/add")
	@AckPermission(value="trademark:add")
	@ResponseBody
	public MessageEntry insert(HttpServletRequest request,
			HttpServletResponse response, Model model,
			@Valid Stock t, BindingResult result) {
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
		int r = stockServiceImpl.insert(t);
		return new MessageEntry(r , "");
	}

	@RequestMapping(value = "/id/{id}")
	@ResponseBody
	public Stock findById(HttpServletRequest request,
							HttpServletResponse response, Model model, @PathVariable Integer id) {
		return stockServiceImpl.findById(id);
	}

	@RequestMapping(value = "/del/{id}")
	@AckPermission(value = "trademark:delete")
	@ResponseBody
	public Integer deleteById(HttpServletRequest request,
							  HttpServletResponse response, Model model, @PathVariable Integer id) {
		return super.deleteById(request, response, model, id);
	}

	@RequestMapping("/edit/ui/{id}")
	@AckPermission(value = "trademark:update")
	public String eidtUI(@PathVariable Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("修改库房:{}", id);
		}
		return "stock/stockEdit";
	}

	@RequestMapping(value = "/edit")
	@AckPermission(value = "trademark:update")
	@ResponseBody
	public MessageEntry edit(HttpServletRequest request,
							  HttpServletResponse response, Model model,
							  @Valid Stock stock, BindingResult result) {
		boolean b = result.hasErrors();
		if (b) {
			FieldError fe = result.getFieldError();
			String msg = fe.getDefaultMessage();
			if (logger.isDebugEnabled()) {
				logger.debug("表单验证错误  : {}", msg);
			}
			return new MessageEntry(0, msg);
		}

		Integer r = stockServiceImpl.update(stock);

		return new MessageEntry(r, "");
	}
}
