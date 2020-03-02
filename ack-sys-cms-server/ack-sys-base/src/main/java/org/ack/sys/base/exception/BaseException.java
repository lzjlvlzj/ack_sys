package org.ack.sys.base.exception;

import org.ack.sys.base.core.BaseMessage;

public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7873493218554525949L;

	protected BaseMessage baseMessage;
	protected int code;

	public BaseException(BaseMessage baseMessage) {
		super(baseMessage.getMsg());
		this.code = baseMessage.getCode();
		
	}
	public BaseException() {
		super();
	}

	public BaseException(String msg) {
		super(msg);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

}
