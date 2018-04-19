package middlem.person.utilsmodule.comutils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 判断屏幕分辨率大小
 * 作者：郑华彬
 * 日期：2014-10-11
 */
public class DisplayMetricsUtil {

	private static Context context;
	private static int screenHeight;
	private static int screenWidth;
	private static float scale;

	public static void init(Context context) {
		if (DisplayMetricsUtil.context == null) {
			DisplayMetricsUtil.context = context;
			DisplayMetrics dm = new DisplayMetrics();
			((Activity) context).getWindowManager().getDefaultDisplay()
					.getMetrics(dm);
			screenHeight = dm.heightPixels;
			screenWidth = dm.widthPixels;
			scale = context.getResources().getDisplayMetrics().density;
		}
	}

	public static int getScreenHeight() {
		return screenHeight;
	}
	
	public static int getDipScreenHeight() {
		return px2dip(screenHeight);
	}

	public static void setScreenHeight(int screenHeight) {
		DisplayMetricsUtil.screenHeight = screenHeight;
	}
	public static int getDipScreenWidth() {
		return px2dip(screenWidth);
	}

	public static int getScreenWidth() {
		return screenWidth;
	}

	public static void setScreenWidth(int screenWidth) {
		DisplayMetricsUtil.screenWidth = screenWidth;
	}

	/**
	 * 将dip转换为px
	 * @return
	 */
	public static int dip2px(float dipValue) {
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px转换为dip
	 * @return
	 */
	public static int px2dip(float pxValue) {
		return (int) (pxValue / scale + 0.5f);
	}
}
