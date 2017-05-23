package org.ack.admin.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.common.datatable.DataTableTemplate;
import org.ack.persist.page.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 如果需要分页功能可以继承这个
 * 
 * @author ack
 *
 * @param <T>
 * @param <PK>
 */
public abstract class AckPageController<T extends Object, PK extends Serializable>
		extends AckController<T, PK> {
	/**
	 * 默认简单分页查询
	 * <p>
	 * 
	 * <p>
	 * 从1到10,按时间降序排列,一定要注意你的字段是否有<code>createtime</code>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/page")
	@ResponseBody
	public Page<T> findPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() T t,
			@RequestParam(required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "10") int count,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		// 构造查询page参数
		Page<T> page = new Page<T>(currentPage, count);
		page.setOrderColumn(orderColumn);
		page.setOrderType(orderType);
		page.setCondition(map);
		// 查询
		page = getService().findPage(page);
		return page;
	}

	/**
	 * 返回DataTable分页查询
	 * <p>
	 * 从1到10,按时间降序排列,一定要注意你的字段是否有<code>createtime</code>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/table")
	@ResponseBody
	public DataTableTemplate<T> dataTable(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@ModelAttribute() T t,
			@RequestParam(required = false, defaultValue = "0") int start,/*第一条记录的起始位置*/
			@RequestParam(required = false, defaultValue = "10") int length,/*每页显示多少记录*/
			@RequestParam(required = false, defaultValue = "1") int draw,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		
		//计算当前页，构造page
		int currentPage = start / length + 1;
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		// 构造查询page参数
		Page<T> page = new Page<T>(currentPage, length);
		
		page.setOrderColumn(orderColumn);
		page.setOrderType(orderType);
		page.setCondition(map);
		// 查询
		page = getService().findPage(page);
		DataTableTemplate<T> dt = changePage2DataTableTemplate(page, draw);
		return dt;
	}

	/**
	 * 数据格式转换
	 * 
	 * @param page
	 * @return
	 */
	private DataTableTemplate<T> changePage2DataTableTemplate(Page<T> page, int draw) {
		DataTableTemplate<T> dt = new DataTableTemplate<T>();
		dt.setDraw(draw);
		dt.setError("");
		dt.setData(page.getResult());
		dt.setRecordsTotal(page.getTotalRecord());
		dt.setRecordsFiltered(page.getTotalRecord());
		return dt;
	}
}
