package com.financial.android.activity.other;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.financial.android.R;
import com.financial.android.utils.DialogUtil;
import com.financial.android.view.CustomProgressDialog;

import org.apache.http.impl.cookie.BasicClientCookie;

@SuppressLint("SetJavaScriptEnabled")
public class JavaScriptActivity extends Activity implements OnClickListener{
    private ImageView mBack;
    private TextView title;
    private WebView webview_js;
    private String protocolUrl;
    private Context ct = this;
    private Bundle bundle;
    private String strTitle;
    private EditText et_send_text;
    private Button btn_send;
    private Handler handler = new Handler();
//    private GoogleApiClient client;
    private TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_javascript);
//        showProgressDialog("加载中...");
//        Intent intent = getIntent();
//        bundle = intent.getExtras();
//        strTitle = bundle.getString("title", "百度一下");
//        protocolUrl = bundle.getString("url", "https://www.baidu.com/");


        initTitleBar();
        initView();
        initData();
    }

    private void initData() {
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
        strTitle="javascript";
        bar_tv_title.setText(strTitle);
    }

    private void initView() {

        webview_js = (WebView) findViewById(R.id.webview_js);
        et_send_text = (EditText) findViewById(R.id.et_send_text);
        tv_show = (TextView) findViewById(R.id.tv_show);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);

        // 设置webview属性
        WebSettings wSet = webview_js.getSettings();
        initWebViewSettings(wSet);
        webview_js.addJavascriptInterface(new runJavaScript(), "custom");
        webview_js.setWebChromeClient(new WebChromeClient() {
        });
        webview_js.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view,
                                                              String url) {
                WebResourceResponse response = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    response = super.shouldInterceptRequest(view, url);
                    //引用本地文件
//                    if (url.contains("index.css")) {
//                        try {
//                            response = new WebResourceResponse("text/css",
//                                    "UTF-8", getAssets().open("index.css"));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
                }
                return response;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                closeProgressDialog();
                super.onPageFinished(view, url);

            }

            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                // super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });
        //长按处理
        webview_js.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                //不显示编辑框
                return true;
            }
        });
        protocolUrl="file:///android_asset/android.html";
        webview_js.loadUrl(protocolUrl);
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    public  class runJavaScript{//这个Java 对象是绑定在另一个线程里的，
        //添加接口支持api17以上
        @JavascriptInterface
        public void runOnAndroidJavaScript(final String str){
            handler.post(new Runnable(){
                @Override
                public void run() {//这里应该特别注意的
                    tv_show.setText("This is a message from javascript:"+str);
                }

            });
        }


        @JavascriptInterface
        public void commolMethod(String message) {

            Toast.makeText(ct,message,Toast.LENGTH_SHORT).show();

        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_send:
                webview_js.loadUrl("javascript:getFromAndroid('" + et_send_text.getText().toString() + "')");
                break;
        }

    }


    @Override
    public void onStart() {
        super.onStart();

//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "JavaScript Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.study.javascript/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop()
    {
        super.onStop();

//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW,
//                "JavaScript Page",
//                Uri.parse("http://host/path"),
//                //注意是包名
//                Uri.parse("android-app://com.study.javascript/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
    }
}
