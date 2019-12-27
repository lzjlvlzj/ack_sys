package org.ack.sys.base.persist.page;

import java.util.HashMap;
import java.util.Map;
/**
 * 分页请求封装
 * */
public class PageRequest {
	private int currentPage = 1; //当前页码
	private int pageSize = 10;   //每页显示多少条
	private String orderColumn = "creatTime"; //排序字段
	private String orderType = "asc"; //排序的方式
	/**
	 * 每页显示的字段
	 */
	private Map<String, ColumnFilter> columnFilters = new HashMap<String, ColumnFilter>();

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Map<String, ColumnFilter> getColumnFilters() {
		return columnFilters;
	}

	public void setColumnFilters(Map<String, ColumnFilter> columnFilters) {
		this.columnFilters = columnFilters;
	}

	public ColumnFilter getColumnFilter(String name) {
		return columnFilters.get(name);
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}
