package com.financial.android.activity.projects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.financial.android.R;
import com.financial.android.base.BaseFragment;
import com.financial.android.view.CircleProgressBar;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by wyy on 2016/1/27.
 */
public class DetailsFragment extends BaseFragment {

    private View view;
    @ViewInject(R.id.circlr_progressbar)
    private CircleProgressBar circlr_progressbar;
    private int progress;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.frag_details,container,false);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        progress=80;
        circlr_progressbar.setProgress(progress);

    }

    @Override
    protected void processClick(View v) {

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
