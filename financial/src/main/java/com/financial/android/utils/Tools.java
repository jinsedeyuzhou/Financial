package com.financial.android.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import com.financial.android.R;


/**
 * @author wyy
 *
 */
public class Tools {
	/**
	 * 获取版本号
	 * @param ct
	 * @return
	 */
	public static int getVersionCode(Context ct) {
		PackageManager pm = ct.getPackageManager();
		try {
			return pm.getPackageInfo(ct.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取版本名称
	 * 
	 * @return
	 */
	public static String getVersionName(Context ct) {
		PackageManager pm = ct.getPackageManager();
		try {
			PackageInfo info = pm.getPackageInfo(ct.getPackageName(), 0);
			String versionName = info.versionName;
			return versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取应用名称
	 */
	public static  String getAppName(Context ct) {
		String verName = ct.getResources().getText(R.string.app_name)
				.toString();
		return verName;
	}
	
	
	/**
	 * 获得当前app版本号
	 * 
	 * @Title: getAppVersionName
	 * @Description: TODO
	 * @param @param context
	 * @param @return
	 * @return String
	 * @throws
	 * @author Administrator
	 * @since JDK 1.8
	 */
	public static String getAppVersionName(Context context) {
		String versionName = "";
		try {
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			if (versionName == null || versionName.length() <= 0) {
				return "1.0.0";
			}
		} catch (Exception e) {
			Log.e("VersionInfo", "Exception", e);
		}
		return versionName;
	}

	/**
	 * 获取版本序列号
	 * 
	 * @param context
	 * @return int
	 */
	public static int getAppVersion(Context context) {
		int version = 0;
		try {
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			version = pi.versionCode;
			if (version <= 0) {
				return 1;
			}
		} catch (Exception e) {
			Log.e("VersionInfo", "Exception", e);
		}
		return version;
	}

	/**
	 * String转int
	 * 
	 * @Title: convertInteger
	 * @Description: TODO
	 * @param @param strs
	 * @param @return
	 * @return int
	 * @throws
	 * @author Administrator
	 * @since JDK 1.8
	 */
	public static int convertInteger(String strs) {
		return Integer.parseInt(strs);
	}

	/**
	 * int转String
	 * 
	 * @Title: convertString
	 * @Description: TODO
	 * @param @param ints
	 * @param @return
	 * @return String
	 * @throws
	 * @author Administrator
	 * @since JDK 1.8
	 */
	public static String convertString(int ints) {
		return Integer.toString(ints);
	}
}
