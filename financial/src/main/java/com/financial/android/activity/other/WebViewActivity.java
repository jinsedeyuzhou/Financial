package com.financial.android.activity.other;

import java.io.IOException;

import org.apache.http.impl.cookie.BasicClientCookie;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.utils.DialogUtil;
import com.financial.android.view.CustomProgressDialog;

@SuppressLint("SetJavaScriptEnabled")
public class WebViewActivity extends Activity {
    private ImageView mBack;
    private TextView title;
    private WebView wv_webView_protocol;
    private String protocolUrl;
    private Context ct = this;
    private Bundle bundle;
    private String strTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_webview);
        showProgressDialog("加载中...");
        Intent intent = getIntent();
        bundle = intent.getExtras();
        strTitle = bundle.getString("title", "百度一下");
        protocolUrl = bundle.getString("url", "https://www.baidu.com/");


        initTitleBar();
        initView();
    }

    /**
     * 设置标题
     */
    private void initTitleBar() {
        RelativeLayout bar_rl_left = (RelativeLayout) findViewById(R.id.bar_rl_left);
        bar_rl_left.setVisibility(View.VISIBLE);
        bar_rl_left.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView bar_tv_title = (TextView) findViewById(R.id.bar_tv_title);
        bar_tv_title.setText(strTitle);
    }

    private void initView() {
        wv_webView_protocol = (WebView) findViewById(R.id.webview_protocol);
        // 同步cookies
        // synCookies(this, protocolUrl);
        // 设置webview属性
        WebSettings wSet = wv_webView_protocol.getSettings();
        initWebViewSettings(wSet);
        wv_webView_protocol.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view,
                                                              String url) {
                WebResourceResponse response = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    response = super.shouldInterceptRequest(view, url);
                    //引用本地文件
                    if (url.contains("index.css")) {
                        try {
                            response = new WebResourceResponse("text/css",
                                    "UTF-8", getAssets().open("index.css"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return response;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                closeProgressDialog();
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                // super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });
        wv_webView_protocol.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        wv_webView_protocol.loadUrl(protocolUrl);
    }

    private void synCookies(WebViewActivity webViewActivity, String protocolUrl) {
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        // Cookie sessionCookie =
        // FXApplication.getApp().getPersistentCookieStore().getCookie("userName");
        BasicClientCookie sessionCookie = new BasicClientCookie("test", "hello");
        sessionCookie.setDomain("192.168.1.105");
        sessionCookie.setPath("/");
        // Cookie sessionCookie = null;
        if (sessionCookie != null) {
            String cookieString = sessionCookie.getName() + "="
                    + sessionCookie.getValue() + "; domain="
                    + sessionCookie.getDomain();
            cookieManager.setCookie(protocolUrl, cookieString);

            CookieSyncManager.getInstance().sync();
        }
    }

    private void initWebViewSettings(WebSettings wSet) {
        // 是否显示缩放按钮
        wSet.setBuiltInZoomControls(true);
        // 支持缩放
        wSet.setSupportZoom(true);
        // 默认字体大小
        wSet.setDefaultFontSize(12);
        // 设置可以访问文件
        wSet.setAllowFileAccess(true);
        // 设置支持webView JavaScript
        wSet.setJavaScriptEnabled(true);
        // 设置缓冲的模式
        wSet.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置字符编码
        wSet.setDefaultTextEncodingName("utf-8");
        //优先使用缓存
        wSet.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//		wSet.setAppCacheEnabled(true);
//		wSet.setDomStorageEnabled(true);
//		wSet.setDatabaseEnabled(true);

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

}
