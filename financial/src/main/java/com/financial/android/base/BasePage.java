package com.financial.android.base;

import com.financial.android.R;
import com.financial.android.utils.DialogUtil;
import com.financial.android.utils.NetUtil;
import com.financial.android.view.CustomProgressDialog;
import com.financial.android.view.CustomToast;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class BasePage implements OnClickListener {
	protected Context ct;
	protected View contentView;
	protected TextView titleTv;
	public boolean isLoadSuccess = false;

	protected FXApplication app;
	// @ViewInject(R.id.loading_view)
	protected View loadingView;
	// @ViewInject(R.id.ll_load_fail)
	protected LinearLayout loadfailView;


	public BasePage(Context context) {
		ct = context;
		contentView = initView((LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
		// loadingView = contentView.findViewById(R.id.loading_view);
		// loadfailView = (LinearLayout)
		// contentView.findViewById(R.id.ll_load_fail);
		app = (FXApplication) ct.getApplicationContext();


	}

	protected void initTitleBar(View view) {
		titleTv = (TextView) view.findViewById(R.id.bar_tv_title);
	}

	/*
	 * 
	 * @OnClick(R.id.btn_left) public void showMenu() { // if (sm.isMenuShowing()) { // sm.showContent(); // } else { // sm.showMenu(); // } }
	 */

	public View getContentView() {
		return contentView;
	}

	protected abstract View initView(LayoutInflater inflater);

	public abstract void initData();

	protected abstract void processClick(View v);

	public void showToast(String msg) {
		showToast(msg, 0);
	}

	public void showToast(String msg, int time) {
		CustomToast customToast = new CustomToast(ct, msg, time);
		customToast.show();
	}

	protected CustomProgressDialog dialog;

	public void showProgressDialog(String content) {
		if (dialog == null && ct != null) {
			dialog = (CustomProgressDialog) DialogUtil.createProgressDialog(ct, content);
		}
		dialog.show();
	}

	public void showLoadingView() {
		if (loadingView != null)
			loadingView.setVisibility(View.VISIBLE);
	}

	public void dismissLoadingView() {
		if (loadingView != null)
			loadingView.setVisibility(View.INVISIBLE);
	}

	public void showLoadFailView() {
		if (loadingView != null) {
			loadingView.setVisibility(View.VISIBLE);
			loadfailView.setVisibility(View.VISIBLE);
		}

	}

	public void dismissLoadFailView() {
		if (loadingView != null)
			loadfailView.setVisibility(View.INVISIBLE);
	}

	public void closeProgressDialog() {
		if (dialog != null)
			dialog.dismiss();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {


		default:
			break;
		}
		processClick(v);

	}

	protected void loadData(HttpRequest.HttpMethod method, String url, RequestParams params, RequestCallBack<String> callback) {
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000 * 1);
		LogUtils.allowD = true;
		if (params != null) {
			if (params.getQueryStringParams() != null)
				LogUtils.d(url + params.getQueryStringParams().toString());
		} else {
			params = new RequestParams();
		}
		params.addHeader("x-deviceid", app.getDeviceId());
//		params.addHeader("x-channel", app.getChannel());
		if (!NetUtil.isConnectionAvailable(ct)) {
			showToast("加载失败，请检查网络！");
			callback.onFailure(new HttpException(), "无网络");
		} else {
			http.send(method, url, params, callback);
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {

	}

	public void onResume() {

	}
	protected void onPause() {

	}
}
