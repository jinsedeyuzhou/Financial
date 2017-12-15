package com.financial.android.activity.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.financial.android.R;
import com.financial.android.activity.main.HomeActivity;
import com.financial.android.base.BaseActivity;
import com.financial.android.utils.SharePrefUtil;
import com.financial.android.utils.StatusBarUtils;

public class WelcomeActivity extends BaseActivity {

	private boolean firstLaunch; // 第一次进应用
	private final static int UPDATE_DIALOG = 1;// 消息类型-弹出升级对话框
	private final static int NETWORK_ERROR = 2;// 消息类型-网络异常
	private final static int JSON_ERROR = 3;// 消息类型-JSON解析失败
	private final static int URL_ERROR = 4;// 消息类型-网络链接异常
	private final static int ENTER_HOME = 5;// 消息类型-跳到主页面

	 private ImageView mSplash;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case ENTER_HOME:
				enterHome();
				break;

			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		StatusBarUtils.immersive(this);
	}

	@Override
	public void initView() {

		// 渐入动画
//		mSplash = (ImageView) findViewById(R.id.iv_splash);
//		AlphaAnimation anim = new AlphaAnimation(0, 1);
//		anim.setDuration(2000);// 动画持续时间
//		mSplash.startAnimation(anim);
	}

	@Override
	public void initData() {
		firstLaunch = SharePrefUtil.getBoolean(ct,
				SharePrefUtil.KEY.FIRST_LAUNCH, true);
		boolean autoCheck = SharePrefUtil.getBoolean(ct,
				SharePrefUtil.KEY.AUTO_UPDATE, false);
		if (autoCheck) {
			checkVersion();
		} else {
			handler.sendEmptyMessageDelayed(ENTER_HOME, 2000);
		}
	}

	@Override
	protected void bindEvent() {

	}

	@Override
	public void processClick(View v) {

	}

	/**
	 * 检查版本更新
	 */
	private void checkVersion() {

	}

	// /**
	// * 获取AccessToken
	// */
	// private void remoteGetAccessToken() {
	//
	// }

	private void enterHome() {
		Intent intent = null;
		// 判断是否显示引导图
		if (firstLaunch == true) {
			intent = new Intent(ct, GuideViewActivity.class);
			startActivity(intent);
		} else {
			// 跳转到首页

			intent = new Intent(ct, HomeActivity.class);
			startActivity(intent);
			boolean gesturePasswordSwitch = SharePrefUtil.getBoolean(ct,
					SharePrefUtil.KEY.SWITCH_GESTURE, false);
			if (gesturePasswordSwitch == true) {
				intent = new Intent(ct, LockActivity.class);
				startActivity(intent);
			}
		}
		WelcomeActivity.this.finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		enterHome();
	}

}
