package org.ack.admin.web.controller;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.persist.page.Page;
import org.ack.pojo.Client;
import org.ack.service.ClientService;
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
	@Override
	public Page<Client> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() Client t,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		return super.findPage(request, response, model, t, currentPage, count,
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
	public Integer insert(HttpServletRequest request,
			HttpServletResponse response, Model model, Client t) {
		return super.insert(request, response, model, t);
	}
}
