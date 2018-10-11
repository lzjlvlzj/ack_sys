package org.ack.common.message;

import java.io.Serializable;

public class MessageEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2709477928815672319L;

	private String message;
	private Integer code;

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

	@Override
	public String toString() {
		String s = "{message : " + message + ", code : " + code + "}";
		return s;
	}

}
