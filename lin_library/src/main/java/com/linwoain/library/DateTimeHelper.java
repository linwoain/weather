/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/21 10:34
 */
package com.linwoain.library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间相关方法
 *
 * @author linwoain
 * @version 2014/10/21 10:34
 */
public class DateTimeHelper {
	public class Format {
		public static final String yyyyMMdd = "yyyyMMdd";
		public static final String yyyyMM = "yyyyMM";
		public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
		public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
		public static final String yyyy_MM_dd1hh_mm_ss = "yyyy-MM-dd/HH:mm:ss";
		public static final String yyyy_MM_dd1hh_mm_ss_SSS = "yyyy-MM-dd/HH:mm:ss:SSS";
		public static final String yyyy_MM_dd = "yyyy-MM-dd";
		public static final String hh_mm_ss = "hh:mm:ss";
		public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
		public static final String yyyyMMddHHmmssS = "yyyyMMddHHmmss";

	}

	public static SimpleDateFormat sdf;

	/**
	 * 将字符串时间转换为时间对象
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date toDate(String date, String format) {
		sdf = new SimpleDateFormat(format, Locale.getDefault());
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当天的日期
	 *
	 * @param format
	 * @return
	 */
	public static String toString(String format) {

		sdf = new SimpleDateFormat(format);
		return sdf.format(System.currentTimeMillis());
	}

	/**
	 * 将日期对象按指定格式输出为字符串
	 *
	 * @param date
	 *            日期
	 * @param format
	 *            格式
	 * @return
	 */
	public static String toString(Date date, String format) {
		if (date == null) {
			throw new RuntimeException("日期对象不能为null");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		return sdf.format(date);
	}

	/**
	 * 将日期对象按指定格式输出为字符串
	 *
	 * @param date
	 *            日期
	 * @param format
	 *            格式
	 * @return
	 */
	public static String toString(long date, String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		return sdf.format(date);
	}

	/**
	 * 如果start==end，则返回0<br>
	 * 如果start>end,则返回负值<br>
	 * 如果start<end,则返回正值
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDays(Date start, Date end) {
		if (start == null || end == null) {
			throw new RuntimeException("时间不能为null");
		}
		Calendar startC = Calendar.getInstance();
		Calendar endC = Calendar.getInstance();
		startC.setTime(start);
		endC.setTime(end);

		startC.clear(Calendar.HOUR_OF_DAY);
		startC.clear(Calendar.MINUTE);
		startC.clear(Calendar.SECOND);
		startC.clear(Calendar.MILLISECOND);

		endC.clear(Calendar.HOUR_OF_DAY);
		endC.clear(Calendar.MINUTE);
		endC.clear(Calendar.SECOND);
		endC.clear(Calendar.MILLISECOND);
		if (startC.after(endC)) {
			int days = 0;
			while (endC.before(startC)) {
				endC.add(Calendar.DAY_OF_YEAR, 1);
				days++;
			}
			return 0 - days;
		} else {
			int days = 0;
			while (startC.before(endC)) {
				startC.add(Calendar.DAY_OF_YEAR, 1);
				days++;
			}
			return days;
		}
	}

	/**
	 * 判断指定日期是哪一天
	 *
	 * @param date
	 * @param days
	 *            0:今天
	 * @return
	 */
	public static boolean isWhichDay(Date date, int days) {
		if (date == null) {
			throw new RuntimeException("时间不能为null");
		}
		Date today = new Date(System.currentTimeMillis());
		Calendar calendarToday = Calendar.getInstance();
		calendarToday.setTime(today);
		calendarToday.add(Calendar.DAY_OF_YEAR, days);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (calendar.get(Calendar.YEAR) == calendarToday.get(Calendar.YEAR) && calendar.get(Calendar.MONTH) == calendarToday.get(Calendar.MONTH) && calendar.get(Calendar.DAY_OF_MONTH) == calendarToday.get(Calendar.DAY_OF_MONTH)) {
			return true;
		} else {
			return false;
		}
	}
}
