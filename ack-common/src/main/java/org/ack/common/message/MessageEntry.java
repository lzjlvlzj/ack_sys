package org.ack.common.message;

import java.io.Serializable;

public class MessageEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2709477928815672319L;

	private String message;
	private Integer code;
	private Object data;
	public MessageEntry(int code, String message ){
		this.code = code;
		this.message = message;
	}
	public MessageEntry(int code, String message, Object data ){
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		String s = "{message : " + message + ", code : " + code + "}";
		return s;
	}

}
