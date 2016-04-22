package com.financial.android.activity.test;

import android.support.v4.app.Fragment;
import android.view.View;

import com.financial.android.R;
import com.financial.android.activity.projects.DescriptionFragment;
import com.financial.android.activity.projects.DetailsFragment;
import com.financial.android.base.BaseActivity;
import com.financial.android.base.FXFragmentPagerAdapter;
import com.financial.android.view.VerticalViewPager;

import java.util.ArrayList;

/**
 * Created by wyy on 2016/1/29.
 * VerticalViewPager
 */
public class VerticalViewPagerActivity extends BaseActivity {
    private ArrayList<Fragment> fragments;
    private FXFragmentPagerAdapter adapter;
    @Override
    public void initView() {
        setContentView(R.layout.activity_bank);
        VerticalViewPager vertical = (VerticalViewPager) findViewById(R.id.vertical);
        fragments=new ArrayList<Fragment>();
        DescriptionFragment df=new DescriptionFragment();
        fragments.add(df);
        DetailsFragment df1=new DetailsFragment();
        fragments.add(df1);
        adapter = new FXFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        vertical.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {

    }
}
