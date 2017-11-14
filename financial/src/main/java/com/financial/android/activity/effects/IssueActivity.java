package com.financial.android.activity.effects;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.financial.android.R;
import com.financial.android.adapter.ExpandableAdapter;
import com.financial.android.base.BaseActivity;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Administrator on 2017/11/13.
 *
 */

public class IssueActivity extends BaseActivity {

    private ImageView mTitleBack;
    private TextView mTitleName;
    private ExpandableListView mElistView;
    private List quesList;
    private List answList;
    private ExpandableAdapter mElAdapter;

    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_issue);
    }

    @Override
    public void initView() {
        mElistView = (ExpandableListView) findViewById(R.id.elistview);
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
        bar_tv_title.setText("常见问题");
    }


    @Override
    protected void bindEvent() {
        mTitleBack.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mTitleName.setText("常见问题");
        Resources res = getResources();
        quesList = Arrays.asList(res.getStringArray(R.array.question_array));
        answList = Arrays.asList(res.getStringArray(R.array.answer_array));
        mElAdapter = new ExpandableAdapter(this, quesList, answList);
        mElistView.setAdapter(mElAdapter);
        mElistView.setGroupIndicator(null);
        int groupCount = mElistView.getCount();
        for (int i=0; i<groupCount; i++)
        {
            mElistView.expandGroup(i);
        }
    }

    @Override
    public void processClick(View v) {
        switch(v.getId())
        {
            case R.id.bar_rl_left:
                finish();
                break;
            default:
                break;
        }

    }
}
