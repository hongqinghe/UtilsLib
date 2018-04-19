package middlem.person.utilsmodule.comutils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***********************************************
 *
 * <P> desc:   String工具类（添加数据保护机制）
 * <P> Author: gongtong
 * <P> Date:   2017-10-24 21:34
 ***********************************************/

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 判断字符串是否还有非法字符
     * @param str
     * @return boolean
     */
    public static boolean isStringFilter(String str) {
        String regEx="[`~!@#$%^&*+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p   =   Pattern.compile(regEx);
        Matcher m   =   p.matcher(str);

        if(m.find()) {
            return false;
        }

        return true;
    }

    // 将textview中的字符全角化。即将所有的数字、字母及标点全部转为全角字符，使它们与汉字同占两个字节，这样就可以避免由于占位导致的排版混乱问题了
    public static String ToDBC(String input) {
        if(input == null){
            return new String();
        }
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return boolean
     */
    public static boolean isStrEmpty(String str) {
        return isBlank(str) && isEmpty(str);
    }

    /**
     * 判断字符串是否相等
     *
     * @param source
     * @param target
     * @return boolean
     */
    public static boolean isEqual(String source, String target) {
        if (isStrEmpty(source)) {
            return isStrEmpty(target);
        } else {
            return source.equals(target);
        }
    }

    /**
     * String的安全检测
     * 为空则返回""
     *
     * @param source
     * @return
     */
    public static String saveCheck(String source) {
        return isStrEmpty(source) ? "" : source;
    }

    /**
     * 得到字符数
     * 一个中文字符对应两个字符
     *
     * @param str
     * @return int
     */
    public static int getStrCount(String str) {
        str = str.replaceAll("[^\\x00-\\xff]", "**");
        int length = str.length();
        return length;
    }
    
    /**
     * 字符全角化
     * 将所有的数字、字母及标点全部转为全角字符
     * 使它们与汉字同占两个字节，这样就可以避免由于占位导致的排版混乱问题了
     *
     * @param source
     * @return String
     */
    public static String doDBC(String source) {
        if (source == null) {
            return new String();
        }
        char[] c = source.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * encode字符串
     * @param str
     * @return String
     */
    public static String encodeStr(String str) {
        try {
            str = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return str;
    }


    /**
     * unicode字符化
     *
     * @param str
     * @return String
     */
    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

    /**
     * 汉字转拼音
     *
     * @param src
     * @return String
     */
//    public static String getPinYin(String src) {
//        char[] t1 = null;
//        t1 = src.toCharArray();
//        // System.out.println(t1.length);
//        String[] t2 = new String[t1.length];
//        // System.out.println(t2.length);
//        // 设置汉字拼音输出的格式
//        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
//        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
//        String t4 = "";
//        int t0 = t1.length;
//        for (int i =0; i < t0; i++) {
//            // 判断能否为汉字字符
//            if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
//                t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中
//                t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
//            } else {
//                // 如果不是汉字字符，间接取出字符并连接到字符串t4后
//                t4 += Character.toString(t1[i]);
//            }
//        }
//        return t4;
//    }

    /**
     * 手机号码格式化
     * <p>1xx-xxxx-xxxx</p>
     * @param phone
     * @return String
     */
    public static String formatPhoneNum(String phone){
        if (isStrEmpty(phone) || !WordUtils.isMobileNo(phone)) {
            return phone;
        }

        String phoneStr1 = phone.substring(0, 3);
        String phoneStr2 = phone.substring(3, 7);
        String phoneStr3 = phone.substring(7, 11);

        return phoneStr1 + "-" + phoneStr2 + "-" + phoneStr3;
    }


    /**
     * 去除所有空格
     * @param source
     * @return String
     */
    public static String preProcess(String source) {
        if (source == null) {
            return null;
        }
        String result = source.replaceAll(",", "");
        result = result.replaceAll(" ", "");
        return result;
    }

    public static boolean regexCheck(String str, String regex) {
        boolean flag = false;
        if (!isStrEmpty(str) && !isStrEmpty(regex)) {
            try {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(str);
                flag = matcher.matches();
            } catch (Exception e) {
                flag = false;
            }
        }
        return flag;
    }


    /**
     * 把空数据转成null.
     *
     * @param source
     *            原始值.
     * @return 转换后的值.
     */
    public static String emptyToNull(String source) {
        return StringUtils.isEmpty(source) ? null : source;
    }

    /**
     * 把null转换称空串.
     *
     * @param source
     *            原始串.
     * @return
     */
    public static String nullToEmpty(String source) {
        return StringUtils.isEmpty(source) ? "" : source;
    }
}
