package com.financial.android.base;

import com.financial.android.utils.DialogUtil;
import com.financial.android.utils.LogUtil;
import com.financial.android.utils.NetUtil;
import com.financial.android.view.CustomProgressDialog;
import com.financial.android.view.CustomToast;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.content.Context;
import android.os.Bundle;
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
	public void onActivityCreated( Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		LogUtil.d(TAG, "onActivityCreated");
		app = (FXApplication) getActivity().getApplication();
		initData(savedInstanceState);
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
		LogUtil.d(TAG, "onCreateView");
	View	rootView = initView(inflater, container);
		return rootView;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		ct=context;
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

	protected abstract View initView(LayoutInflater inflater,
			ViewGroup container);

	protected abstract void initData(Bundle savedInstanceState);

	protected abstract void processClick(View v);

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

	protected void loadData(HttpRequest.HttpMethod method, String url,
			RequestParams params, RequestCallBack<String> callback) {

		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(0);

		LogUtil.LOG_LEVEL = 6;
		if (params != null) {
			if (params.getQueryStringParams() != null)
				LogUtil.d(TAG, url + "?"
						+ params.getQueryStringParams().toString());
		} else {
			params = new RequestParams();

		}
		params.addHeader("x-deviceid", app.getDeviceId());
		// params.addHeader("x-channel", app.getChannel());

		if (!NetUtil.isConnectionAvailable(ct)) {
			callback.onFailure(new HttpException(), "无网络");
			showToast("加载失败，请检查网络！");
		} else {
			LogUtil.d(TAG, url);
			http.send(method, url, params, callback);
		}
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
