package com.financial.android.activity.account;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.financial.android.R;
import com.financial.android.base.BaseActivity;
import com.financial.android.base.FXFragmentPagerAdapter;
import com.financial.android.view.SyncHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyy on 2016/1/20.
 */
public class WalletActivity extends BaseActivity {

    //TitleBar
    private RelativeLayout bar_rl_left;
    private TextView bar_tv_title;


    private RelativeLayout rl_nav;
    private SyncHorizontalScrollView mHsv;
    private RadioGroup rg_nav_content;
    private ImageView iv_nav_indicator;
    private ImageView iv_nav_left;
    private ImageView iv_nav_right;
    private ViewPager mViewPager;


    private ArrayList<Fragment> frags=null;
    private int indicatorWidth;
    public static String[] tabTitle = { "选项1", "选项2","选项3","选项四","选项五"};
    private int currentIndicatorLeft = 0;
//    private FXFragmentPagerAdapter mAdapter;
    private TabFragmentPagerAdapter mAdapter;
    private LayoutInflater mInflater;
    //屏幕宽度
    private int mScreenWidth = 0;

    @Override
    public void initView() {
        setContentView(R.layout.activity_wallet);
        initTitleBar();

        //初始化控件
        rl_nav = (RelativeLayout) findViewById(R.id.rl_nav);
        mHsv = (SyncHorizontalScrollView) findViewById(R.id.mHsv);
        rg_nav_content = (RadioGroup) findViewById(R.id.rg_nav_content);
        iv_nav_indicator = (ImageView) findViewById(R.id.iv_nav_indicator);
        iv_nav_left = (ImageView) findViewById(R.id.iv_nav_left);
        iv_nav_right = (ImageView) findViewById(R.id.iv_nav_right);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_wallet);

        //获取屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mScreenWidth=dm.widthPixels;
        indicatorWidth = dm.widthPixels / 4;

        ViewGroup.LayoutParams cursor_Params = iv_nav_indicator.getLayoutParams();
        cursor_Params.width = indicatorWidth;//// 初始化滑动下标的宽
        iv_nav_indicator.setLayoutParams(cursor_Params);

        mHsv.setSomeParam(rl_nav, iv_nav_left, iv_nav_right, this);

        //获取布局填充器
        mInflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);

        //另一种方式获取
//		LayoutInflater mInflater = LayoutInflater.from(this);

        initNavigationHSV();

//        mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
//        SuccessfulOrderFragment sof=new SuccessfulOrderFragment();
//        frags=new ArrayList<Fragment>();
//        frags.add(sof);
//        UnfinishedFragment uf=new UnfinishedFragment();
//        frags.add(uf);
//        UnfinishedFragment uf1=new UnfinishedFragment();
//        frags.add(uf1);
////
//        mAdapter=new FXFragmentPagerAdapter(getSupportFragmentManager(),frags);
//        mViewPager.setAdapter(mAdapter);
//        mViewPager.setCurrentItem(0);
        mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

    }



    /**
     *  选择的Column里面的Tab
     * */
    private void selectTab(int tab_postion) {
//        columnSelectIndex = tab_postion;
        for (int i = 0; i < rg_nav_content.getChildCount(); i++) {
            View checkView = rg_nav_content.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            // rg_nav_content.getParent()).smoothScrollTo(i2, 0);
            mHsv.smoothScrollTo(i2, 0);
            // mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
            // mItemWidth , 0);
        }
        //判断是否选中
        for (int j = 0; j <  rg_nav_content.getChildCount(); j++) {
            View checkView = rg_nav_content.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }
    }
    private void initNavigationHSV() {

        rg_nav_content.removeAllViews();

        for(int i=0;i<tabTitle.length;i++){

            RadioButton rb = (RadioButton) mInflater.inflate(R.layout.nav_radiogroup_item, null);
            rb.setId(i);
            rb.setText(tabTitle[i]);
            rb.setLayoutParams(new ViewGroup.LayoutParams(indicatorWidth,
                    ViewGroup.LayoutParams.MATCH_PARENT));

            rg_nav_content.addView(rb);
        }

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

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                if (rg_nav_content != null && rg_nav_content.getChildCount() > position) {
                    ((RadioButton) rg_nav_content.getChildAt(position)).performClick();
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        rg_nav_content.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(rg_nav_content.getChildAt(checkedId)!=null){

                    TranslateAnimation animation = new TranslateAnimation(
                            currentIndicatorLeft ,
                            ((RadioButton) rg_nav_content.getChildAt(checkedId)).getLeft(), 0f, 0f);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setDuration(100);
                    animation.setFillAfter(true);

                    //执行位移动画
                    iv_nav_indicator.startAnimation(animation);

                    mViewPager.setCurrentItem(checkedId);	//ViewPager 跟随一起 切换

                    //记录当前下标的距最左侧的距离
                  currentIndicatorLeft = ((RadioButton) rg_nav_content.getChildAt(checkedId)).getLeft();

//                    mHsv.smoothScrollTo(
//                            (checkedId > 1 ? ((RadioButton) rg_nav_content.getChildAt(checkedId)).getLeft() : 0) - ((RadioButton) rg_nav_content.getChildAt(2)).getLeft(), 0);
                    selectTab(checkedId);
                }
            }
        });
    }



    @Override
    public void processClick(View v) {

    }


    public static class TabFragmentPagerAdapter extends FragmentPagerAdapter {

        public TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            Fragment fragment = null;
            switch (arg0) {
                case 0:
                    fragment = new SuccessfulOrderFragment();
                    break;
                case 1:
                    fragment = new UnfinishedFragment();
                    break;
                case 2:
                    fragment=new SuccessfulOrderFragment();
                    break;
                default:

                   fragment=new SuccessfulOrderFragment();
//                    Bundle args = new Bundle();
//                    args.putString("", tabTitle[arg0]);
//                    fragment.setArguments(args);

                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {

            return tabTitle.length;
        }

    }
}
