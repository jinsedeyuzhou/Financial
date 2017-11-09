package com.financial.android.activity.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.financial.android.R;
import com.financial.android.base.BaseFragment;
import com.scwang.smartrefresh.layout.internal.ProgressDrawable;

/**
 * Created by wyy on 2016/1/21.
 */
public class SuccessfulOrderFragment extends BaseFragment {

    private ImageView mIvProgress;
    private ProgressDrawable mProgressDrawable;

    @Override
    protected void bindEvent() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.frag_successful_order;
    }

    @Override
    protected void initView(View view) {
        mIvProgress = (ImageView) view.findViewById(R.id.iv_progress);
        mProgressDrawable = new ProgressDrawable();
        mProgressDrawable.setColor(0xff000000);
        mIvProgress.setImageDrawable(mProgressDrawable);
        mProgressDrawable.start();


    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void processClick(View v) {

    }
}
