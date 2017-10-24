package middlem.person.utilsmodule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***********************************************
 *
 * <P> desc:   字符检测工具类
 * <P> Author: gongtong
 * <P> Date: 2017-10-24 21:47
 ***********************************************/

public class WordUtils {

    //邮箱正则表达式.
    private final static String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * <code>IP地址检验正则表达式</code>.
     */
    private static final String IP_REGEX = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
    private static final String IP_REGEX1 = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.?$";
    private static final String IP_REGEX2 = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.?$";
    private static final String IP_REGEX3 = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.?$";
    private static final String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    private static final String DATE_REGEX2 = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
            + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";


    private WordUtils() {

    }

    /**
     * 得到字符数,一个中文字符对应两个字符.
     *
     * @param s 源字符串.
     * @return 字符数.
     */
    public static int getWordCount(String s) {
        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length;
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
     * 是否为邮箱
     *
     * @param source
     * @return boolean
     */
    public static boolean isEmail(String source) {
        String EMAIL_BASE_PATTERN = "['_A-Za-z0-9-&]+(\\.['_A-Za-z0-9-&]+)*[.]{0,1}@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))";
        return StringUtils.regexCheck(source, EMAIL_BASE_PATTERN);
    }

    /**
     * 判断是否是IP地址
     *
     * @param source
     * @return boolean
     */
    public static boolean isIp(String source) {
        String IP_REGEX = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        return StringUtils.regexCheck(source, IP_REGEX);
    }

    /**
     * 判断是否在输入IP地址
     *
     * @param source
     * @return boolean
     */
    public static boolean isIpInput(String source) {
        String IP_REGEX1 = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.?$";
        String IP_REGEX2 = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.?$";
        String IP_REGEX3 = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.?$";
        return StringUtils.regexCheck(source, IP_REGEX1) ||
                StringUtils.regexCheck(source, IP_REGEX2) ||
                StringUtils.regexCheck(source, IP_REGEX3);
    }

    /**
     * 是否为有效的日期格式,目前只支持yyyy-MM-dd 其他任何格式全部返回false
     * 如:1984-01-01  返回true
     * 19840101      返回false
     * 1984-1-01   返回false
     *
     * @param source
     * @return boolean
     */
    public static boolean isValidDate(String source) {
        String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";
        String DATE_REGEX2 = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        return StringUtils.regexCheck(source, DATE_REGEX) && StringUtils.regexCheck(source, DATE_REGEX2);
    }

    /**
     * 检测手机号码有效性
     * <p>是否11位，且已1开头</p>
     *
     * @param mobile
     * @return boolean
     */
    public static boolean isMobileNo(String mobile) {
        String regex = "^1\\d{10}$";
        return StringUtils.regexCheck(mobile, regex);
    }

    public static boolean checkTelephone(String telephone) {
        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        return check(telephone, regex);
    }
    /**
     * 判断是否为固定电话号码
     *
     * @param number
     *            固定电话号码   验证传真号码
     * @return
     */
    /**
     * 验证固话号码
     *
     * @return
     */
    public static boolean check(String str, String regex) {
        boolean flag = false;
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 检验身份证号码有效性
     *
     * @param idCard
     * @return boolean
     */
    public static boolean isIDCard(String idCard) {
        int idCardNum = 18;
        if (idCard.length() != idCardNum) {
            return false;
        }
        String template = "xyzXYZ";
        String tempChar;
        for (int i = 0; i < idCardNum; i++) {
            tempChar = String.valueOf(idCard.charAt(i));
            if (template.contains(tempChar)) {
                if (i == (idCardNum - 1)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 验证QQ号码
     *
     * @param QQ
     * @return
     */
    public static boolean isQQNo(String QQ) {
        String regex = "^[1-9][0-9]{4,} $";
        return StringUtils.regexCheck(QQ, regex);
    }
}
