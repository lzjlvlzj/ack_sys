package org.ack.admin.web.controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ack.common.Content;
import org.ack.common.datatable.DataTableTemplate;
import org.ack.persist.page.Page;
import org.ack.util.ReflectUtil;
import org.ack.util.StringUtils;
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
	
	private boolean fieldTypeIsNumber(String typeName) {
		for(int i = 0; i < Content.BASE_TYPE_NUMBER.length; i++){
		    String fieldType = 	Content.BASE_TYPE_NUMBER[i];
		    if(fieldType.equals(typeName)){
		    	return true;
		    }
		}
		return false;
	}

	/**
	 * 返回DataTable分页查询
	 * <p>
	 * 从1到10,按时间降序排列,一定要注意你的字段是否有<code>createtime</code>
	 * </p>
	 * <p>
	 * 搜索 :https://www.datatables.net/examples/api/regex.html
	 * </p>
	 * @param request
	 * @param response
	 * @param extraCondition 
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/table")
	@ResponseBody
	public DataTableTemplate<T> dataTable(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			Map<String, Object> extraCondition,
			@ModelAttribute() T t,
			@RequestParam(required = false, defaultValue = "0") int start,/* 第一条记录的起始位置 */
			@RequestParam(required = false, defaultValue = "10") int length,/* 每页显示多少记录 */
			@RequestParam(required = false, defaultValue = "1") int draw,
			@RequestParam(required = false, defaultValue = "createtime") String orderColumn,
			@RequestParam(required = false, defaultValue = "desc") String orderType) {
		// 获得当前排序字段序号
		String orderNum = request.getParameter("order[0][column]");
		// 要排序字段的参数key
		String p = "columns[" + orderNum + "][data]";
		// 排序方式
		orderType = request.getParameter("order[0][dir]");
		// 排序字段
		orderColumn = request.getParameter(p);
		// 搜索字段
		String searchContent = request.getParameter("search[value]");
		// 计算当前页，构造page
		int currentPage = start / length + 1;
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		// 排序字段
		Type[] type = ReflectUtil.getRealClassType(this.getClass());
		Class<T> clazz = (Class<T>) type[0];

		Field[] fields = clazz.getDeclaredFields();
		boolean isNumberic = StringUtils.isNumeric(searchContent);
		for (Field field : fields) {
			Type tp = field.getGenericType();
			String fieldName = field.getName();
			boolean b = Content.BASE_TYPE_STRING.equals(tp.getTypeName());
			//参数数字,字段字符
			if (b && isNumberic) {
				continue;
			}
			//参数字符,字段数字
			b = fieldTypeIsNumber(tp.getTypeName());
			if(b && !isNumberic){
				continue;
			}
			map.put(fieldName, searchContent);
		}
		//其他查询条件
        map.putAll(extraCondition);
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
	private DataTableTemplate<T> changePage2DataTableTemplate(Page<T> page,
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
