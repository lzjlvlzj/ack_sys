package org.ack.admin.web.controller;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.common.message.MessageEntry;
import org.ack.persist.page.Page;
import org.ack.pojo.Logistics;
import org.ack.service.LogisticsService;
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
@RequestMapping("/logistics")
public class LogisticsController extends AckPageController<Logistics, Long>{
	
	@Autowired
    private LogisticsService logisticsServiceImpl;
	private static final Logger logger = LoggerFactory
			.getLogger(LogisticsController.class);

	@Override
	public AckMapperService<Logistics, Long> getService() {
		return logisticsServiceImpl;
	}

	@RequestMapping(value = "/list/ui")
	@AckPermission(value="logistics:list")
	public String listUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "logistics/logisticsList";
	}
	
	@RequestMapping(value = "/page")
	@AckPermission(value="logistics:list")
	@ResponseBody
	public Page<Logistics> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() Logistics t,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		 return super.findPage(request, response, model, null, t, currentPage, count,
				orderColumn, orderType);
	}
	
	@RequestMapping(value = "/add/ui")
	@AckPermission(value="logistics:add")
	public String addUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("新建品牌");
		}
		return "logistics/logisticsEdit";
	}

	@RequestMapping(value = "/add")
	@AckPermission(value="logistics:add")
	@ResponseBody
	public MessageEntry insert(HttpServletRequest request,
			HttpServletResponse response, Model model,
			@Valid Logistics t, BindingResult result) {
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
		int r = logisticsServiceImpl.insert(t);
		return new MessageEntry(r , "");
	}

	@RequestMapping(value = "/id/{id}")
	@ResponseBody
	public Logistics findById(HttpServletRequest request,
							HttpServletResponse response, Model model, @PathVariable Long id) {
		return logisticsServiceImpl.findById(id);
	}

	@RequestMapping(value = "/del/{id}")
	@AckPermission(value = "logistics:delete")
	@ResponseBody
	public Integer deleteById(HttpServletRequest request,
							  HttpServletResponse response, Model model, @PathVariable Long id) {
		return super.deleteById(request, response, model, id);
	}

	@RequestMapping("/edit/ui/{id}")
	@AckPermission(value = "logistics:update")
	public String eidtUI(@PathVariable Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("修改项目:{}", id);
		}
		return "logistics/logisticsEdit";
	}

	@RequestMapping(value = "/edit")
	@AckPermission(value = "logistics:update")
	@ResponseBody
	public MessageEntry edit(HttpServletRequest request,
							  HttpServletResponse response, Model model,
							  @Valid Logistics logistics, BindingResult result) {
		boolean b = result.hasErrors();
		if (b) {
			FieldError fe = result.getFieldError();
			String msg = fe.getDefaultMessage();
			if (logger.isDebugEnabled()) {
				logger.debug("表单验证错误  : {}", msg);
			}
			return new MessageEntry(0, msg);
		}

		Integer r = logisticsServiceImpl.update(logistics);

		return new MessageEntry(r, "");
	}
}
