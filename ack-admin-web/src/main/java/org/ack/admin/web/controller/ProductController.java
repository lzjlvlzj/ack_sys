package org.ack.admin.web.controller;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.persist.page.Page;
import org.ack.pojo.Product;
import org.ack.service.ProductService;
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
@RequestMapping("/product")
public class ProductController extends AckPageController<Product, Long>{
	
	@Autowired
    private ProductService productServiceImpl;
	private static final Logger logger = LoggerFactory
			.getLogger(ProductController.class);

	@Override
	public AckMapperService<Product, Long> getService() {
		return productServiceImpl;
	}
	@RequestMapping(value = "/list/ui")
	@AckPermission(value="product:list")
	public String listUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "product/productList";
	}
	
	@RequestMapping(value = "/page")
	@AckPermission(value="product:list")
	@ResponseBody
	@Override
	public Page<Product> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() Product t,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		return super.findPage(request, response, model, t, currentPage, count,
				orderColumn, orderType);
	}
	
	@RequestMapping(value = "/add/ui")
	@AckPermission(value="product:add")
	public String addUI(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (logger.isDebugEnabled()) {
			logger.debug("新建产品");
		}
		return "product/productEdit";
	}
	
	@RequestMapping(value = "/add")
	@AckPermission(value="product:add")
	@ResponseBody
	@Override
	public Integer insert(HttpServletRequest request,
			HttpServletResponse response, Model model, Product t) {
		return super.insert(request, response, model, t);
	}
}
