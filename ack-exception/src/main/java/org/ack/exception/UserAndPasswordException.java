package org.ack.exception;

/**
 * 用户密码不正确异常
 * 
 * @author ack
 *
 */
public class UserAndPasswordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7025512798972557660L;
	
	public UserAndPasswordException(String msg){
		super(msg);
	}

}
