package org.ack.sys.base.common;

import java.util.Date;

import org.ack.sys.base.util.MD5Util;




public class Token {

	/**
	 * 获得MD5
	 * 
	 * @param date
	 * @return
	 */
	public static String getToken(Date date) {
		if (null == date) {
			date = new Date();
		}
		long t = date.getTime();
		String md5 = MD5Util.md5(t + "");
		return md5;
	}
}
