package org.ack.util;

import java.util.Calendar;

/**
 * 日期工具
 * 
 * @author ack
 *
 */
public class CalendarUtil {

	static Calendar calendar = Calendar.getInstance();
	

	/**
	 * 获得当前的月数
	 * 
	 * @return
	 */
	public static int getCurrentMonth() {
		int m = calendar.get(Calendar.MONTH) + 1;
		return m;
	}

	/**
	 * 获得日志开始计数的日期
	 * 
	 * @param day
	 *            结束日
	 * @return
	 */
	public static String getLogStartDay(int day) {
		int year = calendar.get(Calendar.YEAR);
		int m = getCurrentMonth();
		m = m -1;
		/*如果当前1月份那么年份为上一年,月份为12*/
		if(m == 0){
			year = year -1;
			m = 12;
		}
		String d = year + "-" + m + "-" + day;
		return d;
	}

	/**
	 * 获得日志开始计数的日期
	 * 
	 * @return
	 */
	public static String getLogStartDay() {
		int day = 25;
		String d = getLogStartDay(day);
		return d;
	}

	/**
	 * 获得日志结束计数的日期
	 * 
	 * @param day
	 *            结束日
	 * @return
	 */
	public static String getLogEndDay(int day) {
		int year = calendar.get(Calendar.YEAR);
		int m = getCurrentMonth();
		String d = year + "-" + m + "-" + day;
		return d;
	}

	/**
	 * 获得日志结束计数的日期
	 * 
	 * @return
	 */
	public static String getLogEndDay() {
		int day = 25;
		return getLogEndDay(day);
	}
}
