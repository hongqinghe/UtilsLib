package middlem.person.utilsmodule;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/***********************************************
 *
 * <P> desc:    价格优化类
 * <P> Author: gongtong
 * <P> Date: 2017-10-24 21:33
 ***********************************************/

public class PriceUtils {
    private final static NumberFormat FORMAT_PRICE = new DecimalFormat("##0.00");//保留2位小数

    private PriceUtils() {

    }

    /**
     * String->Long
     * @param source
     * @return
     */
    public static Long toPriceLong(String source) {
        if (StringUtils.isEmpty(source)) {
            return new Long(0);
        }
        BigDecimal price = new BigDecimal(StringUtils.preProcess(source));
        Double d = price.multiply(new BigDecimal("100")).doubleValue();
        return d.longValue();
    }

    /**
     * String->BigDecimal
     * @param source
     * @return
     */
    public static BigDecimal toPriceBigDecimal(String source) {
        if (StringUtils.isEmpty(source)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(StringUtils.preProcess(source));
    }

    /**
     * 价格格式化(保留两位小数)
     * @param source
     * @return
     */
    public static String formatPrice(String source) {
        if (StringUtils.isEmpty(source)) {
            return "";
        }
        BigDecimal price = new BigDecimal(StringUtils.preProcess(source));
        return FORMAT_PRICE.format(price);
    }

    /**
     * Long->String(保留两位小数)
     * @param source
     * @return
     */
    public static String formatPrice(Long source) {
        if (source == null) {
            source = new Long(0);
        }
        BigDecimal price = new BigDecimal(source);
        Double d = price.divide(new BigDecimal("100")).doubleValue();
        return FORMAT_PRICE.format(d);
    }

    /**
     * BigDecimal->String(保留两位小数)
     * @param source
     * @return
     */
    public static String formatPrice(BigDecimal source) {
        if (source == null) {
            source = new BigDecimal(0);
        }
        Double d = source.doubleValue();
        return FORMAT_PRICE.format(d);
    }

    /**
     * Long->String(取整数位)
     * @param source
     * @return
     */
    public static String truncPrice (Long source) {
        String str = formatPrice(source);
        if (str.contains(".00")) {
            str = str.substring(0, str.indexOf("."));
        }
        return str;
    }

    /**
     * double->String(取整数位)
     * @param source
     * @return
     */
    public static String truncPrice(double source) {
        BigDecimal tSource = new BigDecimal(source);
        return formatPrice(tSource);
    }
}
