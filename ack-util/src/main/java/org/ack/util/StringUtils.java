package org.ack.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类
 * 
 * @author ack
 *
 */
public class StringUtils {

	public static String array2String(Integer[] arrays) {
		String s = "";
		for (Integer n : arrays) {
			s = s + n + ",";
		}
		s = s.substring(0, s.length() - 1);
		return s;
	}

	public static boolean isBlank(String s) {
		if (null == s || "".equals(s) || s.length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotBlank(String s) {
		return !isBlank(s);
	}

	public static String date2String(Date d) {
		return date2String(d, null);
	}

	public static String date2String(Date d, String format) {
		if (null == d) {
			return null;
		}
		if (isBlank(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		return new SimpleDateFormat(format).format(d);
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date strToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
