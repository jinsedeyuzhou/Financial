package com.financial.android.base;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.utils.AppManager;
import com.financial.android.utils.DialogUtil;
import com.financial.android.utils.LogUtil;
import com.financial.android.utils.NetUtil;
import com.financial.android.view.CustomProgressDialog;
import com.financial.android.view.CustomToast;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.umeng.analytics.MobclickAgent;

/**
 * @author wyy
 * 
 */
public abstract class BaseActivity extends FragmentActivity implements
		OnClickListener {
	private static final String TAG = "BaseActivity";
	private static final int ACTIVITY_RESUME = 0;
	private static final int ACTIVITY_STOP = 1;
	private static final int ACTIVITY_PAUSE = 2;
	private static final int ACTIVITY_DESTROY = 3;
	public int activityState;

	// 是否允许全屏
	private boolean mAllowFullScreen = true;
	// 是否激活——从后台唤醒 有关九宫格锁屏
	private boolean isActive;
	private boolean stopStatus;
	// 是否跳转
	private boolean nojump;
	private boolean resumeStatus;

	protected Context ct;
	protected FXApplication app;

	
	// 加载的view，不一定用
	protected View loadingView;
	// 加载失败的view,不一定用
	protected LinearLayout loadfailView;
	private RelativeLayout bar_rl_visible;
	private TextView titleTv;


	public void setAllowFullScreen(boolean allowFullScreen) {
		this.mAllowFullScreen = allowFullScreen;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtil.i(TAG, "onCreate");
		// 竖屏锁定
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// 软件盘模式
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (mAllowFullScreen) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			if (mAllowFullScreen) {
				// 取消标题
				requestWindowFeature(Window.FEATURE_NO_TITLE);
			}

		}
		AppManager.getAppManager().addActivity(this);
		// 初始化
		ct = this;
		// 初始化
		app = (FXApplication) getApplication();
		// 未知
		nojump = false;
		initView();
		initData();
	}
	
	/**
	 * 设置跳转
	 */
	public void setnojump() {
		nojump = true;
	}

	/**
	 * 设置title标题
	 */
	protected void initTitleBar(String title,int visible1,int visible2) {
		titleTv = (TextView) findViewById(R.id.bar_tv_title);

	}

	/**
	 * 设置Titlebar的可见性
	 * 
	 * @param visible
	 */
	protected void setTitleBarVisible(int visible) {
		bar_rl_visible = (RelativeLayout) findViewById(R.id.bar_rl_visible);
		bar_rl_visible.setVisibility(visible);
	}

	/**
	 * 判断是否锁屏
	 */

	public boolean isScreenLocked() {

		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		boolean isScreenOn = pm.isScreenOn();

		if (isScreenOn)
			return false;
		else
			return true;
	}

	/**
	 * 程序是否在前台运行
	 * 
	 * @return
	 */
	public boolean isAppOnForeground() {
		// Returns a list of application processes that are running on the
		// device

		ActivityManager activityManager = (ActivityManager) getApplicationContext()
				.getSystemService(Context.ACTIVITY_SERVICE);
		String packageName = getApplicationContext().getPackageName();

		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		if (appProcesses == null)
			return false;

		for (RunningAppProcessInfo appProcess : appProcesses) {
			// The name of the process that this object is associated with.
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}

		return false;
	}

	public abstract void initView();

	public abstract void initData();

	public abstract void processClick(View v);

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case 0:

			break;

		default:
			break;
		}
		processClick(v);
	}

	protected void loadData(HttpRequest.HttpMethod method, String url,
			RequestParams params, RequestCallBack<String> callback) {

		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(0);

		LogUtil.LOG_LEVEL = 6;
		if (params != null) {
			if (params.getQueryStringParams() != null)
				LogUtil.d(TAG, url + "?"
						+ params.getQueryStringParams().toString());
		} else {
			params = new RequestParams();

		}
		params.addHeader("x-deviceid", app.getDeviceId());
//		 params.addHeader("x-channel", app.getChannel()); 渠道推送时有用

		if (!NetUtil.isConnectionAvailable(ct)) {
			showToast("加载失败，请检查网络！");
			callback.onFailure(new HttpException(), "无网络");
		} else {
			LogUtil.d(TAG, url);
			http.send(method, url, params, callback);
		}
	}

	/**
	 * 自定义Toast
	 * @param msg
	 */
	protected void showToast(String msg) {
		showToast(msg, 0);
	}

	protected void showToast(String msg, int time) {
		CustomToast customToast = new CustomToast(ct, msg, time);
		customToast.show();
	}

	/**
	 * 自定义进度条
	 */
	protected CustomProgressDialog dialog;


	/**
	 * 展示进度条
	 * 
	 * @param content
	 */
	protected void showProgressDialog(String content) {
		if (dialog == null && ct != null) {
			dialog = (CustomProgressDialog) DialogUtil.createProgressDialog(ct,
					content);
		}
		dialog.show();
	}

	/**
	 * 关闭进度条
	 */
	protected void closeProgressDialog() {
		if (dialog != null)
			dialog.dismiss();
	}

	/**
	 * 加载成功，展示
	 */
	public void showLoadingView() {
		if (loadingView != null)
			loadingView.setVisibility(View.VISIBLE);
	}

	/**
	 * 加载成功，完成消失展示
	 */
	public void dismissLoadingView() {
		if (loadingView != null)
			loadingView.setVisibility(View.INVISIBLE);
	}

	/**
	 * 加载失败，展示
	 */
	public void showLoadFailView() {
		if (loadingView != null) {
			loadingView.setVisibility(View.VISIBLE);
			loadfailView.setVisibility(View.VISIBLE);
		}

	}

	/**
	 * 加载失败，完成消失展示
	 */
	public void dismissLoadFailView() {
		if (loadingView != null)
			loadfailView.setVisibility(View.INVISIBLE);
	}

	/**
	 * 打印activity生命周期方法
	 */
	protected void onStart() {
		super.onStart();
		activityState = ACTIVITY_STOP;
		LogUtil.i(TAG, "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		activityState = ACTIVITY_RESUME;
		LogUtil.i(TAG, "onResume");
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		activityState = ACTIVITY_PAUSE;
		LogUtil.i(TAG, "onPause");
		MobclickAgent.onPause(this);
	}
	@Override
	protected void onStop() {
		super.onStop();
		LogUtil.i(TAG, "onStop");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		LogUtil.i(TAG, "onRestart");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		activityState = ACTIVITY_DESTROY;
		LogUtil.i(TAG, "onDestroy");
		AppManager.getAppManager().finishActivity(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

}
