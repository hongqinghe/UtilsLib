package middlem.person.utilsmodule;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/***********************************************
 *
 * <P> desc:    日期、时间处理类
 * <P> Author: gongtong
 * <P> Date: 2017-10-24 21:06
 ***********************************************/

public final class DateUtils {
	private static HashMap<String, DateFormat> mFormatsMap = new HashMap<>();

	/**
	 * 简单的日期格式化.
	 */
	public static final String FORMAT_SIMPLE = "yyyy-MM-dd";
	/**
	 * 简单的日期格式化.
	 */
	public static final String FORMAT_SIMPLE3 = "yyyy-MM";
	/**
	 * 简单的日期格式化.
	 */
	public static final String FORMAT_SIMPLE4 = "yyyyMMdd";

	/**
	 * 年月日时分秒毫秒
	 */
	public static final String FORMAT_SIMPLE5 = "yyyyMMddHHmmssSSS";

	/**
	 * 简单的日期格式化.
	 */
	public static final String FORMAT_SIMPLE6 = "yyyy.MM.dd HH:mm";
	public static final String FORMAT_SIMPLE8 = "yyyy.MM.dd";
	public static final String FORMAT_SIMPLE9 = "yyyy年MM月";
	/**
	 * 简单的日期格式化.
	 */
	public static final String FORMAT_SIMPLE10 = "yyyy年MM月dd日";
	/**
	 * 简单的日期格式化.
	 */
	public static final String FORMAT_SIMPLE11 = "yyyyMM";
	/**
	 * 简单的日期格式化.
	 */
	public static final String FORMAT_SIMPLE12 = "M.d";
	/**
	 * 简单的日期格式化.
	 */
	public static final String FORMAT_SIMPLE13 = "yyyy-MM-dd";


	/**
	 * 简单的日期格式化.
	 */
	public static final String FORMAT_SIMPLE14 = "yyyy-MM-ddHH:mm";
	/**
	 * 三个月为90天(包括90天).
	 */
	public static final int LESS_THREE_MONTH = 91;
	/**
	 * 年-月-日 时:分:秒
	 */
	public static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 年-月-日 时:分
	 */
	public static final String FORMAT_SHORT = "yyyy-MM-dd HH:mm";
	/**
	 * 年-月-日
	 */
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	/**
	 * 年-月
	 */
	public static final String FORMAT_DATE_SHORE = "yyyy-MM";
	/**
	 * 年月日
	 */
	public static final String FORMAT_DATE_SIMPLE = "yyyyMMdd";
	/**
	 * 年月
	 */
	public static final String FORMAT_DATE_SIMPLE_SHORT = "yyyyMM";
	/**
	 * 时:分:秒
	 */
	public static final String FORMAT_TIME = "HH:mm:ss";
	/**
	 * 时:分
	 */
	public static final String FORMAT_TIME_SHORT = "HH:mm";

	/**
	 * 一周内为7天
	 */
	private static final int LESS_A_WEEK = 7;

	/**
	 * 一个季度为91天
	 */
	private static final int LESS_A_SEASON = 91;

	/**
	 * 一天的小时数
	 */
	public static final long ONE_DAY_HOUR = 24;
	/**
	 * 一小时的分钟数
	 */
	public static final long ONE_HOUR_MINUTE = 60;
	/**
	 * 一分钟的秒数
	 */
	public static final long ONE_MINUTE_SECOND = 60;
	/**
	 * 一天的总时长
	 */
	public static final long ONE_DAY_TIME = ONE_DAY_HOUR * ONE_HOUR_MINUTE * ONE_MINUTE_SECOND * 1000;

	/**
	 * 防止被构建.
	 */
	private DateUtils() {

	}

	/**
	 * Date—>String
	 *
	 * @param format
	 * @param date:为空表示今天
	 * @return String
	 */
	public static String toString(String format, Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.CHINA);
		if (StringUtils.isStrEmpty(format)) {
			return "";
		}
		if (date == null) {
			date = new Date();
		}

		return dateFormat.format(date);
	}

	/**
	 * long—>String
	 *
	 * @param format
	 * @param time:-1表示今天
	 * @return
	 */
	public static String toString(String format, long time){
		SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.CHINA);
		if (StringUtils.isStrEmpty(format)) {
			return "";
		}
		if (time <= 0) {
			time = Calendar.getInstance().getTimeInMillis();
		}

		return dateFormat.format(new Date(time));
	}

	/**
	 * long->Date
	 *
	 * @param timeMillis
	 * @return Date
	 */
	public static Date toDate(Long timeMillis) {
		if (timeMillis == null || timeMillis <= 0) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timeMillis);
		return c.getTime();
	}

	/**
	 * String->Date
	 *
	 * @param format
	 * @param dateStr
	 * @return
	 */
	public static Date toDate(String format, String dateStr) {
		if (StringUtils.isStrEmpty(dateStr)) {
			return null;
		}
		DateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException("DateUtils-->" + dateStr, e);
		}
	}

	/**
	 * 获取当前日期
	 * @return int
	 */
	public static int getDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取指定日期
	 * @param date
	 * @return String
	 */
	public static String getDay(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd", Locale.CHINA);
		return format.format(date);
	}

	/**
	 * 得到某天的前后N天
	 * @param offset：位移
	 * @return String
	 */
	public static String getDay(Date date, int offset) {
		SimpleDateFormat format = new SimpleDateFormat("dd", Locale.CHINA);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, offset);
		if (date == null) {
			date = calendar.getTime();
		}
		return format.format(date);
	}

	/**
	 * 得到某天的前后N天日期
	 * @param date 某天，
	 * @param offset
	 * @return Date
	 */
	public static Date getDate(Date date, int offset) {
		if (date == null) {
			date = new Date();
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, offset);
		return calendar.getTime();
	}

	/**
	 * 获取当前月份
	 * <p></p>
	 * @author MaBao 2015-2-22 下午4:27:28
	 * @return
	 */
	public static int getMonth() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取指定月份
	 * @param date
	 * @return
	 */
	public static String getMonth(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MM", Locale.CHINA);
		return format.format(date);
	}

	/**
	 * 获取指定月份
	 * @param offset：位移
	 * @return
	 */
	public static String getMonth(int offset) {
		SimpleDateFormat format = new SimpleDateFormat("MM", Locale.CHINA);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, offset);
		Date date = calendar.getTime();
		return format.format(date);
	}

	/**
	 * 得到某天的前后N月日期
	 * @param date
	 * @param offset
	 * @return Date
	 */
	public static Date getMonth(Date date, int offset) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, offset);
		return calendar.getTime();
	}

	/**
	 * 获取当前年份
	 * @return int
	 */
	public static int getYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获取指定年份
	 * @param date
	 * @return String
	 */
	public static String getYear(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy", Locale.CHINA);
		return format.format(date);
	}

	/**
	 * 获取指定年份
	 * @param offset:位移量
	 * @return String
	 */
	public static String getYear(int offset) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy", Locale.CHINA);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, offset);
		Date date = calendar.getTime();
		return format.format(date);
	}

	/**
	 * 得到一天的最早时间.
	 *
	 * @param date
	 * 		指定日期.
	 * @return 一天的最早时间.
	 */
	public static Date getZeroTime(Date date) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}




	/**
	 * 判断是否为周日
	 *
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static boolean isSunday(String formatStr, String dateStr) {
		if (StringUtils.isStrEmpty(dateStr) || StringUtils.isStrEmpty(formatStr)) {
			return false;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(toDate(formatStr, dateStr));
		return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
	}

	/**
	 * 获取某个日期
	 * @param formatStr
	 * @param dateStr
	 * @return:(int) - >周日(0)~周六(6)
	 */
	public static int getWeekDay(String formatStr, String dateStr) {
		if (StringUtils.isStrEmpty(dateStr) || StringUtils.isStrEmpty(formatStr)) {
			return -1;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(toDate(formatStr, dateStr));
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 获取今天之后的第N天是星期几
	 * @param N：间隔时长
	 * @return:(int) - >周日(0)~周六(6)
	 */
	public static int getWeekDay(int N) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, N);
		int weekIndex =  calendar.get(Calendar.DAY_OF_WEEK) - 1;

		return weekIndex;
	}

	/**
	 * 取得当天日期是周几
	 *
	 * @param date:空表示获取今天
	 * @return:(int) - >周日(0)~周六(6)
	 */
	public static int getWeekDay(Date date) {
		Calendar c = Calendar.getInstance();
		if (date != null) {
			c.setTime(date);
		}
		int week_of_year = c.get(Calendar.DAY_OF_WEEK);
		return week_of_year - 1;
	}

	/**
	 * 判断该日期是否为同一周内
	 * @param source
	 * @return boolean
	 */
	public static boolean isSameWeek(String source) {
		return isSameWeek(source, toString(FORMAT_DATE, new Date()));
	}

	/**
	 * 判断两个日期是否为同一周内（未公开）
	 * @param source
	 * @param target
	 * @return boolean
	 */
	private static boolean isSameWeek(String source, String target) {
		if (StringUtils.isStrEmpty(source) || StringUtils.isStrEmpty(target)) {
			return false;
		}

		long sourceTime = getZeroTime(toDate(FORMAT_DATE, source)).getTime();
		long targetTime = getZeroTime(toDate(FORMAT_DATE, target)).getTime();
		long subTime = Math.abs(sourceTime - targetTime);
		return subTime < LESS_A_WEEK * ONE_DAY_TIME;
	}

	/**
	 * 判断该日期是否为同一季度内
	 * @param source
	 * @return boolean
	 */
	public static boolean isSameSeason(String source) {
		return isSameSeason(source, toString(FORMAT_DATE, new Date()));
	}

	/**
	 * 判断两个日期是否为一季度内（未公开）
	 * @param source
	 * @param target
	 * @return boolean
	 */
	private static boolean isSameSeason(String source, String target) {
		if (StringUtils.isStrEmpty(source) || StringUtils.isStrEmpty(target)) {
			return false;
		}

		long sourceTime = getZeroTime(toDate(FORMAT_DATE, source)).getTime();
		long targetTime = getZeroTime(toDate(FORMAT_DATE, target)).getTime();
		long subTime = Math.abs(sourceTime - targetTime);
		return subTime < LESS_A_SEASON * ONE_DAY_TIME;
	}

	/**
	 * 获取相隔N天的日期
	 * <p>任意天的日期</p>
	 * @param format：格式
	 * @param N：间隔时长，获取与当前相隔天数的日期
	 * @return String
	 */
	public static String getDate(String format, int N) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, N);
		Date date = c.getTime();

		return toString(format, date);
	}

	/**
	 * 获取季度值
	 * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
	 *
	 * @param date:空表示获取今天
	 * @return
	 */
	public static int getSeason(Date date) {

		int season = 0;

		Calendar c = Calendar.getInstance();
		if (date != null) {
			c.setTime(date);
		}
		int month = c.get(Calendar.MONTH);
		switch (month) {
			case Calendar.JANUARY:
			case Calendar.FEBRUARY:
			case Calendar.MARCH:
				season = 1;
				break;
			case Calendar.APRIL:
			case Calendar.MAY:
			case Calendar.JUNE:
				season = 2;
				break;
			case Calendar.JULY:
			case Calendar.AUGUST:
			case Calendar.SEPTEMBER:
				season = 3;
				break;
			case Calendar.OCTOBER:
			case Calendar.NOVEMBER:
			case Calendar.DECEMBER:
				season = 4;
				break;
			default:
				break;
		}
		return season;
	}


	/***
	 * 将time转换成"HH:mm"格式
	 * 如：0转换成 00:00
	 * @param time
	 * @return
	 */
	public static String getTimeStr(String time) {
		if (StringUtils.isEmpty(time)) {
			return "";
		}
		Integer itime = Integer.valueOf(time);
		int hour = itime / 60;
		int minute = itime % 60;
		return String.format("%s:%s", getTimeFormat(hour),
				getTimeFormat(minute));
	}

	@SuppressLint("DefaultLocale")
	public static String getTimeFormat(int monthOrday) {
		return String.format("%02d", monthOrday);
	}

	/**
	 * 将"HH:mm"格式的time转换成 int型
	 *
	 * @param time
	 * @return
	 */
	public static int getTimeVal(String time) {
		if (StringUtils.isEmpty(time)) {
			Calendar calendar = Calendar.getInstance();
			return calendar.get(Calendar.HOUR_OF_DAY) * 60
					+ calendar.get(Calendar.MINUTE);
		}
		String[] times = time.split(":");
		return Integer.valueOf(times[0]) * 60 + Integer.valueOf(times[1]);
	}

	/**
	 * 获取当前为本年的第几周
	 * @return
	 */
	public static int getCurrentWeeks() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.setMinimalDaysInFirstWeek(7);
		cal.setTime(new Date());
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 把日期格式化成短日期格式.
	 * <p>
	 * 格式如：HH:mm
	 * <p>
	 * .
	 *
	 * @param date
	 * 		要格式化的日期.
	 * @return 格式化结果.
	 */
	public static String dateToShortString(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat tFormater = getFormater("HH:mm");
		return tFormater.format(date);
	}

	/**
	 * 把日期格式化成短日期格式.
	 * <p>
	 * 格式如：HH:mm
	 * <p>
	 * .
	 *
	 * @param date
	 * 		要格式化的日期.
	 * @return 格式化结果.
	 */
	public static String date2String(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat tFormater = getFormater("yyyy-MM-dd");
		return tFormater.format(date);
	}

	/**
	 * 把日期格式化成短日期格式.
	 * <p>
	 * 格式如：HH:mm
	 * <p>
	 * .
	 *
	 * @param date
	 * 		要格式化的日期.
	 * @return 格式化结果.
	 */
	public static String date3String(Date date) {
		DateFormat tFormater = getFormater("yyyy-MM-dd HH:mm");
		if (date == null) {
			return null;
		}
		return tFormater.format(date);
	}

	/**
	 * 把日期格式字符串解析成日期.
	 *
	 * @param dateStr
	 * 		日期字符串.
	 * @return 日期.2015-02-18
	 */
	public static Date string2Date(String dateStr) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		DateFormat df = getFormater(FORMAT_SIMPLE);
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException(" 解析日期出错,输入格式不正确:" + dateStr, e);
		}
	}

	/**
	 * 把日期格式字符串解析成日期.
	 *
	 * @param dateStr
	 * 		日期字符串.
	 */
	public static Date string2Date(String dateStr, String regx) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		DateFormat df = getFormater(regx);
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException(AppUtilsContextWrapper.getString(R.string.jiexiriqichucuo) + dateStr, e);
		}
	}

	public static Date string3Date(String dateStr) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		DateFormat df = getFormater(FORMAT_SIMPLE4);
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException(AppUtilsContextWrapper.getString(R.string.jiexiriqichucuo) + dateStr, e);
		}
	}

	public static Date string4Date(String dateStr) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		DateFormat df = getFormater(FORMAT_SIMPLE5);
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException(AppUtilsContextWrapper.getString(R.string.jiexiriqichucuo) + dateStr, e);
		}
	}

	/**
	 * 把日期格式字符串解析成日期.
	 *
	 * @param dateStr
	 * 		日期字符串.
	 * @return 日期.2015-02
	 */
	public static Date stringToDate(String dateStr) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		DateFormat df =  getFormater(FORMAT_SIMPLE3);
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException(AppUtilsContextWrapper.getString(R.string.jiexiriqichucuo) + dateStr, e);
		}
	}

	/**
	 * 把日期格式字符串解析成日期.
	 *
	 * @return 20150218
	 */
	public static String dateToString(Date date) {
		if (date == null) {
			return null;
		}
		return getFormater("yyyyMMdd").format(date);
	}

	/**
	 * 把日期格式字符串解析成日期.
	 *
	 * @return 201502
	 */
	public static String monthToString(Date date) {
		if (date == null) {
			return null;
		}
		return getFormater("yyyyMM").format(date);
	}

	/**
	 * 得到某天时间的后面某天
	 */
	public static Date addDay(Date date, int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, +day); // 得到后面某天
		return calendar.getTime();
	}

	/**
	 * 得到某月的后某个月
	 */
	public static Date addMonth(Date date, int month) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, +month); // 得到后面某天
		return calendar.getTime();
	}

	/**
	 * 解析时间.
	 *
	 * @param currentDate
	 * 		源日期.
	 * @param shortTime
	 * 		短时间字符串.
	 * @return 返回结果.
	 */
	public static Date shortStringToDate(Date currentDate, String shortTime) {
		DateFormat tFormater = getFormater("yyyy-MM-dd HH:mm:ss");
		if (currentDate == null) {
			throw new IllegalArgumentException(AppUtilsContextWrapper.getString(R.string.riqibuyunxuweikong));
		}
		StringBuilder sb = new StringBuilder();
		sb.append(getFormater("yyyy-MM-dd").format(currentDate)).append(" ")
				.append(shortTime).append(":00");
		try {
			return tFormater.parse(sb.toString());
		} catch (ParseException e) {
			throw new IllegalArgumentException(AppUtilsContextWrapper.getString(R.string.jiexiriqichuxiancuowu) + shortTime, e);
		}
	}

	/**
	 * 转成日期时间格式的字符串.
	 * <p>
	 * 格式如：'yyyy-MM-dd HH:mm:ss'
	 * <p>
	 * .
	 *
	 * @param date
	 * 		日期.
	 * @return 日期时间格式字符串.
	 */
	public static String toDateTimeString(Date date) {
		DateFormat tFormater = getFormater("yyyy-MM-dd HH:mm:ss");
		if (date == null) {
			return null;
		}
		return "'" + tFormater.format(date) + "'";
	}

	/**
	 * 转成日期时间格式的字符串.
	 * <p>
	 * 格式如：'yyyy-MM-dd HH:mm:ss'
	 * <p>
	 * .
	 *
	 * @param date
	 * 		日期.
	 * @return 日期时间格式字符串.
	 */
	public static String date2StringTime(Date date) {
		DateFormat tFormater = getFormater("yyyy-MM-dd HH:mm:ss");
		if (date == null) {
			return null;
		}
		return tFormater.format(date);
	}



	/**
	 * 获取当天0点时间戳
	 *
	 * @return
	 */
	public static long getZeroTime(long timestamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 得到一天的最后时刻
	 * @param date：为空时表示当天
	 * @return Date
	 */
	public static Date getLastTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		return calendar.getTime();

	}

	/**
	 * 把日期格式字符串解析成日期.
	 *
	 * @param dateStr
	 * 		日期字符串.
	 * @param regex
	 * 		格式.
	 * @return 日期.
	 */
	public static Date parse(String dateStr, String regex) {
		if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(regex)) {
			return null;
		}
		DateFormat df = getFormater(regex);
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException(AppUtilsContextWrapper.getString(R.string.jiexiriqichucuo) + dateStr, e);
		}
	}

	public static String toDayString(Long time, String regex) {
		if (time == null || StringUtils.isBlank(regex)) {
			return null;
		}
		FastDateFormat tdf = FastDateFormat.getInstance(regex);
		return tdf.format(time);
	}

	/**
	 * 把日期格式化成字符串.
	 *
	 * @param date
	 * 		日期.
	 * @param regex
	 * 		格式.
	 * @return 日期字符串.
	 */
	public static String format(Date date, String regex) {
		DateFormat df =  getFormater(regex);
		return df.format(date);
	}

	public static String toTimeFormat(int minute) {
		return String.format("%02d", minute / 60) + ":" + String.format("%02d", minute % 60);
	}

	/**
	 * 将毫秒数的时间转成日期类型.
	 *
	 * @param timeMillis
	 * 		毫秒数的时间.
	 * @return 日期.
	 */
	public static Date timeToDate(Long timeMillis) {
		if (timeMillis == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timeMillis);
		return c.getTime();
	}

	public static int timeToInt(String time) {
		if (time != null) {
			String[] times = time.split(":");
			if (times.length == 2) {
				int hour = Integer.valueOf(times[0]);
				int minute = Integer.valueOf(times[1]);
				return hour * 60 + minute;
			}
		}
		return -1;
	}

	/**
	 * 比较2个时间是否为同一天
	 *
	 */
	public static boolean isDateEqual(String source, String target) {
		return isDateEqual(source, target, FORMAT_SIMPLE);
	}

	/**
	 * 比较2个时间是否为同一天
	 *
	 */
	public static boolean isDateEqual(String source, String target, String formatStr) {
		Calendar sourceDate = Calendar.getInstance();
		sourceDate.setTime(parse(source, formatStr));
		Calendar targetDate = Calendar.getInstance();
		targetDate.setTime(parse(target, formatStr));
		return sourceDate.get(Calendar.YEAR) == targetDate.get(Calendar.YEAR)
				&& sourceDate.get(Calendar.MONTH) == targetDate.get(Calendar.MONTH)
				&& sourceDate.get(Calendar.DAY_OF_MONTH) == targetDate.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 判断莫个日期string是否为周日
	 *
	 * @param dateStr
	 * 		时间格式str.
	 * @return boolean.
	 */
	public static boolean isDateSunday(String dateStr) {
		return isDateSunday(dateStr, getFormater("yyyy-MM-dd"));
	}

	public static boolean isDateSunday(String dateStr, DateFormat formatStr) {
		if (dateStr == null) {
			return false;
		}
		Date bdate;
		try {
			bdate = formatStr.parse(dateStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException(AppUtilsContextWrapper.getString(R.string.jiexiriqichucuo) + dateStr, e);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);
		return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
	}

	/**
	 * 判断莫个日期string是否为周日
	 *
	 * @param dateStr
	 * 		时间格式str.
	 * @return boolean.
	 */
	public static String getWeekDay(String dateStr) {
		return getWeekDay(dateStr, getFormater("yyyy-MM-dd"));
	}

	public static String getWeekDay(String dateStr, DateFormat formatStr) {
		if (dateStr == null) {
			return "";
		}
		Date bdate;
		try {
			bdate = formatStr.parse(dateStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException(AppUtilsContextWrapper.getString(R.string.jiexiriqichucuo) + dateStr, e);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);
		return cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY ? AppUtilsContextWrapper.getString(R.string.xingqiyi) :
				cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY ? AppUtilsContextWrapper.getString(R.string.xingqier) :
						cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY ? AppUtilsContextWrapper.getString(R.string.xingqisan) :
								cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY ? AppUtilsContextWrapper.getString(R.string.xingqisi) :
										cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY ? AppUtilsContextWrapper.getString(R.string.xingqiwu) :
												cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ? AppUtilsContextWrapper.getString(R.string.xingqiliu) :
														cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ? AppUtilsContextWrapper.getString(R.string.xingqiri) : "";
	}

	/**
	 * 判断两个日日期是否为同一周
	 *
	 * @param date2
	 * 		是今天.
	 * @return boolean.
	 */
	public static boolean isSameDate(String date1, String date2) {
		DateFormat format = getFormater("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(date1);
			d2 = format.parse(date2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(d1);
		cal2.setTime(d2);
		int subYear = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
		//subYear==0,说明是同一年
		if (subYear == 0) {
			if (cal2.get(Calendar.DAY_OF_YEAR) - cal1.get(Calendar.DAY_OF_YEAR) < LESS_A_WEEK) {
				return true;
			}
			//隔一年
		}
		else if (subYear == 1) {
			if (cal2.get(Calendar.DAY_OF_YEAR) + 365 - cal1.get(Calendar.DAY_OF_YEAR) < LESS_A_WEEK) {
				return true;
			}
			//隔大于一年
		}
		else if (subYear > 1) {
			return false;
		}
		return false;
	}

	/**
	 * 判断两个日日期是否为三个月内
	 *
	 * @param date2
	 * 		是今天.
	 * @return boolean.
	 */
	public static boolean inThreeMonth(String date1, String date2) {
		DateFormat format = getFormater("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(date1);
			d2 = format.parse(date2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(d1);
		cal2.setTime(d2);
		int subYear = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
		//subYear==0,说明是同一年
		if (subYear == 0) {
			if (cal2.get(Calendar.DAY_OF_YEAR) - cal1.get(Calendar.DAY_OF_YEAR) < LESS_THREE_MONTH) {
				return true;
			}
			//隔一年
		}
		else if (subYear == 1) {
			if (cal2.get(Calendar.DAY_OF_YEAR) + 365 - cal1.get(Calendar.DAY_OF_YEAR) < LESS_THREE_MONTH) {
				return true;
			}
			//隔大于一年
		}
		else if (subYear > 1) {
			return false;
		}
		return false;
	}

	/** 返回当月最后一天的日期 */
	public static Date getLastDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		// 设置日期为本月最大日期
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return calendar.getTime();
	}

	/** 返回当月第一天的日期 */
	public static Date getFirstDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		// 设置日期为本月最大日期
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		return calendar.getTime();
	}


	/*
	判断是否是这个月后几天
	 */
	public static boolean isThisMonthLastDays(int days) {

		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(new Date());
		fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fromCalendar.set(Calendar.MINUTE, 0);
		fromCalendar.set(Calendar.SECOND, 0);
		fromCalendar.set(Calendar.MILLISECOND, 0);


		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(getLastTime(getLastDayOfMonth()));//得到这个月的最后一天的最后一个时刻
		toCalendar.set(Calendar.HOUR_OF_DAY, 0);
		toCalendar.set(Calendar.MINUTE, 0);
		toCalendar.set(Calendar.SECOND, 0);
		toCalendar.set(Calendar.MILLISECOND, 0);


		return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24) < days;
	}

	/**
	 * 判断现在时刻是否是今天某时刻到某时刻之间
	 *
	 * @param startClock
	 * @param endClock
	 * @return
	 */
	public static boolean isInTime(int startClock, int endClock) {
		Calendar cal = Calendar.getInstance();// 当前日期
		int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
		return (hour >= startClock && hour < endClock ? true : false);
	}

	/*
	判断是否是这个月前几天
	 */
	public static boolean isThisMonthFrontDays(int days) {

		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(new Date());
		fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fromCalendar.set(Calendar.MINUTE, 0);
		fromCalendar.set(Calendar.SECOND, 0);
		fromCalendar.set(Calendar.MILLISECOND, 0);

		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(getZeroTime(getFirstDayOfMonth()));//得到这个月的第一天的最早一个时刻
		toCalendar.set(Calendar.HOUR_OF_DAY, 0);
		toCalendar.set(Calendar.MINUTE, 0);
		toCalendar.set(Calendar.SECOND, 0);
		toCalendar.set(Calendar.MILLISECOND, 0);

		return (fromCalendar.getTime().getTime() - toCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24) < days;

	}


	/**
	 * 格式化时间戳成 yyyy-MM-dd HH:mm:ss
	 *
	 */
	public static String formatLongToDate(long time) {
		DateFormat sdf = getFormater(AppUtilsContextWrapper.getString(R.string.yue));
		Date date = new Date(time * 1000);
		return sdf.format(date);
	}


	/**
	 * 将字符串形式的日期转成时间戳,以毫秒为基数
	 *
	 * @return 失败返回null
	 */
	public static Long stringToLong(String date, String regx) {
		if (TextUtils.isEmpty(date)) {
			return 0L;
		}
		DateFormat format =getFormater(regx);
		try {
			return format.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取明天的日期
	 *
	 */
	public static Date getTomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * 获取昨天的日期
	 *
	 * @return
	 */
	public static Date getYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 将一种格式的日期字符串转换为另一种格式的日期字符串
	 *
	 * @param aSourceDateText
	 * 		原日期字符串
	 * @param aSourceDateFormat
	 * 		原日期字符串对应的格式, 如"yyyyMMdd", "yyyy-MM-dd"等
	 * @param aDestDateFormat
	 * 		目标日期字符串格式
	 * @return 目标日期字符串格式对应的日期字符串
	 */
	public static String convertDateString(String aSourceDateText,
                                           String aSourceDateFormat,
                                           String aDestDateFormat) {
		try {
			Date tDestDate = getFormater(aSourceDateFormat).parse(aSourceDateText);
			return getFormater(aDestDateFormat).format(tDestDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static int getDayFromDate(String aSourceDateText, String aSourceDateFormat) {
		try {
			Date tDestDate = getFormater(aSourceDateFormat).parse(aSourceDateText);
			return Integer.valueOf(getFormater("dd").format(tDestDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int getMonthFromDate(String aSourceDateText, String aSourceDateFormat) {
		try {
			Date tDestDate = getFormater(aSourceDateFormat).parse(aSourceDateText);
			return Integer.valueOf(getFormater("MM").format(tDestDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static boolean isToday(String aSourceDateText, String aSourceDateFormat){
		try {
			Date tDate = getFormater(aSourceDateFormat).parse(aSourceDateText);
			String tDateText = getFormater("yyyyMMdd").format(tDate);
			String tTodayText = getFormater("yyyyMMdd").format(new Date());
			return tDateText.equals(tTodayText);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isThisMonth(String aSourceDateText, String aSourceDateFormat)
	{
		try {
			Date tDate = getFormater(aSourceDateFormat).parse(aSourceDateText);
			String tDateText = getFormater("yyyyMM").format(tDate);
			String tThisMonthText = getFormater("yyyyMM").format(new Date());
			return tDateText.equals(tThisMonthText);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取某个月有多少天
	 *
	 * @param year
	 * 		该月份所在的年份
	 * @param month
	 * 		月份，从1开始
	 */
	public static int getTotalDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);//Java月份才0开始算
		return cal.getActualMaximum(Calendar.DATE);
	}


	/**
	 * 获取某个月份的所有日期
	 */
	@SuppressWarnings("WrongConstant")
	public static List<Date> getDatesByYearAndMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		List<Date> list = new ArrayList<>();
		do {
			list.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		} while ((month - 1) == calendar.get(Calendar.MONTH));
		return list;

	}

	public static String format(long mill, String regx) {
		DateFormat sdf = getFormater(regx);
		Date date = new Date();
		date.setTime(mill);
		return sdf.format(date);
	}

	/**
	 * 格式化时间戳成 yyyy-MM-dd HH:mm
	 *
	 */
	public static String formatLongToDateStr(long time) {
		DateFormat tFormater = getFormater("yyyy-MM-dd HH:mm");
		Date date = new Date(time * 1000);
		return tFormater.format(date);
	}

	/**
	 * 格式化时间戳成 yyyy-MM-dd
	 *
	 */
	public static String formatLongToDateStr2(long time) {
		Date date = new Date(time * 1000);
		return getFormater("yyyy-MM-dd").format(date);
	}

	public static String formatLongToDateStr(long timeInSec, String destFormat) {
		DateFormat tFormater = getFormater(destFormat);
		Date date = new Date(timeInSec * 1000);
		return tFormater.format(date);
	}

	public static String timestamp2date(long timestampMill, String format) {
		DateFormat tFormater = getFormater(format);
		Date date = new Date(timestampMill);
		return tFormater.format(date);
	}

	/**
	 * 获取某个日期的上一个月
	 *
	 * @return 如果传入的时间或格式错误会返回“197001”
	 */
	public static String lastMonth(String thisMonth, String regx) {
		Calendar calendar = Calendar.getInstance();
		DateFormat sdf = getFormater(regx);

		try {
			Date date = sdf.parse(thisMonth);
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -1);
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return "197001";
		}
	}

	/**
	 * 获取当前日期的下一个月
	 *
	 */
	public static String getNextMonth(String thisMonth, String regx) {
		Calendar calendar = Calendar.getInstance();
		DateFormat sdf = getFormater(regx);

		try {
			Date date = sdf.parse(thisMonth);
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 1);
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return "197001";
		}
	}


	/**
	 * date2比date1多的天数
	 */
	public static int differentDays(Date date1, Date date2)
	{
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1= cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if(year1 != year2)   //同一年
		{
			int timeDistance = 0 ;
			for(int i = year1 ; i < year2 ; i ++)
			{
				if(i%4==0 && i%100!=0 || i%400==0)    //闰年
				{
					timeDistance += 366;
				}
				else    //不是闰年
				{
					timeDistance += 365;
				}
			}

			return timeDistance + (day2-day1) ;
		}
		else    //不同年
		{
			return day2-day1;
		}
	}

	/**
	 * 计算两个日期相差的月份
	 */
	public static int differentMonth(Date date1, Date date2){
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);

		if(c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
			return Math.abs(c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH));
		}else{
			if(date1.before(date2)){
				Calendar ct = c1;
				c1 = c2;
				c2 = ct;
			}
			int monthSpace = 12 - c2.get(Calendar.MONTH)+c1.get(Calendar.MONTH);
			return monthSpace + 12 * (Math.abs(c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR))-1);
		}
	}

	/**
	 * 将一个字符串转成Date类型
	 * @param str 要转的字符串
	 * @param format 字符串的格式
	 * @return 格式错误返回null
	 */
	public static Date strToDate(String str, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressLint("SimpleDateFormat")
	public static DateFormat getFormater(String aFormat) {
		if(!mFormatsMap.containsKey(aFormat)) {
			mFormatsMap.put(aFormat, new SimpleDateFormat(aFormat));
		}
		return mFormatsMap.get(aFormat);
	}
}
