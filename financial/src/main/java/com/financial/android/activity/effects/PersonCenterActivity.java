package com.financial.android.activity.effects;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.financial.android.R;
import com.financial.android.base.BaseActivity;
import com.financial.android.utils.StatusBarUtils;

/**
 * Created by Administrator on 2017/11/8.
 */

public class PersonCenterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_center);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //状态栏透明和间距处理
        StatusBarUtils.immersive(this);
        StatusBarUtils.setPaddingSmart(this, toolbar);
        StatusBarUtils.setPaddingSmart(this, findViewById(R.id.profile));
        StatusBarUtils.setPaddingSmart(this, findViewById(R.id.blurview));

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {

    }
}
