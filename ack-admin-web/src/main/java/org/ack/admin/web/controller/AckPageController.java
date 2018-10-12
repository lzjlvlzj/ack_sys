package org.ack.admin.web.controller;

import org.ack.common.datatable.DataTableTemplate;
import org.ack.persist.page.Page;
import org.ack.util.ReflectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

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

	private static final Logger logger = LoggerFactory
			.getLogger(AckPageController.class);

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
	public Page<T> findPage(HttpServletRequest request,
			HttpServletResponse response, Model model,
			Map<String, Object> extraCondition, T t, int currentPage,
			int count, String orderColumn, String orderType) {
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != extraCondition) {
			map.putAll(extraCondition);
		}
		// 构造查询page参数
		Page<T> page = new Page<T>(currentPage, count);
		page.setOrderColumn(orderColumn);
		page.setOrderType(orderType);
		page.setCondition(map);
		// 查询
		page = getService().findPage(page);
		return page;
	}

	@SuppressWarnings("unchecked")
	protected Map<String, Object> getQueryConditions(
			HttpServletRequest request, Map<String, Object> extraCondition, T t) {
		// 排序字段
		Type[] type = ReflectUtil.getRealClassType(this.getClass());
		Class<T> clazz = (Class<T>) type[0];
		Map<String, Object> map = getQueryConditions(request, extraCondition,
				clazz);
		return map;
	}

	/**
	 * DataTable查询用这个
	 * 
	 * */
	protected Page<T> getPage(HttpServletRequest request,
			Map<String, Object> extraCondition, T t, int currentPage, int length) {
		// 排序条件
		Map<String, String> sortMap = getSortCondition(request);
		// 排序方式
		String orderType = sortMap.get("orderType");
		// 排序字段
		String orderColumn = sortMap.get("orderColumn");
		Page<T> page = getPage(request, extraCondition, t, currentPage, length,
				orderColumn, orderType);
		return page;
	}

	/**
	 * 普通查询用这个
	 * */
	protected Page<T> getPage(HttpServletRequest request,
			Map<String, Object> extraCondition, T t, int currentPage,
			int length, String orderColumn, String orderType) {
		// 查询条件
		Map<String, Object> map = getQueryConditions(request, extraCondition, t);
		// 构造查询page参数
		Page<T> page = new Page<T>(currentPage, length);
		page.setOrderColumn(orderColumn);
		page.setOrderType(orderType);
		page.setCondition(map);
		return page;
	}
	
	public Page<T> findPage(Page<T> page){
		// 查询
		page = getService().findPage(page);
		return page;
	}

	/**
	 * 返回DataTable分页查询
	 * <p>
	 * 从1到10,按时间降序排列,一定要注意你的字段是否有<code>createtime</code>
	 * </p>
	 * <p>
	 * 搜索 :https://www.datatables.net/examples/api/regex.html
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param extraCondition
	 * @param model
	 * @return
	 */
	public DataTableTemplate<T> dataTable(HttpServletRequest request,
			HttpServletResponse response, Model model,
			Map<String, Object> extraCondition, T t, int start,/* 第一条记录的起始位置 */
			int length,/* 每页显示多少记录 */
			int draw, String orderColumn, String orderType) {
		if (logger.isDebugEnabled()) {
			logger.debug("datatable query");
		}
		// 计算当前页，构造page
		int currentPage = start / length + 1;
		// 构造查询page参数
		Page<T> page = getPage(request, extraCondition, t, currentPage, length);
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
	protected DataTableTemplate<T> changePage2DataTableTemplate(Page<T> page,
			int draw) {
		DataTableTemplate<T> dt = new DataTableTemplate<T>();
		dt.setDraw(draw);
		dt.setError("");
		dt.setData(page.getResult());
		dt.setRecordsTotal(page.getTotalRecord());
		dt.setRecordsFiltered(page.getTotalRecord());
		return dt;
	}

}
