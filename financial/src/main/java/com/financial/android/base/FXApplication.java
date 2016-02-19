package com.financial.android.base;

import com.financial.android.bean.AccessToken;
import com.financial.android.bean.UserInfo;
import com.financial.android.utils.SharePrefUtil;
import com.financial.android.widget.LockPatternUtils;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

public class FXApplication extends Application {
	// 全局变量
	private static FXApplication application;
	// 宽高
	private int width;
	private int height;
	// 版本号
	private String version;
	// 设备ID
	private String deviceId;
	private String system;

	private LockPatternUtils mLockPatternUtils;

	private AccessToken accessToken;
	private UserInfo userInfo;
	
	/**
	 * token是否失效，或者是否可用 判断
	 * 
	 * @return
	 */
	public boolean accessTokenAvailable() {
		long currentTimeMs = System.currentTimeMillis();
		long currentTimeS = currentTimeMs / 1000;

		if (accessToken != null && accessToken.getExpires_in() > currentTimeS) {
			return true;
		}
		return false;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		application = this;
		mLockPatternUtils = new LockPatternUtils(this);

		// 获取系统id
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		deviceId = tm.getDeviceId();

		PackageManager pm = getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo;
		try {
			packInfo = pm.getPackageInfo(getPackageName(), 0);
			version = packInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		system = android.os.Build.MODEL + ","
				+ android.os.Build.VERSION.SDK_INT + ","
				+ android.os.Build.VERSION.RELEASE;

		DisplayMetrics dm = getResources().getDisplayMetrics();
		width = (int) (dm.widthPixels * dm.density + 0.5f); // 屏幕宽（px，如：480px）
		height = (int) (dm.heightPixels * dm.density + 0.5f); // 屏幕高（px，如：800px）

		
		if (SharePrefUtil.getLong(getApplicationContext(), SharePrefUtil.KEY.LAST_UID, 0) > 0) {
//			setUserInfo((UserInfo) SharePrefUtil.getObj(getApplicationContext(), SharePrefUtil.KEY.USER_INFO));
		}
		boolean firstLaunch = SharePrefUtil.getBoolean(getApplicationContext(), SharePrefUtil.KEY.FIRST_LAUNCH, true);
		if (firstLaunch == true) {
			SharePrefUtil.saveBoolean(getApplicationContext(), SharePrefUtil.KEY.FIRST_LAUNCH, true);
		}
	
	}

	
	
	public static FXApplication getApp() {
		return application;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public AccessToken getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
//		if (userInfo != null && userInfo.getUid() > 0) {
//			SharePrefUtil.saveLong(getApplicationContext(),
//					SharePrefUtil.KEY.LAST_UID, userInfo.getUid());
//			SharePrefUtil.saveObj(getApplicationContext(),
//					SharePrefUtil.KEY.USER_INFO, userInfo);
//		}
	}
	public LockPatternUtils getLockPatternUtils() {
		return mLockPatternUtils;
	}
	
}
