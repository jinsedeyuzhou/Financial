package com.financial.android.activity.account;


import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.base.BaseActivity;

/**
 * 设置界面->我的账户
 * 
 * @author wyy
 * 
 */
public class SettingActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
	}

	@Override
	public void initView() {

		initTitleBar();
	}

	/**
	 * 设置标题
	 */
	private void initTitleBar() {
		RelativeLayout bar_rl_left = (RelativeLayout) findViewById(R.id.bar_rl_left);
		bar_rl_left.setVisibility(View.VISIBLE);
		bar_rl_left.setOnClickListener(this);
		TextView bar_tv_title = (TextView) findViewById(R.id.bar_tv_title);
		bar_tv_title.setText("设置");
	}

	@Override
	public void initData() {

	}

	@Override
	protected void bindEvent() {

	}

	@Override
	public void processClick(View v) {
		switch (v.getId()) {
		case R.id.bar_rl_left:
			SettingActivity.this.finish();
			break;

		default:
			break;
		}
	}



}
