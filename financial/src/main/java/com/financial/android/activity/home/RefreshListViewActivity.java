package com.financial.android.activity.home;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.base.BaseActivity;
import com.financial.android.view.OnRefreshListener;
import com.financial.android.view.RefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyy on 2016/3/3.
 *
 */
public class RefreshListViewActivity extends BaseActivity implements OnRefreshListener{

    private RefreshListView refresh_listview;
    private List<String> textList;
    private MyAdapter adapter;
    private RelativeLayout bar_rl_left;
    private TextView bar_tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_listview);
    }

    @Override
    public void initView() {
        initTitleBar();
        refresh_listview = (RefreshListView) findViewById(R.id.refresh_listview);
        textList = new ArrayList<String>();
        for (int i = 0; i < 25; i++) {
            textList.add("这是一条ListView的数据" + i);
        }
        adapter = new MyAdapter();
        refresh_listview.setAdapter(adapter);
        refresh_listview.setOnRefreshListener(this);
    }
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return textList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return textList.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            TextView textView = new TextView(ct);
            textView.setText(textList.get(position));
            textView.setTextColor(Color.RED);
            textView.setTextSize(18.0f);
            return textView;
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
        bar_tv_title.setText("刷新");
    }
    @Override
    public void onDownPullRefresh() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                SystemClock.sleep(2000);
                for (int i = 0; i < 2; i++) {
                    textList.add(0, "这是下拉刷新出来的数据" + i);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                adapter.notifyDataSetChanged();
                refresh_listview.hideHeaderView();
            }
        }.execute(new Void[]{});
    }

    public void onLoadingMore() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                SystemClock.sleep(2000);

                textList.add("这是加载更多出来的数据1");
                textList.add("这是加载更多出来的数据2");
                textList.add("这是加载更多出来的数据3");
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                adapter.notifyDataSetChanged();

                // 控制脚布局隐藏
                refresh_listview.hideFooterView();
            }
        }.execute(new Void[]{});
    }
    @Override
    public void initData() {

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void processClick(View v) {

    }
}
