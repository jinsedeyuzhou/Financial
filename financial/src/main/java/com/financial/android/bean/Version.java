package com.financial.android.bean;

import java.io.Serializable;

public class Version implements Serializable {
	private static final long serialVersionUID = 1L;
	// 应用程序名称
	private String appName;
	// apk名称
	private String apkName;
	// 版本号
	private int versionCode;
	// 版本名称
	private String versionName;
	// 下载地址
	private String downloadURL;
	// 显示信息
	private String displayMessage;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Version() {

	}

	public Version(String appName, String apkName, int versionCode,
			String versionName, String downloadURL, String displayMessage) {
		this.appName = appName;
		this.apkName = apkName;
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.downloadURL = downloadURL;
		this.displayMessage = displayMessage;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getApkName() {
		return apkName;
	}

	public void setApkName(String apkName) {
		this.apkName = apkName;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getDownloadURL() {
		return downloadURL;
	}

	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	public String getDisplayMessage() {
		return displayMessage;
	}

	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}

}
