package com.financial.android.activity.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.financial.android.R;
import com.financial.android.base.FXApplication;

public class GuideGesturePasswordActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gesturepassword_guide);
		findViewById(R.id.gesturepwd_guide_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FXApplication.getApp().getLockPatternUtils().clearLock();
				Intent intent = new Intent(GuideGesturePasswordActivity.this,
						LockSetupActivity.class);
				
				// 打开新的Activity
				startActivity(intent);
				finish();
			}
		});
	}

}
