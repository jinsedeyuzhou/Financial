package com.financial.android.activity.welcome;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.view.View.OnClickListener;
import com.financial.android.R;
import com.financial.android.activity.login.LoginActivity;
import com.financial.android.base.FXApplication;
import com.financial.android.utils.LockPatternUtils;
import com.financial.android.view.CustomToast;
import com.financial.android.view.LockPatternView;

import java.util.List;

public class LockActivity extends Activity implements OnClickListener{
	private LockPatternView mLockPatternView;
	private int mFailedPatternAttemptsSinceLastTimeout = 0;
	private CountDownTimer mCountdownTimer = null;
	private Handler mHandler = new Handler();
	private TextView mHeadTextView;
	private Animation mShakeAnim;

	private TextView gesturepwd_unlock_forget;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lock);
		initView();
		initData();

	}
	public void initView() {

		mLockPatternView = (LockPatternView) this
				.findViewById(R.id.gesturepwd_unlock_lockview);
		mLockPatternView.setOnPatternListener(mChooseNewLockPatternListener);
		mLockPatternView.setTactileFeedbackEnabled(true);
		mHeadTextView = (TextView) findViewById(R.id.gesturepwd_unlock_text);
		mShakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_x);

		gesturepwd_unlock_forget = (TextView) findViewById(R.id.gesturepwd_unlock_forget);
		gesturepwd_unlock_forget.setOnClickListener(this);
	}

	public void initData() {

	}





	@Override
	protected void onResume() {
		super.onResume();

//		if (!App.getInstance().getLockPatternUtils().savedPatternExists()) {
//			startActivity(new Intent(this, GuideGesturePasswordActivity.class));
//			finish();
//		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mCountdownTimer != null)
			mCountdownTimer.cancel();
	}
	private Runnable mClearPatternRunnable = new Runnable() {
		public void run() {
			mLockPatternView.clearPattern();
		}
	};

	protected LockPatternView.OnPatternListener mChooseNewLockPatternListener = new LockPatternView.OnPatternListener() {

		public void onPatternStart() {
			mLockPatternView.removeCallbacks(mClearPatternRunnable);
			patternInProgress();
		}

		public void onPatternCleared() {
			mLockPatternView.removeCallbacks(mClearPatternRunnable);
		}

		public void onPatternDetected(List<LockPatternView.Cell> pattern) {
			if (pattern == null)
				return;
			if (FXApplication.getApp().getLockPatternUtils().checkPattern(pattern)) {
				mLockPatternView
						.setDisplayMode(LockPatternView.DisplayMode.Correct);
//				Intent intent = new Intent(UnlockGesturePasswordActivity.this,
//						GuideGesturePasswordActivity.class);
//				// 打开新的Activity
//				startActivity(intent);
//				showToast("解锁成功");
				finish();
			} else {
				mLockPatternView
						.setDisplayMode(LockPatternView.DisplayMode.Wrong);
				if (pattern.size() >= LockPatternUtils.MIN_PATTERN_REGISTER_FAIL) {
					mFailedPatternAttemptsSinceLastTimeout++;
					int retry = LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT
							- mFailedPatternAttemptsSinceLastTimeout;
					if (retry >= 0) {
						if (retry == 0)
							showToast("您已5次输错密码，请30秒后再试");
						mHeadTextView.setText("密码错误，还可以再输入" + retry + "次");
						mHeadTextView.setTextColor(Color.RED);
						mHeadTextView.startAnimation(mShakeAnim);
					}

				}else{
					showToast("输入长度不够，请重试");
				}

				if (mFailedPatternAttemptsSinceLastTimeout >= LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT) {
					mHandler.postDelayed(attemptLockout, 2000);
				} else {
					mLockPatternView.postDelayed(mClearPatternRunnable, 2000);
				}
			}
		}

		public void onPatternCellAdded(List<LockPatternView.Cell> pattern) {

		}

		private void patternInProgress() {
		}
	};

	Runnable attemptLockout = new Runnable() {

		@Override
		public void run() {
			mLockPatternView.clearPattern();
			mLockPatternView.setEnabled(false);
			mCountdownTimer = new CountDownTimer(
					LockPatternUtils.FAILED_ATTEMPT_TIMEOUT_MS + 1, 1000) {

				@Override
				public void onTick(long millisUntilFinished) {
					int secondsRemaining = (int) (millisUntilFinished / 1000) - 1;
					if (secondsRemaining > 0) {
						mHeadTextView.setText(secondsRemaining + " 秒后重试");
					} else {
						mHeadTextView.setText("请绘制手势密码");
						mHeadTextView.setTextColor(Color.WHITE);
					}

				}

				@Override
				public void onFinish() {
					mLockPatternView.setEnabled(true);
					mFailedPatternAttemptsSinceLastTimeout = 0;
				}
			}.start();
		}
	};

	/**
	 * 自定义Toast
	 * @param msg
	 */
	protected void showToast(String msg) {
		showToast(msg, 0);
	}

	protected void showToast(String msg, int time) {
		CustomToast customToast = new CustomToast(this, msg, time);
		customToast.show();
	}

	@Override
	public void onClick(View v) {
		Intent intent=null;
		switch (v.getId())
		{
			case R.id.gesturepwd_unlock_forget:
				mLockPatternView.removeCallbacks(mClearPatternRunnable);
				FXApplication.getApp().getLockPatternUtils().clearLock();
				intent=new Intent(this, LoginActivity.class);
				startActivity(intent);
				finish();
				break;
		}

	}

	@Override
	public void onBackPressed() {
//		super.onBackPressed();
	}
}
