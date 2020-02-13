package org.ack.sys.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 *
 * @author ack
 *
 */
public class StringUtils {

	Pattern pattern = null;

	/** is date
	 * @param s
	 * @return
	 */
	public static boolean isDateString(String s){
		if(isBlank(s)){
			return false;
		}
		String p = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		return matcher(p, s);
	}

	/** is
	 * @param p
	 * @param s
	 * @return
	 */
	public static boolean matcher(String p, String s) {
		Pattern r = Pattern.compile(p);
		Matcher m = r.matcher(s);
		return m.find();
	}

	/**
	 * 判断一个字符串不是纯数字
	 *
	 * @param s
	 * @return
	 */
	public static boolean isString(String s) {
		return !isNumeric(s);
	}

	/**
	 * 判断一个字符串是否由纯数字组成
	 * */
	public static boolean isNumeric(String s) {
		if (null == s || s.length() == 0) {
			return false;
		}
		int n = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			boolean b = Character.isDigit(c);
			if (!b) {
				n = 1;
				break;
			}
		}
		if (n == 1) {
			return false;
		} else {
			return true;
		}

	}

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
