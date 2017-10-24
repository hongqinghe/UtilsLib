package middlem.person.utilsmodule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/***********************************************
 *
 * <P> desc:  数据类型转换处理类
 * <P> Author: gongtong
 * <P> Date: 2017-10-24 21:04
 ***********************************************/

public class ConvertUtils {

    private final static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    private final static FastDateFormat df = FastDateFormat.getInstance("yyyy-MM-dd");
    private final static FastDateFormat tf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    private final static FastDateFormat taf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");

    private final static NumberFormat nf = new DecimalFormat("####");
    private final static NumberFormat nf2 = new DecimalFormat("#,###.##");
    private final static NumberFormat nf3 = new DecimalFormat("##0.00");//保留2位小数
    private final static NumberFormat nf4 = new DecimalFormat("##0.0");//保留1位小数

    private final static int DEFAULT_ROUND = BigDecimal.ROUND_HALF_UP;

    private final static int defaultLenth = 50;

    private ConvertUtils() {

    }
    /**
     * 日期转换为字符串格式.
     * @param date 日期.
     */
    public static String toString(Date date) {
        if(date == null){
            return "";
        }
        return df.format(date);
    }
    /**
     * 日期转换为字符串格式.
     * @param date 日期.
     */
    public static String toString(Calendar date) {
        return df.format(date);
    }
    /**
     * 长整型转换成字符串.
     * @param source 源数据.
     * @return 转换后的字符串.
     */
    public static String toString(Long source) {
        if (source == null) {
            return "";
        }
        return nf.format(source);
    }
    /**
     * 整型转换成字符串.
     * @param source 源数据.
     * @return 转换后的字符串.
     */
    public static String toString(Integer source) {
        if (source == null) {
            return "";
        }
        return nf.format(source);
    }
    /**
     * 短整型转换成字符串.
     * @param source 源数据.
     * @return 转换后的字符串.
     */
    public static String toString(Short source) {
        if (source == null) {
            return "";
        }
        return nf.format(source);
    }

    /**
     * 短整型转换成字符串.
     * @param source 源数据.
     * @return 转换后的字符串.
     */
    public static String toString(Double source) {
        if (source == null) {
            return "0";
        }
        return nf2.format(source);
    }


    /**
     * Float转换成字符串.
     * @param source 源数据.
     * @return 转换后的字符串.
     */
    public static String toString(Float source) {
        if (source == null) {
            return "0";
        }
        return nf2.format(source);
    }

    /**
     * 转换成Short类型.
     * @param source 源数据.
     * @return 转换结果.
     */
    public static Date toDate(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        try {
            return SDF.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 转换成Short类型.
     * @param source 源数据.
     * @return 转换结果.
     */
    public static Short toShort(String source) {
        source = preProcess(source);
        return NumberUtils.toShort(source);
    }
    /**
     * 转换成Integer类型.
     * @param source 源数据.
     * @return 转换结果.
     */
    public static Integer toInteger(String source) {
        source = preProcess(source);
        return NumberUtils.toInt(source);
    }
    /**
     * 转换成Long类型.
     * @param source 源数据.
     * @return 转换结果.
     */
    public static Long toLong(String source) {
        source = preProcess(source);
        return NumberUtils.toLong(source);
    }
    /**
     * 转换成Double类型.
     * @param source 源数据.
     * @return 转换结果.
     */
    public static Double toDouble(String source) {
        source = preProcess(source);
        return NumberUtils.toDouble(source);
    }

    private static String preProcess(String source) {
        if (source == null) {
            return null;
        }
        String result = source.replaceAll(",", "");
        result = result.replaceAll(" ", "");
        return result;
    }

    /**
     * dp->px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float density = SystemUtils.getScreenDensity(context);
        return (int) (dpValue * density + 0.5f);
    }

    /**
     * px->dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        float density = SystemUtils.getScreenDensity(context);
        return (int) (pxValue / density + 0.5f);
    }

    /**
     * Long->String
     *
     * @param source
     * @return String
     */
    public static String long2String(Long source) {
        return long2String(source, "");
    }

    /**
     * Long->String
     * @param source
     * @param format
     * @return
     */
    public static String long2String(Long source, String format) {
        return NumberUtils.format(source, format);
    }

    /**
     * String->long
     * @param val
     * @param defaultVal
     * @return long
     */
    public static final long string2Long(String val, long defaultVal) {
        if (StringUtils.isStrEmpty(val) || !TextUtils.isDigitsOnly(val)) {
            return defaultVal;
        } else {
            try {
                return NumberUtils.toLong(StringUtils.preProcess(val));
            } catch (Exception e) {
                e.printStackTrace();
                return defaultVal;
            }
        }
    }

    /**
     * Integer->String
     *
     * @param source
     * @return String
     */
    public static String int2String(Integer source) {
        return int2String(source, "");
    }

    /**
     * Integer->String
     *
     * @param source
     * @param format
     * @return
     */
    public static String int2String(Integer source, String format) {
        return NumberUtils.format(source, format);
    }

    /**
     * String->int
     *
     * @param val
     * @param defaultVal
     * @return int
     */
    public static final int string2Int(String val, int defaultVal) {
        if (StringUtils.isStrEmpty(val) || !TextUtils.isDigitsOnly(val)) {
            return defaultVal;
        } else {
            try {
                return NumberUtils.toInt(StringUtils.preProcess(val));
            } catch (Exception e) {
                e.printStackTrace();
                return defaultVal;
            }
        }
    }

    /**
     * Short->String
     *
     * @param source
     * @return String
     */
    public static String short2String(Short source) {
        return short2String(source, "");
    }

    /**
     * Short->String
     *
     * @param source
     * @param format
     * @return
     */
    public static String short2String(Short source, String format) {
        return NumberUtils.format(source, format);
    }
    /**
     * 默认为四舍五入遇5上进.
     * @param d
     * @param i
     * @return
     */
    public static double format(Double d, int i, int _round_type){
        if (d == null) {
            return 0.00;
        }else{
            return 	new BigDecimal(d).setScale(i,_round_type).doubleValue();
        }
    }
    /**
     * 默认为四舍五入遇5上进.
     * @param d
     * @param i
     * @return
     */
    public static double format(Double d, int i){
        return format(d, i,DEFAULT_ROUND);
    }

    @SuppressLint("DefaultLocale")
    public static String getTimeFormat(int monthOrday) {
        return String.format("%02d", monthOrday);
    }

    public static String toPriceStr(Long source) {
        if(source == null){
            source = new Long(0);
        }
        BigDecimal price = new BigDecimal(source);
        Double d = price.divide(new BigDecimal("100")).doubleValue();
        return nf3.format(d);
    }

    public static String toPriceStr(BigDecimal source) {
        if(source == null){
            source = new BigDecimal(0);
        }
        Double d = source.doubleValue();
        return nf3.format(d);
    }


    public static String toNumberStr(String source) {
        if(StringUtils.isEmpty(source)){
            return "";
        }
        source = preProcess(source);
        BigDecimal price;
        try {
            price = new BigDecimal(source);
        }catch(Exception e){
            price = new BigDecimal(0);
        }
        return nf3.format(price);
    }

    public static String toPriceIntStr(BigDecimal source) {
        if(source == null){
            source = new BigDecimal(0);
        }
        Double d = source.doubleValue();
        return nf.format(d);
    }

    /**
     * 时间转换为字符串格式.
     * @param time 时间.
     * @return 日期.
     */
    public static String toTimesString(long time) {
        return taf.format(time);
    }

    /**
     * 根据传入的格式将String类型的日期转换为Long
     *
     * @author tianml
     * @param dateStr
     *            日期
     * @param formatStr
     *            转换格式
     * @return 返回转换后的Long类型日期
     */
    public static Long dateFormat_stringToLong(String dateStr, String formatStr) {
        SimpleDateFormat df = new SimpleDateFormat(formatStr);
        try {
            return df.parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断是否越界.越界返回true,未越界返回false,null返回false.
     * @param lenth
     * @param s
     * @return
     */
    public static boolean isOverLenth(int lenth,String s){
        if(s == null){
            return false;
        }else  if(WordUtils.getWordCount(s.trim())>lenth){
            return true;
        }else{
            return false;
        }
    }

    /**
     * String->Short
     *
     * @param val
     * @param defaultVal
     * @return int
     */
    public static final Short string2Short(String val, Short defaultVal) {
        if (StringUtils.isStrEmpty(val) || !TextUtils.isDigitsOnly(val)) {
            return defaultVal;
        } else {
            try {
                return NumberUtils.toShort(StringUtils.preProcess(val));
            } catch (Exception e) {
                e.printStackTrace();
                return defaultVal;
            }
        }
    }

    /**
     * Float->String
     *
     * @param source
     * @return String
     */
    public static String float2String(Float source) {
        return float2String(source, "");
    }

    /**
     * Float->String
     *
     * @param source
     * @param format
     * @return
     */
    public static String float2String(Float source, String format) {
        return NumberUtils.format(source, format);
    }

    /**
     * String->Float
     *
     * @param val
     * @param defaultVal
     * @return int
     */
    public static final Float string2Float(String val, Float defaultVal) {
        if (StringUtils.isStrEmpty(val)) {
            return defaultVal;
        } else {
            try {
                return NumberUtils.toFloat(StringUtils.preProcess(val));
            } catch (Exception e) {
                e.printStackTrace();
                return defaultVal;
            }
        }
    }

    /**
     * Double->String
     *
     * @param source
     * @return String
     */
    public static String double2String(Double source) {
        return double2String(source, "");
    }

    /**
     * Double->String
     *
     * @param source
     * @param format
     * @return
     */
    public static String double2String(Double source, String format) {
        return NumberUtils.format(source, format);
    }

    /**
     * String->Double
     *
     * @param val
     * @param defaultVal
     * @return int
     */
    public static final double string2Double(String val, Double defaultVal) {
        if (StringUtils.isStrEmpty(val)) {
            return defaultVal;
        } else {
            try {
                return NumberUtils.toDouble(StringUtils.preProcess(val));
            } catch (Exception e) {
                e.printStackTrace();
                return defaultVal;
            }
        }
    }

    /**
     * String->boolean
     * @param str
     * @return boolean
     */
    public static boolean string2Boolean(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        } else if (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("1")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Object->int
     * @param obj
     * @param defaultValue
     * @return int
     */
    public static int objectToInteger(Object obj, int defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(String.valueOf(obj));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * Object->boolean
     * @param obj
     * @param defaultVal
     * @return boolean
     */
    public static boolean object2Boolean(Object obj, boolean defaultVal) {
        if (obj == null) {
            return defaultVal;
        } else {
            return string2Boolean(object2String(obj, "false"));
        }
    }

    /**
     * Object->String
     * @param obj
     * @param defaultVal
     * @return String
     */
    public static String object2String(Object obj, String defaultVal) {
        if (obj == null) {
            return defaultVal;
        } else {
            return String.valueOf(obj);
        }
    }

    /**
     * 对象->长整型
     * @param obj
     * @param defaultVal
     * @return long
     */
    public static long object2Long(Object obj, long defaultVal) {
        if (obj == null) {
            return defaultVal;
        }
        return string2Long(String.valueOf(obj), defaultVal);
    }

    /**
     * 对象->双精度型
     * @param obj
     * @param defaultVal
     * @return double
     */
    public static double object2Double(Object obj, double defaultVal) {
        if (obj == null) {
            return defaultVal;
        }
        return string2Double(String.valueOf(obj), defaultVal);
    }

    /**
     * 对双精度型->整型
     * @param d
     * @return int
     */
    public static int double2Int(Double d) {
        try {
            return d.intValue();
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 对双精度型->长整型
     * @param d
     * @return long
     */
    public static long double2Long(Double d) {
        try {
            return d.longValue();
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * boolean->String
     * @param source
     * @return String
     */
    public static String boolean2String(Boolean source) {
        if (source == null) {
            return "";
        }
        return String.valueOf(source);
    }

    /**
     * bytes->String
     *
     * @param source
     * @return String
     */
    public static String bytes2String(byte[] source) {
        if (source == null || source.length == 0) {
            return "";
        }
        try {
            return new String(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 日期转换为字符串格式.
     * @param time 时间.
     * @return 日期.
     */
    public static String toDateString(long time) {
        return df.format(time);
    }


    /**
     * @param resource 源数值（Double类型）
     * @return 乘以100后的精准long值
     */
    public static long getMultiLongValueResult(double resource){
        BigDecimal originNum = new BigDecimal(Double.toString(resource));
        BigDecimal target = new BigDecimal(Double.toString(100));
        BigDecimal result = originNum.multiply(target);// 相乘结果
        System.out.println(result);
        BigDecimal one = new BigDecimal("1");
        long longValue = result.divide(one,2, BigDecimal.ROUND_HALF_UP).longValue();
        return longValue;
    }
}
