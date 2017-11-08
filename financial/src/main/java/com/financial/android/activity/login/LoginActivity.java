package com.financial.android.activity.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.financial.android.R;
import com.financial.android.base.BaseActivity;
import com.financial.android.utils.LogUtil;
import com.financial.android.view.ClearEditText;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";
    private RelativeLayout bar_rl_left;
    private TextView bar_tv_title;
    private RelativeLayout rl_login_register;
    private ImageView iv_login_authcode;
    private ValidationCode bpUtil;
    private String verifyCode; // 保存验证码信息
    // 登录按钮
    private Button signin;
    private ClearEditText userName;
    private ClearEditText passwd;
    private ClearEditText authCode;
    // 是否记住密码
    private CheckBox cb_rem_passwd;
    // 找回密码
    private TextView tv_find_passwd;
    private LinearLayout ll_login_content;
    private UMImage image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initView() {


        // 用户名
        userName = (ClearEditText) findViewById(R.id.et_username_login);
        // 密码
        passwd = (ClearEditText) findViewById(R.id.et_pass_login);
        // 验证码
        authCode = (ClearEditText) findViewById(R.id.edt_login_authcode);
        // 登录验证码
        iv_login_authcode = (ImageView) findViewById(R.id.iv_login_authcode);
        // 注册按钮
        rl_login_register = (RelativeLayout) findViewById(R.id.rl_login_register);
        // 登录
        signin = (Button) findViewById(R.id.btn_sign_in);
        // 记住密码
        cb_rem_passwd = (CheckBox) findViewById(R.id.cb_rem_passwd);
        // 找回密码
        tv_find_passwd = (TextView) findViewById(R.id.tv_find_passwd);
        // 输入数据
        ll_login_content = (LinearLayout) findViewById(R.id.ll_login_content);

        //分享
        Button btn_share = (Button) findViewById(R.id.btn_share);
        Button btn_login_other = (Button) findViewById(R.id.btn_login_other);
        btn_share.setOnClickListener(this);
        btn_login_other.setOnClickListener(this);

        // 失去焦点隐藏软键盘 不起作用
        ll_login_content.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg) {
                arg0.performClick();
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                return imm.hideSoftInputFromWindow(getCurrentFocus()
                        .getWindowToken(), 0);
            }
        });

        signin.setOnClickListener(this);
        iv_login_authcode.setOnClickListener(this);
        rl_login_register.setOnClickListener(this);
        tv_find_passwd.setOnClickListener(this);
        initTitleBar();
    }

    @Override
    public void initData() {

        image = new UMImage(LoginActivity.this, R.drawable.ic_launcher);
        // 生成并显示验证码
        bpUtil = ValidationCode.getInstance();
        iv_login_authcode.setImageBitmap(bpUtil.createBitmap());
        // 保存验证码
        verifyCode = bpUtil.getCode();

        try {
            File file = new File(ct.getFilesDir(), "info.xml");
            FileInputStream fis = new FileInputStream(file);
            //-----------------------------------------------------
            SharedPreferences sp = ct.getSharedPreferences("config", Context.MODE_PRIVATE);
            String qq = sp.getString("qq", "");
            userName.setText(qq);
            String pw = sp.getString("pw", "");
            passwd.setText(pw);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void bindEvent() {

    }

    private void initTitleBar() {
        bar_tv_title = (TextView) findViewById(R.id.bar_tv_title);
        bar_rl_left = (RelativeLayout) findViewById(R.id.bar_rl_left);
        bar_rl_left.setOnClickListener(this);
        bar_rl_left.setVisibility(View.VISIBLE);
        bar_tv_title.setText("登录");

    }

    @Override
    public void processClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            // 返回
            case R.id.bar_rl_left:
                onBackPressed();
                break;
            // 注册
            case R.id.rl_login_register:
                intent = new Intent(ct, RegisterActivity.class);
                startActivity(intent);
                // 验证码
            case R.id.iv_login_authcode:
                iv_login_authcode.setImageBitmap(bpUtil.createBitmap());
                verifyCode = bpUtil.getCode();
                break;
            case R.id.btn_sign_in:
                // 最大长度可以通过布局控制
                System.out.println("verifyCode:" + verifyCode);
                if (userName.getText().toString().length() == 0) {
                    showToast("请输入用户名");
                } else if (userName.getText().length() < 6
                        || userName.getText().length() > 20) {
                    showToast("用户名应为6-20位半角英文或数字");
                } else if (passwd.getText().toString().length() == 0) {
                    showToast("请输入密码");
                } else if (passwd.getText().toString().length() < 6
                        || passwd.getText().toString().length() > 16) {
                    showToast("密码应为6-25位半角英文或数字");
                } else if (authCode.getText().toString().length() == 0) {
                    showToast("请输入图片验证码");
                } else if (!authCode.getText().toString()
                        .equalsIgnoreCase(verifyCode)) {
                    showToast("验证码不正确");
                } else {
                    String name = userName.getText().toString();
                    String password = passwd.getText().toString();
                    // String code = authCode.getText().toString();
                    // signin.setClickable(false);
                    submit(name, password);
                }

                break;
            case R.id.tv_find_passwd:
                intent = new Intent(ct, LostPasswdActivity.class);
                startActivity(intent);
                break;

            //分享,第三方登录
            case R.id.btn_share:
                final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                        {
                                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                                SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
                        };
                new ShareAction(LoginActivity.this).setDisplayList( displaylist )
                        .withText("呵呵")
                        .withTitle("title")
                        .withTargetUrl("http://www.baidu.com")
                        .setListenerList(umShareListener)
                        .withMedia(image)
                        .open();
                break;
            case R.id.btn_login_other:

                break;
            default:
                break;
        }
    }

    /**
     * 提交数据
     */
    private void submit(String name, String password) {

        // SharedPreferences sp=ct.getSharedPreferences("config", mode)
        try {
            if (cb_rem_passwd.isChecked()) {

                // File file = new File("/mnt/sdcard/info.txt");
                // File file=new File(ct.getFilesDir(),"info.txt");
                File file = new File(ct.getFilesDir(), "info.xml");
                FileOutputStream fos = new FileOutputStream(file);
                // String str = name + "##" + password;
                // fos.write(str.getBytes());
                // fos.close();
                // showToast("登录成功");
                // -------------------------------------------------//
                XmlSerializer xs = Xml.newSerializer();
                xs.setOutput(fos, "UTF-8");
                xs.startDocument("utf-8", true);
                xs.startTag(null, "info");
                xs.startTag(null, "qq");
                xs.text(name);
                xs.endTag(null, "qq");
                xs.startTag(null, "pw");
                xs.text(password);
                xs.endTag(null, "pw");
                xs.endTag(null, "info");
                xs.endDocument();
                fos.close();
                showToast("登录成功");
                // ----------------------------------------------//
                SharedPreferences sp = ct.getSharedPreferences("config", Context.MODE_PRIVATE);
                Editor edit = sp.edit();

                edit.putString("qq", name);
                edit.putString("pw", password);
                edit.commit();


            } else {
                LogUtil.d(TAG, "不记住密码！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult( requestCode, resultCode, data);
    }
    UMShareListener umShareListener= new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ct, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ct,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ct,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };


}
