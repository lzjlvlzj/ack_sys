package org.ack.admin.web.controller;

import org.ack.auth.authenticate.annotation.AckPermission;
import org.ack.base.service.AckMapperService;
import org.ack.common.message.MessageEntry;
import org.ack.persist.page.Page;
import org.ack.pojo.Brand;
import org.ack.pojo.Product;
import org.ack.service.ProductService;
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
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController extends AckPageController<Product, Integer>{
	
	@Autowired
    private ProductService productServiceImpl;
	private static final Logger logger = LoggerFactory
			.getLogger(ProductController.class);

	@Override
	public AckMapperService<Product, Integer> getService() {
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
	public Page<Product> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() Product t,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		return super.findPage(request, response, model, null, t, currentPage, count,
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
	public MessageEntry insert(HttpServletRequest request,
			HttpServletResponse response, Model model,
			@Valid Product product, BindingResult result) {
		boolean b = result.hasErrors();
		if (b) {
			FieldError fe = result.getFieldError();
			String msg = fe.getDefaultMessage();
			if (logger.isDebugEnabled()) {
				logger.debug("表单验证错误  : {}", msg);
			}
			return new MessageEntry(0, msg);
		}
		int r = productServiceImpl.insert(product);
		return new MessageEntry(r , "");
	}

	@RequestMapping(value = "/id/{id}")
	@ResponseBody
	public Product findById(HttpServletRequest request,
						  HttpServletResponse response, Model model, @PathVariable Integer id) {
		return productServiceImpl.findById(id);
	}

	@RequestMapping(value = "/del/{id}")
	@AckPermission(value = "product:delete")
	@ResponseBody
	public Integer deleteById(HttpServletRequest request,
							  HttpServletResponse response, Model model, @PathVariable Integer id) {
		return super.deleteById(request, response, model, id);
	}

	@RequestMapping("/edit/ui/{id}")
	@AckPermission(value = "product:update")
	public String eidtUI(@PathVariable Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("修改项目:{}", id);
		}
		return "product/productEdit";
	}

	@RequestMapping(value = "/edit")
	@AckPermission(value = "product:update")
	@ResponseBody
	public MessageEntry edit(HttpServletRequest request,
							 HttpServletResponse response, Model model,
							 @Valid Product product, BindingResult result) {
		boolean b = result.hasErrors();
		if (b) {
			FieldError fe = result.getFieldError();
			String msg = fe.getDefaultMessage();
			if (logger.isDebugEnabled()) {
				logger.debug("表单验证错误  : {}", msg);
			}
			return new MessageEntry(0, msg);
		}

		Integer r = productServiceImpl.update(product);

		return new MessageEntry(r, "");
	}
	@RequestMapping(value = "/find/brand")
	@AckPermission(value = "product:add or product:update")
	@ResponseBody
	public List<Brand> findAllBrand(){
		return productServiceImpl.findAllBrand();
	}
}
