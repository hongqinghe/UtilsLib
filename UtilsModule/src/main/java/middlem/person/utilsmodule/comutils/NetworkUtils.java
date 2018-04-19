package middlem.person.utilsmodule.comutils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/***********************************************
 * <P> 网络工具类
 * <P> Author: MaBao
 * <P> Date: 2017-04-17 17:20
 * <P> Copyright © 2008 二维火科技
 ***********************************************/
public class NetworkUtils {
	private static final String DEFAULT_NO_IP = "127.0.0.1";

	private static final String WIFI_NAME="wlan0";

	private NetworkUtils() {

	}

	/**
	 * 检测手机网络可不可用
	 *
	 * @param context
	 * @return boolean
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] wifi_infos = connectivity.getAllNetworkInfo();
			NetworkInfo net_info = connectivity.getActiveNetworkInfo();
			if (wifi_infos != null) {
				for (NetworkInfo ni : wifi_infos) {
					if (ni.getTypeName().equals("WIFI") && ni.isConnected()) {
						return true;
					}
				}
			}
			if (net_info != null) {
				if (net_info.isAvailable()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 检测手机Wifi网络可不可用
	 * <p>仅检测Wifi网络</p>
	 *
	 * @param context
	 * @return boolean
	 */
	public static boolean isWifiNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] wifi_infos = connectivity.getAllNetworkInfo();
			if (wifi_infos != null) {
				for (NetworkInfo ni : wifi_infos) {
					if (ni.getTypeName().equals("WIFI") && ni.isConnected()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 得到当前ip地址.
	 * @return
	 */
	public static String getLocalIpAddress() {
		String ipAddress= DEFAULT_NO_IP;
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()
							&&!inetAddress.isLinkLocalAddress()){
						ipAddress = inetAddress.getHostAddress().toString();
						if(intf.getName().equals(WIFI_NAME)){
							return ipAddress;
						}
					}
				}
			}
		} catch (SocketException ex) {
			LogUtils.e(ex);
		}
		return ipAddress;
	}

	public static String getMac(Context context) {
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		if (wifi != null) {
			String mac = info.getMacAddress();
			if (StringUtils.isEmpty(mac)) {
				mac = UUIDUtils.randomUUID().toString();
			}
			return mac;
		}else{
			return  UUIDUtils.randomUUID().toString();
		}
	}
}
