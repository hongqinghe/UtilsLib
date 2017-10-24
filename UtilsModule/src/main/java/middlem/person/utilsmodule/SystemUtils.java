package middlem.person.utilsmodule;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

/***********************************************
 * <P> System工具类
 * <P> Author: MaBao
 * <P> Date: 2017-04-14 14:59
 * <P> Copyright © 2008 二维火科技
 ***********************************************/
public class SystemUtils {

    /**
     * 获取软件版本号
     * <p>对应AndroidManifest下versionCode内容</p>
     *
     * @param context
     * @return int
     */
    public static int getVersionCode(Context context) {
        int versionCode = -1;
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionCode = pi.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取软件版本名称
     * <p>对应AndroidManifest下versionName内容</p>
     *
     * @param context
     * @return String
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取手机唯一标识符
     *
     * @param context
     * @return String
     */
    @SuppressLint("MissingPermission")
    public static String getDeviceToken(Context context) {
        String specToken = "9774d56d682e549c";
        String device_id = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        if (TextUtils.isEmpty(device_id) || device_id.equals(specToken)) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            device_id = tm.getDeviceId();
        } else {
            return device_id;
        }

        if (TextUtils.isEmpty(device_id) || device_id.equals(specToken)) {
            SharedPreferences token = context.getSharedPreferences("token", 0);
            SharedPreferences.Editor editor = token.edit();
            device_id = token.getString("token", "");
            if (TextUtils.isEmpty(device_id)) {
                UUID uuid = UUID.randomUUID();
                String uniqueId = uuid.toString();
                device_id = uniqueId.replace("-", "");
                editor.putString("token", "");
                editor.commit();
            }
        }

        return device_id;
    }

    /**
     * 用来判断服务是否运行.         
     *
     * @param context          
     * @param className
     * @return true 在运行 false 不在运行         
     */
    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(1000);
        if (serviceList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * 获取语言环境
     *
     * @param context
     * @return String: zh_cn(中文简体),zh_tw(中文繁体),en_us(美式英文),en_uk(英式英文)...
     */
    public static String getDeviceLanguage(Context context) {
        String language = "zh_cn";
        Locale locale = context.getResources().getConfiguration().locale;
        if (locale.getCountry().equals("TW")) {
            language = "zh_tw";
        } else if (locale.getCountry().equals("US")) {
            language = "en_us";
        } else if (locale.getCountry().equals("UK")) {
            language = "en_uk";
        }
        return language;
    }

    /**
     * 判断某个进程是否存在
     * Note：
     * 1. android4.4及以下版本返回系统运行的进程（所有app）的信息;
     * 2. android5.0及以上版本返回本app的进程的信息.
     *
     * @param context            
     * @param processName
     * @return true 存在 false 不存在  
     */
    public static boolean isProcessExist(Context context, String processName) {
        if (context == null)
            throw new NullPointerException("context can`t be null");

        if (StringUtils.isStrEmpty(processName))
            throw new NullPointerException("processName can`t be null");

        boolean isExist = false;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processList = activityManager.getRunningAppProcesses();
        if (processList == null || processList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < processList.size(); i++) {
            if (processList.get(i).processName.equals(processName)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     * @param context
     * @return int
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕密度
     * @param context
     * @return float
     */
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 获取系统StatusBar的高度
     * @param context
     * @return int
     */
    public static int getSystemStateBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
