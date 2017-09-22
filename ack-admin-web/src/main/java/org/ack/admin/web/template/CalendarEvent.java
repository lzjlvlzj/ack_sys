package org.ack.admin.web.template;

import java.io.Serializable;

/**
 * 日历控件上的事件
 * 
 * */
public class CalendarEvent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -962755836233207042L;
	
	private String id;
	private String start;
	private String end;
	private String title;
	private String className;
	private String backgroundColor;
	private String borderColor;
	
	public String getStart() {
		return start;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

}
