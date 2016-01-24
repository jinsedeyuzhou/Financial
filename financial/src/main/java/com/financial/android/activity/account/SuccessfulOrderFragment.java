package com.financial.android.activity.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.financial.android.R;
import com.financial.android.base.BaseFragment;

/**
 * Created by wyy on 2016/1/21.
 */
public class SuccessfulOrderFragment extends BaseFragment {

    private View view;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.frag_successful_order,container,false);

        return view;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void processClick(View v) {

    }
}
