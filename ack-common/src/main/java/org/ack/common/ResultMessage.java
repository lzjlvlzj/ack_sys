package org.ack.common;

import java.io.Serializable;

/**
 * 前端消息返回
 * 
 * @author ack
 *
 */
public class ResultMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7119488483641947506L;

	private String code; // 消息码
	private Object data; // 消息体
	private String message; // 消息内容
	
	public ResultMessage(String message){
		this.message = message;
	}
	
	public ResultMessage(String code, String message){
		this.code = code;
		this.message = message;
	}
	
	public ResultMessage(String code, String message, Object data){
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		String s = "{" + "code : " + code + ", message : " + message
				+ ", data : " + data + "}";
		return s.toString();
	}

}
