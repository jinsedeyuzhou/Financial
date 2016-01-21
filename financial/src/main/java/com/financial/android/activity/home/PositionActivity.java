package com.financial.android.activity.home;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.base.BaseActivity;

public class PositionActivity extends BaseActivity {


    @Override
    public void initView() {
        setContentView(R.layout.activity_position);
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
        bar_tv_title.setText("城市选择");
    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bar_rl_left:
                PositionActivity.this.finish();
                break;

            default:
                break;
        }
    }

}
