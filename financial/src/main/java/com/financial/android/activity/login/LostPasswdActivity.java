package com.financial.android.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.base.BaseActivity;

/**
 * Created by wyy on 2016/1/18.
 */

public class LostPasswdActivity extends BaseActivity {

    private static final String TAG = "LostPasswdActivity";
    private RelativeLayout bar_rl_left;
    private TextView bar_tv_title;


    @Override
    public void initView() {
        setContentView(R.layout.activity_passwd_lost);
        initTitleBar();
    }


    /**
     * 设置TitleBar
     */
    private void initTitleBar() {
        bar_tv_title = (TextView) findViewById(R.id.bar_tv_title);
        bar_rl_left = (RelativeLayout) findViewById(R.id.bar_rl_left);
        bar_rl_left.setOnClickListener(this);
        bar_rl_left.setVisibility(View.VISIBLE);
        bar_tv_title.setText("忘记密码");

    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {
        Intent intent=null;
        switch (v.getId()) {
            case R.id.bar_rl_left:
                LostPasswdActivity.this.finish();
                break;
            default:
                break;
        }
    }
}
