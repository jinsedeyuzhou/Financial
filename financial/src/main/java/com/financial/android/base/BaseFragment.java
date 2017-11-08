package com.financial.android.base;

import com.financial.android.utils.DialogUtil;
import com.financial.android.utils.LogUtil;
import com.financial.android.utils.NetUtil;
import com.financial.android.view.CustomProgressDialog;
import com.financial.android.view.CustomToast;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public abstract class BaseFragment extends Fragment implements OnClickListener {
    private static final String TAG = "BaseFragment";
    protected Context ct;
    protected FXApplication app;

    protected View loadingView;
    protected LinearLayout loadfailView;

    protected View rootView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.d(TAG, "onActivityCreated");
        app = (FXApplication) getActivity().getApplication();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG, "oncreate");
        //放在这里也可以
//		ct = getActivity();
    }

    public View getRootView() {
        return rootView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutID(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        bindEvent();
        initData(savedInstanceState);
    }

    protected abstract void bindEvent();

    protected abstract int getLayoutID();

    protected abstract void initView(View view);

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void processClick(View v);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ct = context;
    }

    @Override
    public void onStart() {
        LogUtil.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    public void onStop() {
        LogUtil.d(TAG, "onStop");
        super.onStop();

    }

    @Override
    public void onResume() {
        LogUtil.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        LogUtil.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        LogUtil.d(TAG, "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        LogUtil.d(TAG, "onDetach");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        LogUtil.d(TAG, "onDestroy");
        super.onDestroy();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 0:

                break;

            default:
                break;
        }
        processClick(v);
    }

    /**
     * 自定义Toast
     *
     * @param msg
     */
    protected void showToast(String msg) {
        showToast(msg, 0);
    }

    protected void showToast(String msg, int time) {
        CustomToast customToast = new CustomToast(ct, msg, time);
        customToast.show();
    }

    /**
     * 自定义进度条
     */
    protected CustomProgressDialog dialog;

    /**
     * 展示进度条
     *
     * @param content
     */
    protected void showProgressDialog(String content) {
        if (dialog == null && ct != null) {
            dialog = (CustomProgressDialog) DialogUtil.createProgressDialog(ct,
                    content);
        }
        dialog.show();
    }

    /**
     * 关闭进度条
     */
    protected void closeProgressDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    /**
     * 加载成功，展示
     */
    public void showLoadingView() {
        if (loadingView != null)
            loadingView.setVisibility(View.VISIBLE);
    }

    /**
     * 加载成功，完成消失展示
     */
    public void dismissLoadingView() {
        if (loadingView != null)
            loadingView.setVisibility(View.INVISIBLE);
    }

    /**
     * 加载失败，展示
     */
    public void showLoadFailView() {
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
            loadfailView.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 加载失败，完成消失展示
     */
    public void dismissLoadFailView() {
        if (loadingView != null)
            loadfailView.setVisibility(View.INVISIBLE);
    }

}
