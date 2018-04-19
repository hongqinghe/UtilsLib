package middlem.person.utilsmodule.comutils;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***********************************************
 *
 * <P> desc:    安全操作转化
 * <P> Author: gongtong
 * <P> Date: 2017-10-24 21:45
 ***********************************************/

public class SafeUtils {

    public  final  static String TAG = "SafeUtils";

    /**
     * 此方法主要用于ui界面显示的时候所有传值都需要通过此接口转化一下,保证数据null不出现的crash.
     * @param t 需要通过安全方法转化的对象
     * @param <T>
     * @return 字符串.
     */
    public static<T> String getSafeStringUiShow(T t){
        if(t == null) {
            return "";
        }else if(t instanceof Integer){
                return ConvertUtils.int2String((Integer) t);
        }else if(t instanceof Boolean){
            return ConvertUtils.boolean2String((Boolean) t);
        }else  if(t instanceof Double){
                return ConvertUtils.double2String((Double) t);
        }else  if(t instanceof Short){
                return ConvertUtils.short2String((Short) t);
        }else  if(t instanceof Long){
                return ConvertUtils.long2String((Long) t);
        }else if(t instanceof Float){
            return ConvertUtils.float2String((Float) t);
        }else if(t instanceof String){
            return (String)t;
        }else if(t instanceof Byte[]){
            return ConvertUtils.bytes2String((byte[]) t);
        }else{
            Log.e(TAG, "getSafeStringUiShow get a unSupport type");
            return "";
        }
    }
    /**
     * 如果是double或者float类型的话需要转化为保证2位精度,如果是int则保存0
     * 此方法主要用于ui界面显示的时候所有传值都需要通过此接口转化一下,保证数据null不出现的crash.
     * @param t 需要通过安全方法转化的对象
     * @param <T>
     * @return 字符串.
     */
    public static<T> String getSafeStringFroWidgetFund(T t){
        if(t == null) {
            return "一";
        }else if(t instanceof Integer){
                return ConvertUtils.int2String((Integer) t);
        }else  if(t instanceof Double){
                return ConvertUtils.double2String((Double) t);
        }else  if(t instanceof Short){
                return ConvertUtils.short2String((Short) t);
        }else  if(t instanceof Long){
                return ConvertUtils.long2String((Long) t);
        }else if(t instanceof Float){
            return ConvertUtils.float2String((Float) t);
        }else if(t instanceof String){
            return (String)t;
        }else{
            Log.e(TAG, "getSafeStringFroWidgetFund get a unSupport type");
            return "";
        }
    }
    /**
     * @param t 需要通过安全方法转化的对象
     * @param <T>
     * @return 字符串.
     */
    public static<T> String getSafeStringDefalutNull(T t){
        if(t == null) {
            return null;
        }else{
            return getSafeStringUiShow(t);
        }
    }

    /**
     * @param list
     * @param index
     * @param <T>
     * @return
     */
    public static <T> T safelyListGet(List<T> list, int index) {
        if (list != null) {
            int size = list.size();
            if (index >= 0 && index <= size - 1) {
                return list.get(index);
            }
        }
        return null;
    }


    /**
     * @param list
     * @param index
     * @param t
     * @param <T>
     */
    public static <T> void safelyListAdd(List<T> list, int index, T t) {
        if (list != null && t != null) {
            int size = list.size();
            if (index > size) {
                list.add(t);
            } else if (index < 0) {
                list.add(0, t);
            } else {
                list.add(index, t);
            }
        }
    }

    /**
     *
     * @param list
     * @param t
     * @param <T>
     */
    public static <T> void safelyListAdd(List<T> list, T t) {
        if (list != null && t != null) {
                list.add(t);
        }
    }

    /**
     *
     * @param array
     * @param index
     * @param <T>
     * @return
     */
    public static <T> T safelyArrayGet(T[] array, int index) {
        if (array != null) {
            int size = array.length;
            if (index >= 0 && index <= size - 1) {
                return array[index];
            }
        }
        return null;
    }

    /**
     *
     * @param array
     * @param index
     * @param t
     * @param <T>
     */
    public static <T> void safelyArrayPut(T[] array, int index, T t) {
        if (array != null && t != null) {
            int size = array.length;
            if (index >= 0 && index <= size - 1) {
                array[index] = t;
            }
        }
    }

    /**
     *
     * @param map
     * @param key
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> V safelyMapGet(Map<K,V> map, K key) {

        if (map != null && key != null) {
            return map.get(key);
        }
        return null;
    }

    /**
     *
     * @param map
     * @param key
     * @param value
     * @param <K>
     * @param <V>
     */
    public static <K,V> void safelyMapPut(Map<K,V> map, K key, V value) {
        if (map != null && key != null && value != null) {
            map.put(key,value);
        }
    }

    /**
     *
     * @param returnMap
     * @param mapChild
     * @param <K>
     * @param <V>
     */
    public static <K,V> void safelyMapPutAll(Map<K,V> returnMap, Map<K,V> mapChild) {
        if (returnMap != null && mapChild != null) {
            returnMap.putAll(mapChild);
        }
    }
    /**
     *
     * @param map
     * @param <K>
     * @param <V>
     */
    public static <K,V> Map<String,String> safeChange2StringMap(Map<K,V> map) {
        Map<String,String> returnMap = new HashMap<>();
        if (map != null) {
            for (K t : map.keySet()) {
                safelyMapPut(returnMap, getSafeStringDefalutNull(t), getSafeStringDefalutNull(map.get(t)));
            }
        }
        return returnMap;
    }
    /**
     *
     * @param map
     * @param <K>
     * @param <V>
     */
    public static <K,V> List<V> safeChangeBusinessLst(Map<K,V> map) {
        List<V> lst = new ArrayList<>();
        if (map != null) {
            for (V t : map.values()) {
                safelyListAdd(lst,t);
            }
        }
        return lst;
    }

}
