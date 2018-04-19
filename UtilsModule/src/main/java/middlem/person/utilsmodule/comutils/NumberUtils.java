/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2000, 2007 浙江钻木电子科技有限公司
 *
 * 工程名称：	com.zmsoft.eatery
 * 创建者：	黄晓峰 创建日期： 2007-10-23
 * 创建记录：	创建类结构。
 *
 * ************************* 变更记录 ********************************
 * 修改者： 
 * 修改日期：
 * 修改记录：
 *
 *
 * ......************************* To Do List*****************************
 *
 *
 * Suberversion 信息
 * ID:			$Id$
 * 源代码URL：	$HeadURL$
 * 最后修改者：	$LastChangedBy$
 * 最后修改日期：	$LastChangedDate$
 * 最新版本：		$LastChangedRevision$
 **/

package middlem.person.utilsmodule.comutils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
/***********************************************
 *
 * <P> desc:    数字相关的工具类
 * <P> Author: gongtong
 * <P> Date: 2017-10-24 21:32
 ***********************************************/

public final class NumberUtils extends org.apache.commons.lang3.math.NumberUtils{
	public final static String NF_NORMAL = "####";
	public final static String NF_WITH_DOT = "#,###";
	public final static String NF_2_DECIMAL = "#,###.##";
	public final static String NF_WITH_ZERO = "#,##0.00";

	private final static int DEFAULT_ROUND = BigDecimal.ROUND_HALF_UP;

	private static final int PERCENT = 100;
	private static final double FLOOR = 0.5;
	public static final double ZERO = 10E-6;
	/**
	 * 私有构造.
	 */
	private NumberUtils() {

	}

	/**
	 * 数字格式化
	 *
	 * @param source
	 * @param format
	 * @return String
	 */
	public static String format(Integer source, String format) {
		String result = "";
		NumberFormat nf = new DecimalFormat(NF_NORMAL);
		if (!StringUtils.isStrEmpty(format)) {
			nf = new DecimalFormat(format);
		}
		if (source != null) {
			result = nf.format(source);
		}
		return result;
	}

	/**
	 * 数字格式化
	 *
	 * @param source
	 * @param format
	 * @return String
	 */
	public static String format(Long source, String format) {
		String result = "";
		NumberFormat nf = new DecimalFormat(NF_NORMAL);
		if (!StringUtils.isStrEmpty(format)) {
			nf = new DecimalFormat(format);
		}
		if (source != null) {
			result = nf.format(source);
		}
		return result;
	}

	/**
	 * 数字格式化
	 *
	 * @param source
	 * @param format
	 * @return String
	 */
	public static String format(Short source, String format) {
		String result = "";
		NumberFormat nf = new DecimalFormat(NF_NORMAL);
		if (!StringUtils.isStrEmpty(format)) {
			nf = new DecimalFormat(format);
		}
		if (source != null) {
			result = nf.format(source);
		}
		return result;
	}

	/**
	 * 数字格式化
	 *
	 * @param source
	 * @param format
	 * @return String
	 */
	public static String format(Float source, String format) {
		String result = "";
		NumberFormat nf = new DecimalFormat(NF_2_DECIMAL);
		if (!StringUtils.isStrEmpty(format)) {
			nf = new DecimalFormat(format);
		}
		if (source != null) {
			result = nf.format(source);
		}
		return result;
	}

	/**
	 * 数字格式化
	 *
	 * @param source
	 * @param format
	 * @return String
	 */
	public static String format(Double source, String format) {
		String result = "";
		NumberFormat nf = new DecimalFormat(NF_2_DECIMAL);
		if (!StringUtils.isStrEmpty(format)) {
			nf = new DecimalFormat(format);
		}
		if (source != null) {
			result = nf.format(source);
		}
		return result;
	}

	/**
	 * 四舍五入
	 *
	 * @param d
	 * @param i
	 * @return
	 */
	public static double round(Double d, int i, int _round_type) {
		if (d == null) {
			return 0.00;
		} else {
			return new BigDecimal(d).setScale(i, _round_type).doubleValue();
		}
	}

	/**
	 * 四舍五入（遇5上进）
	 *
	 * @param d
	 * @param i
	 * @return
	 */
	public static double round(Double d, int i) {
		return round(d, i, DEFAULT_ROUND);
	}

	/**
	 * 处理数字的四舍五入. 四对后两位小数进行处理.
	 *
	 * @param number
	 *            要处理的数字.
	 * @return 处理结果.
	 */
	public static Double round(Double number) {
		return Math.floor(number * PERCENT + FLOOR) / PERCENT;
	}

	/**
	 * 对double数据进行取精度.
	 * <p>
	 * For example: <br>
	 * double value = 100.345678; <br>
	 * double ret = round(value,4,BigDecimal.ROUND_HALF_UP); <br>
	 * ret为100.3457 <br>
	 *
	 * @param value
	 *            double数据.
	 * @param scale
	 *            精度位数(保留的小数位数).
	 * @param roundingMode
	 *            精度取值方式.
	 * @return 精度计算后的数据.
	 */
	public static double round(double value, int scale, int roundingMode) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		double d = bd.doubleValue();
		bd = null;
		return d;
	}

	/**
	 * 把一个浮点型数据转成有意义的数值,如果是null则转成0.
	 *
	 * @param source
	 * @return double
	 */
	private static double nullToZero(Double source) {
		return source == null ? 0 : source;
	}


	/**
	 * 把一个Short型的数据转成有意义的数值,如果是null则转成0.
	 *
	 * @param source
	 *            源Short型数据.
	 * @return 转换后的结果.
	 */
	public static Double nullToZero(Short source) {
		if (source == null) {
			return 0d;
		}
		return source.doubleValue();
	}

	/**
	 * 把一个Short型的数据转成有意义的数值,如果是null则转成0.
	 *
	 * @param source
	 *            源Short型数据.
	 * @return 转换后的结果.
	 */
	public static Short nullShortToZero(Short source) {
		if (source == null) {
			return 0;
		}
		return source;
	}

	/**
	 * 把一个Integer型的数据转成有意义的数值,如果是null则转成0.
	 *
	 * @param source
	 *            源Short型数据.
	 * @return 转换后的结果.
	 */
	public static Double nullToZero(Integer source) {
		if (source == null) {
			return 0d;
		}
		return source.doubleValue();
	}

	/**
	 * 把一个Integer型的数据转成有意义的数值,如果是null则转成0.
	 *
	 * @param source
	 *            源Short型数据.
	 * @return 转换后的结果.
	 */
	public static int nullIntToZero(Integer source) {
		if (source == null) {
			return 0;
		}
		return source;
	}

	public static long nullLongToZero(Long source) {
		if (source == null) {
			return 0;
		}
		return source;
	}

	/**
	 * 将Double型金额 * rule  转换成long型
	 * @param source
	 * @param rule
     * @return
     */
	public static long doubulRuleToLong(Double source, int rule){
		if(source == null){
			return 0L;
		}
		Double targ = source * rule;
		return targ.longValue();
	}

	/**
	 * 对数字的求和.
	 * @param a 被加数.
	 * @param b 加数.
	 * @return 结果.
	 */
	public static Double add(Double a, Double b) {
		return nullToZero(a) + nullToZero(b);
	}

	/**
	 * 对数字的求和,保证数字的精度不会被损失.
	 * @param a 被加数.
	 * @param b 加数.
	 * @return 结果.
	 */
	public static Double doubleToSum(Double a, Double b) {
		BigDecimal b1 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 判断double型数字是否为零(包括正负数).
	 * @param d 被加数.
	 * @return 结果.
	 */
	public static boolean isZero(double d) {
		return Math.abs(d) < ZERO;
	}

	public static boolean isZero(Double d) {
		if(d == null){
			return true;
		}
		return Math.abs(d) < ZERO;
	}

	/**
	 * 整数验证支持.
	 * @param source
	 * @return
	 */
	public static boolean isDigit(String source){
		String regex = "^-?[1-9]\\d*$";
		return StringUtils.isStrEmpty(source) && StringUtils.regexCheck(source, regex);
	}

	/**
	 * 是否是数字.
	 * @param source 源数字.
	 * @return true,是正常数字.
	 */
	public static boolean isNumber(String source) {
		String regex = "^\\d*[.]?\\d*$";
		if (StringUtils.isStrEmpty(source)) {
			return false;
		}

		if (!StringUtils.regexCheck(source, regex)) {
			return false;
		}

		String first = null;
		int split = source.indexOf('.');
		if (split == -1) {
			first = source;
		} else {
			first = source.substring(0, split);
		}
		if (first == null || first.length() == 0) {
			return true;
		}
		if (first.length() > 1 && first.startsWith("0")) {
			return false;
		}
		return true;
	}

	/**
	 * 是否相等
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean isEquals(Double a, Double b){
		if(a == null){
			return b == null;
		} else {

			return b != null && Math.abs(a - b) < 0.001;
		}
	}

	/**
	 * eg: 85除以10 = 8.5
	 * 		80除以10 = 8
	 * @param value
	 * @return
     */
	public static String parse(int value){
		return parse(value, 10);
	}
	public static String parse(int value, int denominator){
		if(value % denominator == 0){
			return String.valueOf(value/denominator);
		}else {
			return String.valueOf((float) value/denominator);
		}
	}
}
