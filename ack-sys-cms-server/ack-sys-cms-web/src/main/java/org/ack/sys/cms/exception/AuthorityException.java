package org.ack.sys.cms.exception;

import org.ack.sys.base.exception.BaseException;

public class AuthorityException extends BaseException{

	private static final long serialVersionUID = -2328456299921806229L;

	public AuthorityException() {
		super("没有权限进行操作");
	}
	
	public AuthorityException(String msg) {
		super(msg);
	}
	
}
