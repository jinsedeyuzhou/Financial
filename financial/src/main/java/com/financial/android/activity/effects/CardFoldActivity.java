package com.financial.android.activity.effects;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.adapter.TestStackAdapter;
import com.financial.android.base.BaseActivity;
import com.loopeer.cardstack.AllMoveDownAnimatorAdapter;
import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.UpDownAnimatorAdapter;
import com.loopeer.cardstack.UpDownStackAnimatorAdapter;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/11/14.
 */

public class CardFoldActivity extends BaseActivity implements CardStackView.ItemExpendListener {
    public static Integer[] TEST_DATAS = new Integer[]{
            R.color.color_1,
            R.color.color_2,
            R.color.color_3,
            R.color.color_4,
            R.color.color_5,
            R.color.color_6,
            R.color.color_7,
            R.color.color_8,
            R.color.color_9,
            R.color.color_10,
            R.color.color_11,
            R.color.color_12,
            R.color.color_13,
            R.color.color_14,
            R.color.color_15,
            R.color.color_16,
            R.color.color_17,
            R.color.color_18,
            R.color.color_19,
            R.color.color_20,
            R.color.color_21,
            R.color.color_22,
            R.color.color_23,
            R.color.color_24,
            R.color.color_25,
            R.color.color_26
    };
    private CardStackView mStackView;
    private LinearLayout mActionButtonContainer;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_fold);
    }

    @Override
    public void initView() {
        mStackView = (CardStackView) findViewById(R.id.cardStackView);
        mActionButtonContainer = (LinearLayout) findViewById(R.id.button_container);
        TestStackAdapter mTestStackAdapter = new TestStackAdapter(this);

        mStackView.setAdapter(mTestStackAdapter);
        mStackView.setItemExpendListener(this);
        mTestStackAdapter.updateData(Arrays.asList(TEST_DATAS));
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
        bar_tv_title.setText("卡式折叠");
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


    public void onPreClick(View view) {
        mStackView.pre();
    }

    public void onNextClick(View view) {
        mStackView.next();
    }

    public void anim(View view) {
        count = 3;
        if (count % 3 == 0) {
            mStackView.setAnimatorAdapter(new AllMoveDownAnimatorAdapter(mStackView));
        } else if (count % 3 == 1) {
            mStackView.setAnimatorAdapter(new UpDownAnimatorAdapter(mStackView));

        } else if (count % 3 == 2) {
            mStackView.setAnimatorAdapter(new UpDownStackAnimatorAdapter(mStackView));
        }


    }

    @Override
    public void onItemExpend(boolean expend) {
//        mActionButtonContainer.setVisibility(expend ? View.VISIBLE : View.GONE);
    }

}
