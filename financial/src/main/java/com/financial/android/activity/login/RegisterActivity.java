package com.financial.android.activity.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.base.BaseActivity;
import com.financial.android.utils.KeyboardUtil;
import com.financial.android.utils.TimeCount;
import com.financial.android.view.ClearEditText;


public class RegisterActivity extends BaseActivity {

    private static final String TAG = "RegisterActivity";
    private RelativeLayout bar_rl_left;
    private TextView bar_tv_title;
    private TextView tv_register_smscode;
    private TimeCount timeCount;
    private ClearEditText et_username_register;
    private ClearEditText et_pass_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public void initView() {

        initTitleBar();
        et_username_register = (ClearEditText) findViewById(R.id.et_username_register);
        et_pass_register = (ClearEditText) findViewById(R.id.et_pass_register);
        et_pass_register.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                    InputMethodManager imm = (InputMethodManager) ct
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    // imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);隐藏系统软键盘
                    if (imm.isActive())  //一直是true
                        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                                InputMethodManager.HIDE_NOT_ALWAYS);
                int inputback = et_pass_register.getInputType();
                et_pass_register.setInputType(InputType.TYPE_NULL);
                new KeyboardUtil(RegisterActivity.this, ct, et_pass_register).showKeyboard();
                et_pass_register.setInputType(inputback);
                return false;
            }
        });

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
    protected void bindEvent() {

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
