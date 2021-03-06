package com.financial.android.activity.projects;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.financial.android.R;
import com.financial.android.adapter.BaseRecyclerAdapter;
import com.financial.android.adapter.ProjectsListAdapter;
import com.financial.android.adapter.SmartViewHolder;
import com.financial.android.base.BaseFragment;
import com.financial.android.bean.Product;
import com.financial.android.utils.DynamicTimeFormat;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.R.layout.simple_list_item_2;
import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Created by wyy on 2016/1/26.
 * 直投项目
 */
public class AssignmentFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private BaseRecyclerAdapter<Item> mAdpater;

    private enum Item {
        尺寸拉伸("下拉的时候Header的高度跟随变大"),
        位置平移("下拉的时候Header的位置向下偏移"),
        背后固定("下拉的时候Header固定在背后"),
        显示时间("开启显示上次更新功能"),
        隐藏时间("关闭显示上次更新功能"),
        默认主题("更改为默认主题颜色"),
        橙色主题("更改为橙色主题颜色"),
        红色主题("更改为红色主题颜色"),
        绿色主题("更改为绿色主题颜色"),
        蓝色主题("更改为蓝色主题颜色"),
        加载更多("上啦加载更多"),
        ;
        public String name;
        Item(String name) {
            this.name = name;
        }
    }

    private RecyclerView mRecyclerView;
    private RefreshLayout mRefreshLayout;
    private ClassicsHeader mClassicsHeader;
    private Drawable mDrawableProgress;
    private static boolean isFirstEnter = true;


    //数据源
    private ArrayList<Product> products;
    private ProjectsListAdapter plAdapter;


    @Override
    protected void bindEvent() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.frag_assignment_projects;
    }

    @Override
    protected void initView(View view) {

        mRefreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);

        int deta = new Random().nextInt(7 * 24 * 60 * 60 * 1000);
        mClassicsHeader = (ClassicsHeader)mRefreshLayout.getRefreshHeader();
        mClassicsHeader.setLastUpdateTime(new Date(System.currentTimeMillis()-deta));
        mClassicsHeader.setTimeFormat(new SimpleDateFormat("更新于 MM-dd HH:mm", Locale.CHINA));
        mClassicsHeader.setTimeFormat(new DynamicTimeFormat("更新于 %s"));

        mDrawableProgress = mClassicsHeader.getProgressView().getDrawable();
        if (mDrawableProgress instanceof LayerDrawable) {
            mDrawableProgress = ((LayerDrawable) mDrawableProgress).getDrawable(0);
        }

        View recycler = view.findViewById(R.id.recyclerView);
        if (recycler instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) recycler;
            recyclerView.setLayoutManager(new LinearLayoutManager(ct));
            recyclerView.addItemDecoration(new DividerItemDecoration(ct, VERTICAL));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdpater = new BaseRecyclerAdapter<Item>(Arrays.asList(Item.values()), simple_list_item_2,this) {
                @Override
                protected void onBindViewHolder(SmartViewHolder holder, Item model, int position) {
                    holder.text(android.R.id.text1, model.name());
                    holder.text(android.R.id.text2, model.name);
                    holder.textColorId(android.R.id.text2, R.color.colorTextAssistant);
                }
            });
            mRecyclerView = recyclerView;
        }

        if (isFirstEnter) {
            isFirstEnter = false;
            //触发自动刷新
            mRefreshLayout.autoRefresh();
        }

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        products=new ArrayList<Product>();



    }


    private class GetDataTask extends AsyncTask<Void, Void, List<Product>>
    {

        @Override
        protected List<Product> doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Product> result) {

            //可以将productsList数据清除，重新加载请求后的数据

            plAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.

            super.onPostExecute(result);
        }
    }
    @Override
    protected void processClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (Item.values()[position]) {
            case 背后固定:
                mClassicsHeader.setSpinnerStyle(SpinnerStyle.FixedBehind);
                mRefreshLayout.setPrimaryColors(0xff444444, 0xffffffff);
                if (Build.VERSION.SDK_INT >= 21) {
                    mDrawableProgress.setTint(0xffffffff);
                } else if (mDrawableProgress instanceof VectorDrawableCompat) {
                    ((VectorDrawableCompat) mDrawableProgress).setTint(0xffffffff);
                }
                /*
                 * 由于是后面才设置，需要手动更改视图的位置
                 * 如果在 onCreate 或者 xml 中设置好[SpinnerStyle] 就不用手动调整位置了
                 */
                mRefreshLayout.getLayout().bringChildToFront(mRecyclerView);
                break;
            case 尺寸拉伸:
                mClassicsHeader.setSpinnerStyle(SpinnerStyle.Scale);
                break;
            case 位置平移:
                mClassicsHeader.setSpinnerStyle(SpinnerStyle.Translate);
                break;
            case 显示时间:
                mClassicsHeader.setEnableLastTime(true);
                break;
            case 隐藏时间:
                mClassicsHeader.setEnableLastTime(false);
                break;
            case 默认主题:
                setThemeColor(R.color.colorPrimary, R.color.colorPrimaryDark);
                mRefreshLayout.getLayout().setBackgroundResource(android.R.color.transparent);
                mRefreshLayout.setPrimaryColors(0, 0xff666666);
                if (Build.VERSION.SDK_INT >= 21) {
                    mDrawableProgress.setTint(0xff666666);
                } else if (mDrawableProgress instanceof VectorDrawableCompat) {
                    ((VectorDrawableCompat) mDrawableProgress).setTint(0xff666666);
                }
                break;
            case 蓝色主题:
                setThemeColor(R.color.colorPrimary, R.color.colorPrimaryDark);
                break;
            case 绿色主题:
                setThemeColor(android.R.color.holo_green_light, android.R.color.holo_green_dark);
                break;
            case 红色主题:
                setThemeColor(android.R.color.holo_red_light, android.R.color.holo_red_dark);
                break;
            case 橙色主题:
                setThemeColor(android.R.color.holo_orange_light, android.R.color.holo_orange_dark);
                break;
            case 加载更多:
                mRefreshLayout.autoLoadmore();
                return;
        }
        mRefreshLayout.autoRefresh();
    }

    private void setThemeColor(int colorPrimary, int colorPrimaryDark) {
        mRefreshLayout.setPrimaryColorsId(colorPrimary, android.R.color.white);
        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(ct, colorPrimaryDark));
            mDrawableProgress.setTint(0xffffffff);
        } else if (mDrawableProgress instanceof VectorDrawableCompat) {
            ((VectorDrawableCompat) mDrawableProgress).setTint(0xffffffff);
        }
    }

}
