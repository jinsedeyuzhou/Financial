package com.financial.android.activity.projects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.financial.android.R;
import com.financial.android.base.BaseFragment;
import com.lidroid.xutils.ViewUtils;

/**
 * Created by wyy on 2016/1/27.
 *
 */
public class DescriptionFragment extends BaseFragment {


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View  view = inflater.inflate(R.layout.frag_description,container,false);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void processClick(View v) {

    }
}
