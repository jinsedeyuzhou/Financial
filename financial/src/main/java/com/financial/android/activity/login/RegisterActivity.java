package com.financial.android.activity.login;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.base.BaseActivity;
import com.financial.android.utils.TimeCount;


public class RegisterActivity extends BaseActivity {

    private static final String TAG = "RegisterActivity";
    private RelativeLayout bar_rl_left;
    private TextView bar_tv_title;
    private TextView tv_register_smscode;
    private TimeCount timeCount;

    @Override
    public void initView() {
        setContentView(R.layout.activity_register);
        initTitleBar();
        tv_register_smscode = (TextView) findViewById(R.id.tv_register_smscode);

        tv_register_smscode.setOnClickListener(this);

    }
    /**
     * 设置TitleBar
     */
    private void initTitleBar() {
        bar_tv_title = (TextView) findViewById(R.id.bar_tv_title);
        bar_rl_left = (RelativeLayout) findViewById(R.id.bar_rl_left);
        bar_rl_left.setOnClickListener(this);
        bar_rl_left.setVisibility(View.VISIBLE);
        bar_tv_title.setText("注册用户");

    }
    @Override
    public void initData() {
        timeCount=new TimeCount(120000, 1000, tv_register_smscode);

    }

    @Override
    public void processClick(View v) {
        Intent intent=null;
        switch (v.getId()) {
            case R.id.tv_register_smscode:
                timeCount.start();
                break;
            case R.id.btn_sign_out:

                break;
            case R.id.bar_rl_left:
                RegisterActivity.this.finish();
            default:

                break;

        }

    }


}
