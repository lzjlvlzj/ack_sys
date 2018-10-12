package org.ack.admin.web.controller;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.persist.page.Page;
import org.ack.pojo.Brand;
import org.ack.service.BrandService;
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
@RequestMapping("/brand")
public class BrandController extends AckPageController<Brand, Integer>{
	
	@Autowired
    private BrandService brandServiceImpl;
	private static final Logger logger = LoggerFactory
			.getLogger(BrandController.class);

	@Override
	public AckMapperService<Brand, Integer> getService() {
		return brandServiceImpl;
	}

	@RequestMapping(value = "/list/ui")
	@AckPermission(value="trademark:list")
	public String listUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "brand/brandList";
	}
	
	@RequestMapping(value = "/page")
	@AckPermission(value="trademark:list")
	@ResponseBody
	@Override
	public Page<Brand> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() Brand t,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		return super.findPage(request, response, model, t, currentPage, count,
				orderColumn, orderType);
	}
	
	@RequestMapping(value = "/add/ui")
	@AckPermission(value="trademark:add")
	public String addUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("新建品牌");
		}
		return "brand/brandEdit";
	}
	
	@RequestMapping(value = "/add")
	@AckPermission(value="trademark:add")
	@ResponseBody
	@Override
	public Integer insert(HttpServletRequest request,
			HttpServletResponse response, Model model, Brand t) {
		return super.insert(request, response, model, t);
	}
}
