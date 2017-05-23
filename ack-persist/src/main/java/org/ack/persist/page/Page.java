package org.ack.persist.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 分页封装
 * 
 * @author ack
 *
 * @param <T>
 */
public class Page <T> implements Serializable{
	
	private static final long serialVersionUID = -7630122685567567060L;
	
	private int currentPage ;                          //当前所在页数
	private int totalRecord;                           //总记录数
	private int totalPage;                             //总页数
	private int pageSize;                              //每页显示条数
	private int startPosition;                         //查询起始位置
	private String orderColumn;                        //排序字段
	private String orderType;                          //升序or降序 asc desc
	private Map<String,Object> condition;              //查询条件
	private List<T> result = new ArrayList<T>();       //每次查询的集合
	
	
	
	public Page(){
		currentPage = 1;
		totalRecord = 0;
		totalPage = 0;
		pageSize = 10;
		condition = new HashMap<String, Object>();
	}
	
	public Page(int currentPage, int pageSize){
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	/**上一页
	 * 
	 * @return int
	 */
	public int getPreviousPageNo(int page){
		currentPage = page - 1;
		boolean b = hasPrevious();
		if(!b) {//没有上一页默认第一页
			currentPage = 1;
		}
		return currentPage;
	}
	

	/**下一页
	 * 
	 * @return
	 */
	public int getNextPageNo(int page){
		currentPage = page + 1;
		boolean b = this.hasNext();
		if(!b){
			currentPage = getTotalPage();
		}
		return currentPage;
	}
	
	/**是否有上一页
	 * 
	 * @return
	 */
	public boolean hasPrevious(){
		boolean b = false;
		if(currentPage > 0){
			b = true;
		}
		return b;
	}
	
	/**是否有下一页
	 * 
	 * @return
	 */
	public boolean hasNext(){
		boolean b = false;
		if(currentPage <= getCurrentPage()){
			b = true;
		}
		return b;
	}
	
	public int getTo() {
		int to = currentPage * pageSize;
		return to;
	}

	public int getFrom() {
		int from = (currentPage - 1) * pageSize;
		return from;
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
	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
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
	
	@Override
	public String toString() {
		String s = "{currentpage : "+ this.currentPage +","
				 + " totalRecord : "+ this.totalRecord +", "
				 + " pageSize : "+ this.getPageSize()  +","
				 + " totalPage : "+ this.totalPage +"}";
		
		return s;
	}
}
