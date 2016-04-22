package com.financial.android.activity.test;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.financial.android.R;
import com.financial.android.adapter.VerticalAdapter;
import com.financial.android.base.BaseActivity;
import com.financial.android.widget.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyy on 2016/1/29.
 * 显示衣服 VerticalViewPager widget
 */
public class VerticalViewActivity extends BaseActivity {

    private ViewPager vertical_viewpager;
    private VerticalAdapter vAdapter1;
    private VerticalAdapter vAdapter2;
    private VerticalViewPager verticalPager1;
    private VerticalViewPager verticalPager2;
    private List<View> pageViews;
    private List<View> verticalViews1;
    private List<View> verticalViews2;

   private VerticalPagerAdapter baseAdapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_vertical);
        LayoutInflater inflater = getLayoutInflater();
        pageViews = new ArrayList<View>();
        verticalViews1 = new ArrayList<View>();
        verticalViews2 = new ArrayList<View>();

        verticalViews1.add(inflater.inflate(R.layout.item01, null));
        verticalViews1.add(inflater.inflate(R.layout.item02, null));
        verticalViews1.add(inflater.inflate(R.layout.item03, null));
        verticalViews2.add(inflater.inflate(R.layout.item04, null));
        verticalViews2.add(inflater.inflate(R.layout.item05, null));
        verticalViews2.add(inflater.inflate(R.layout.item06, null));

        View view1 = inflater.inflate(R.layout.vertical_pager1, null);
        View view2 = inflater.inflate(R.layout.vertical_pager2, null);
        pageViews.add(view1);
        pageViews.add(view2);

        verticalPager1 = (VerticalViewPager) view1.findViewById(R.id.pager1);
        verticalPager2 = (VerticalViewPager) view2.findViewById(R.id.pager2);
        vertical_viewpager = (ViewPager) findViewById(R.id.vertical_viewpager);

        vAdapter1 = new VerticalAdapter(verticalViews1);
        vAdapter2 = new VerticalAdapter(verticalViews2);

        verticalPager1.setAdapter(vAdapter1);
        verticalPager2.setAdapter(vAdapter2);
        baseAdapter = new VerticalPagerAdapter(pageViews);

        vertical_viewpager.setAdapter(baseAdapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {

    }
}
