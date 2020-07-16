package org.ack.sys.base.core.auth;

/**
 * 权限逻辑操作符
 * 
 * and or
 * 
 * */
public enum AuthLogic {

	AND("AND"), OR("OR");
	
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
