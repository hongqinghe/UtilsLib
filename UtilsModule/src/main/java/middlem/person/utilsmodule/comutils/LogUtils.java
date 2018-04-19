package middlem.person.utilsmodule.comutils;

import java.util.HashMap;

/***********************************************
 *
 * <P> desc:    Log打印基础类
 * <p> 强制使用，方便日志管理
 * <P> Author: gongtong
 * <P> Date: 2017-10-24 21:31
 ***********************************************/

public class LogUtils {
	private static String DEFAULT_TAG = "LogUtils";
	private static boolean isLogEnable = false;
	private static BaseLog baseLog = null;

	public static void setLogEnable(boolean enable) {
		isLogEnable = enable;
		baseLog = new BaseLog();
	}

	public static void d(String msg){
		if (isLogEnable) {
			d(DEFAULT_TAG, msg);
		}
	}

	public static void d (String tag, String msg){
		if (isLogEnable) {
			baseLog.printDefault(BaseLog.D, tag, msg);
		}
	}

	public static void e(String msg){
		if (isLogEnable) {
			e(DEFAULT_TAG, msg);
		}
	}

	public static void e(Exception e) {
		if (isLogEnable) {
			e(DEFAULT_TAG, e.toString());
		}
	}

	public static void e(String tag, Exception e) {
		if (isLogEnable) {
			e(tag, e.toString());
		}
	}

	public static void e (String tag, String msg){
		if (isLogEnable) {
			baseLog.printDefault(BaseLog.E, tag, msg);
		}
	}

	public static void i(String msg){
		if (isLogEnable) {
			i(DEFAULT_TAG, msg);
		}
	}

	public static void i (String tag, String msg){
		if (isLogEnable) {
			baseLog.printDefault(BaseLog.I, tag, msg);
		}
	}

	public static void v(String msg){
		if (isLogEnable) {
			v(DEFAULT_TAG, msg);
		}
	}

	public static void v (String tag, String msg){
		if (isLogEnable) {
			baseLog.printDefault(BaseLog.V, tag, msg);
		}
	}

	public static void w(String msg){
		if (isLogEnable) {
			w(DEFAULT_TAG, msg);
		}
	}

	public static void w (String tag, String msg){
		if (isLogEnable) {
			baseLog.printDefault(BaseLog.W, tag, msg);
		}
	}

	public static void wtf(String msg){
		if (isLogEnable) {
			wtf(DEFAULT_TAG, msg);
		}
	}

	public static void wtf (String tag, String msg){
		if (isLogEnable) {
			baseLog.printDefault(BaseLog.WTF, tag, msg);
		}
	}

	public static void json(String msg, String[] headers) {
		if (isLogEnable) {
			json(DEFAULT_TAG, msg, headers);
		}
	}

	public static void json(String tag, String msg, String[] headers) {
		if (isLogEnable) {
			baseLog.printJson(tag, msg, headers);
		}
	}

	public static void params(HashMap<String, String> params, String... headers) {
		if (isLogEnable) {
			params(DEFAULT_TAG, params, headers);
		}
	}

	public static void params(String tag, HashMap<String, String> params, String... headers) {
		if (isLogEnable) {
			baseLog.printParams(tag, params, headers);
		}
	}
}
