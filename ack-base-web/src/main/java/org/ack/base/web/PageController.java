package org.ack.base.web;

import org.ack.common.Content;
import org.ack.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public abstract class PageController extends BaseController {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");

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
		if (null != extraCondition) {
			map.putAll(extraCondition);
		}
        if(StringUtils.isBlank(searchContent)){
        	return map;
        }
		Field[] fields = clazz.getDeclaredFields();
		boolean isNumberic = StringUtils.isNumeric(searchContent);
		boolean isDate = StringUtils.isDateString(searchContent);
		for (Field field : fields) {
			Type tp = field.getGenericType();
			String fieldName = field.getName();
			boolean str = Content.BASE_TYPE_STRING.equals(tp.getTypeName());
			boolean d = Content.BASE_TYPE_DATE.equals(tp.getTypeName());
			// 参数字符,字段数字
			boolean num = fieldTypeIsNumber(tp.getTypeName());
			if(str){
				map.put(fieldName, searchContent);
			}
			if(isNumberic && num){
				map.put(fieldName, searchContent);
			}
			if(isDate && d){
				map.put(fieldName, searchContent);
			}
			
		}
		return map;
	}

}
