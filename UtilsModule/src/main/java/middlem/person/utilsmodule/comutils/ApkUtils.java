package middlem.person.utilsmodule.comutils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/***********************************************
 *
 * <P> desc:    Apk升级Utils
 * <P> Author: gongtong
 * <P> Date: 2017-10-24 21:21
 ***********************************************/
 

public class ApkUtils {

    public static int STATUS = -1;

    /** 下载前 */
    public static final int PREPARE = 0;

    /** 下载中 */
    public static final int WORKING = 1;

    /** 下载完成 */
    public static final int DONE = 2;

    /** 下载出错 */
    public static final int ERROR = 3;

    private ApkUtils() {

    }

    /**
     * 下载apk
     * @param apkUrl
     * @param savePath
     * @param callBack
     */
    public static void downloadApk(final String apkUrl, final String savePath, final CallBack callBack) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                int progress = 0;
                STATUS = PREPARE;
                try {
                    // 判断SD卡是否存在，并且是否具有读写权限
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        URL url = new URL(apkUrl);
                        // 创建连接
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.connect();
                        // 获取文件大小
                        int length = conn.getContentLength();
                        // 创建输入流
                        InputStream is = conn.getInputStream();

                        File file = new File(FileUtils.getApkFilePath());
                        // 判断文件目录是否存在
                        if (!file.exists()) {
                            file.mkdir();
                        }

                        File apkFile = new File(savePath);
                        //File apkFile = new File("/storage/sdcard0/" + version.getVersionCode());
                        FileOutputStream fos = new FileOutputStream(apkFile);
                        int count = 0;
                        // 缓存
                        byte buf[] = new byte[1024];
                        // 写入到文件中
                        do {
                            int numread = is.read(buf);
                            count += numread;
                            // 计算进度条位置
                            progress = (int) (((float) count / length) * 100);
                            // 更新进度
                            STATUS = WORKING;
                            if (null != callBack) {
                                callBack.onResult(false, STATUS, progress);
                            }
                            if (numread <= 0) {
                                // 下载完成
                                STATUS = DONE;
                                if (null != callBack) {
                                    callBack.onResult(true, STATUS, progress);
                                }
                                break;
                            }
                            // 写入文件
                            fos.write(buf, 0, numread);
                        } while (true);// 点击取消就停止下载.
                        fos.close();
                        is.close();
                    } else {
                        STATUS = ERROR;
                        if (null != callBack) {
                            callBack.onResult(false, STATUS, progress);
                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    STATUS = ERROR;
                    if (null != callBack) {
                        callBack.onResult(false, STATUS, progress);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    STATUS = ERROR;
                    if (null != callBack) {
                        callBack.onResult(false, STATUS, progress);
                    }
                }
            }
        }.start();
    }

    /**
     * 普通方式安装Apk
     * @param context
     * @param savePath
     * @return
     */
    public static boolean installApk(Context context, String savePath) {

        File file = new File(savePath);
        if(!file.exists()) return false;
        if (isApkCanInstall(context, savePath)) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            try {
                context.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException e) {// fix bug
                return false;
            }
        }
        return false;
    }

    public interface CallBack {
        void onResult(boolean flag, int status, int progress);
    }

    /**
     * 判断APK文件是否完整
     * @return
     */
    public static boolean isApkCanInstall(Context context, String savePath){
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(savePath, PackageManager.GET_ACTIVITIES);
        boolean result = info != null;
        return result;
    }

}
