package com.financial.android.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.activity.login.LoginActivity;
import com.financial.android.activity.other.WebViewActivity;
import com.financial.android.base.BaseFragment;
import com.financial.android.utils.LogUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class AccountFragment extends BaseFragment {

    @ViewInject(R.id.bar_tv_title)
    private TextView bar_tv_title;
    @ViewInject(R.id.bar_rl_visible)
    private RelativeLayout bar_rl_visible;

    // 头像
    @ViewInject(R.id.iv_icon_head)
    private ImageView mIconHead;
    // 消息
    @ViewInject(R.id.rl_account_message)
    private RelativeLayout rl_account_message;
    // 设置
    @ViewInject(R.id.rl_account_setting)
    private RelativeLayout rl_account_setting;
    //钱包
    @ViewInject(R.id.rl_account_wallet)
    private RelativeLayout rl_account_wallet;
    private View view;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        ViewUtils.inject(this, view);
        LogUtil.d("BaseFragment", "AccountFragment");
        rl_account_message.setOnClickListener(this);
        rl_account_setting.setOnClickListener(this);
        rl_account_wallet.setOnClickListener(this);
        mIconHead.setOnClickListener(this);
        initTitleBar();
        return view;
    }

    /**
     * 设置标题
     */
    private void initTitleBar() {
        bar_tv_title.setText("我的账户");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void processClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.rl_account_message:
                intent = new Intent(ct, MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_account_setting:
                 intent=new Intent(ct,SettingActivity.class);
                 startActivity(intent);
                break;
            case R.id.iv_icon_head:
                intent = new Intent(ct, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_account_wallet:
                intent=new Intent(ct,WalletActivity.class);
                startActivity(intent);
                break;
            default:

                break;
        }
    }

}
