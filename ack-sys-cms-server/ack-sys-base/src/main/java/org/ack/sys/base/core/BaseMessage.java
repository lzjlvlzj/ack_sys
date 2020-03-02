package org.ack.sys.base.core;

public interface BaseMessage {
	/**
	 * 获得消息码
	 */
	public int getCode();

	/**
	 * 获得消息提示
	 * 
	 * @return
	 */
	public String getMsg();

	/**
	 * 获得具体消息
	 * 
	 * @return
	 */
	public Object getData();
}
