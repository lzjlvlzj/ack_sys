package org.ack.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 封装某一个功能的curd等操作
 * <p>
 * 比如<b>用户管理</b>中有curd的操作
 * 
 * @author ack
 *
 */
public class MenuFunction implements Serializable {

	private static final long serialVersionUID = 1762949129254293928L;
	private Integer id;                         // 数据库id
	private String functionName;                // 功能名称
	private String url;                         // 功能url
	private String comments;                    // 备注
	private Date createTime;                    // 创建时间
	private Integer menuId;                     // 对于菜单id
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	
	@Override
	public String toString() {
		String s = "{"
				+ "id : id"
				+ ", functionName : " + functionName
				+ ", url : " + url 
				+ ", menuId :　" + menuId
				+ "}";
		return s;
	}

	

}
