package org.ack.auth.authenticate;

/**
 * 权限逻辑操作符
 * 
 * and or
 * 
 * */
public enum AuthLogic {

	AND("and"), OR("or");
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	AuthLogic(String value) {
		this.value = value;
	}
}
