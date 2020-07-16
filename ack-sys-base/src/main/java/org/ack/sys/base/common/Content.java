package org.ack.sys.base.common;

public class Content {

	/**
	 * session key
	 */
	public static final String SESSION_KEY_USER = "user";
	
	public static final String SESSION_KEY_KAPTCHA = "captcha";

	public static final String[] CROS_DOMAIN = { "127.0.0.1", 
			"localhost", 
			"localhost:8080", 
			"127.0.0.1:8080", 
			"google.com" };
	public static final String DB_MONGO_PRIFIX = "mongodb://";
	
	public static final String DB_MONGO_ID_NAME = "_id";
	
	public static final String MONGO_PAGE_ID_NAME = "lastId";
	
	public static final String STATIC_RESOURCE_PREFIX = "static";

	/*
	 * 默认图片格式
	 */
	public static final String[] DEFAULT_IMG_SUFFIX = {"png", "jpg", "jpeg"};
	
	
	
}
