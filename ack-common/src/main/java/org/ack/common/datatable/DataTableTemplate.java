package org.ack.common.datatable;

import java.io.Serializable;
import java.util.List;

/**
 * DataTable服务器端返回模板
 * @see http://datatables.club/manual/server-side.html
 * @author ack
 *
 */
public class DataTableTemplate<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -322198131038864986L;
	
	private int draw = 1;                     // draw,防止xss攻击 (客户端发送后再由服务端发送回来)
	
	private int recordsTotal;                 // 无条件下的查询数量
	
	private  int recordsFiltered;             // 有条件下的查询数量
	 
	private List<T> data;                     // 结果集
	
	private String error;                     // 错误的友好信息

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
