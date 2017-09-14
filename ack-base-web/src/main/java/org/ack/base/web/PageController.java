package org.ack.base.web;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ack.common.Content;
import org.ack.util.StringUtils;

public abstract class PageController extends BaseController {

	/**
	 * DataTable 排序信息
	 * 
	 * @param request
	 * @return
	 */
	protected Map<String, String> getSortCondition(HttpServletRequest request) {
		// 获得当前排序字段序号
		String orderNum = request.getParameter("order[0][column]");
		// 要排序字段的参数key
		String p = "columns[" + orderNum + "][data]";
		// 排序方式
		String orderType = request.getParameter("order[0][dir]");
		// 排序字段
		String orderColumn = request.getParameter(p);

		Map<String, String> map = new HashMap<String, String>();

		map.put("orderType", orderType);
		map.put("orderColumn", orderColumn);

		return map;
	}

	protected boolean fieldTypeIsNumber(String typeName) {
		for (int i = 0; i < Content.BASE_TYPE_NUMBER.length; i++) {
			String fieldType = Content.BASE_TYPE_NUMBER[i];
			if (fieldType.equals(typeName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param request
	 * @param clazz
	 * @return
	 */
	protected Map<String, Object> getQueryConditions(
			HttpServletRequest request, Map<String, Object> extraCondition,
			Class<?> clazz) {
		// 搜索字段
		String searchContent = request.getParameter("search[value]");
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != extraCondition){
			map.putAll(extraCondition);
		}
		
		Field[] fields = clazz.getDeclaredFields();
		boolean isNumberic = StringUtils.isNumeric(searchContent);
		for (Field field : fields) {
			Type tp = field.getGenericType();
			String fieldName = field.getName();
			boolean b = Content.BASE_TYPE_STRING.equals(tp.getTypeName());
			// 参数数字,字段字符
			if (b && isNumberic) {
				continue;
			}
			// 参数字符,字段数字
			b = fieldTypeIsNumber(tp.getTypeName());
			if (b && !isNumberic) {
				continue;
			}
			map.put(fieldName, searchContent);
		}
		return map;
	}
	

}
