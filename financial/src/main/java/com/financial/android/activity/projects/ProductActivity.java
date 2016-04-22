package com.financial.android.activity.projects;

import android.support.v4.app.Fragment;
import android.view.View;


import com.financial.android.R;
import com.financial.android.base.BaseActivity;
import com.financial.android.base.FXFragmentPagerAdapter;
import com.financial.android.view.DirectionalViewPager;

import java.util.ArrayList;

/**
 * Created by wyy on 2016/1/27.
 * DirectionalViewPager view
 *
 */
public class ProductActivity extends BaseActivity {

    private DirectionalViewPager directional_viewpager;
    private ArrayList<Fragment> fragments;
    private FXFragmentPagerAdapter adapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_product);
        directional_viewpager = (DirectionalViewPager) findViewById(R.id.directional_viewpager);
        directional_viewpager.setOrientation(DirectionalViewPager.HORIZONTAL);
        fragments=new ArrayList<Fragment>();
        DescriptionFragment df=new DescriptionFragment();
        fragments.add(df);
        DetailsFragment df1=new DetailsFragment();
        fragments.add(df1);
        adapter = new FXFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        directional_viewpager.setAdapter(adapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {

    }

}
