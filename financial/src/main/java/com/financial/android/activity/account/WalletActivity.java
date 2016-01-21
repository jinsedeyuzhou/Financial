package com.financial.android.activity.account;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.base.BaseActivity;

/**
 * Created by wyy on 2016/1/20.
 */
public class WalletActivity extends BaseActivity {

    private RelativeLayout bar_rl_left;
    private TextView bar_tv_title;

    @Override
    public void initView() {
        setContentView(R.layout.activity_wallet);
        initTitleBar();
    }
    /**
     * 设置标题
     */
    private void initTitleBar() {
        bar_rl_left = (RelativeLayout) findViewById(R.id.bar_rl_left);
        bar_rl_left.setVisibility(View.VISIBLE);
        bar_rl_left.setOnClickListener(this);
        bar_tv_title = (TextView) findViewById(R.id.bar_tv_title);
        bar_tv_title.setText("钱包");
    }
    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {

    }
}
