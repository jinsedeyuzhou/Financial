package com.financial.android.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

/**
 * private TimeCount time;
 * time = new TimeCount(60000, 1000, mBtnCode);
 * time.start();
 * @author wyy
 *
 */
public class TimeCount extends CountDownTimer {

	private TextView tv;
	public TimeCount(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
	}
	public TimeCount(long millisInFuture, long countDownInterval,TextView tv) {
		super(millisInFuture, countDownInterval);
		this.tv=tv;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		tv.setBackgroundColor(Color.parseColor("#B6B6D8"));
		tv.setClickable(false);
		tv.setText(millisUntilFinished / 1000 + "秒");

	}

	@Override
	public void onFinish() {
		tv.setText("重新获取验证码");
		tv.setClickable(true);
		tv.setBackgroundColor(Color.parseColor("#4EB84A"));
	}

}
