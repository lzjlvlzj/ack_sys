package org.ack.sys.persist.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/***
 * 分页返回封装
 * @author ack
 *
 */
public class Page<T> {
	private int currentPage ;                                                                 //当前所在页数
	private int totalRecord;                                                                  //总记录数
	private int totalPage;                                                                    //总页数
	private int pageSize;                                                                     //每页显示条数
	private long startPosition;                                                               //查询起始位置
	private String orderColumn;                                                               //排序字段
	private String orderType;                                                                 //升序or降序 asc desc
	private Map<String,Object> condition = new HashMap<String,Object>();                      //查询条件
	private List<T> result;                                                                   //每次查询的集合 
	private String tableName;                                                                 //数据库名称
	private List<String> fileds;                                                              //数据库中字段
	public Page() {
		this.pageSize = 10;
		this.currentPage = 1;
		this.orderColumn = "createtime";
		this.orderType = "desc";
		this.startPosition = 0;
	}
	
    public Page(int pageSize, String orderColumn, String orderType) {
		this.pageSize = pageSize;
		this.orderColumn = orderColumn;
		this.orderType = orderType;
		this.currentPage = 1;
		this.startPosition = 0L;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(long startPosition) {
		this.startPosition = startPosition;
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
	public Map<String, Object> getCondition() {
		return condition;
	}
	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
	

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	
	public List<String> getFileds() {
		return fileds;
	}

	public void setFileds(List<String> fileds) {
		this.fileds = fileds;
	}

	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", totalRecord=" + totalRecord + ", totalPage=" + totalPage
				+ ", pageSize=" + pageSize + ", startPosition=" + startPosition + ", orderColumn=" + orderColumn
				+ ", orderType=" + orderType + "]";
	}
	
	
}
