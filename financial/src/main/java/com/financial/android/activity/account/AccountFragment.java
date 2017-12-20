package com.financial.android.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.activity.login.LoginActivity;
import com.financial.android.activity.other.WebViewActivity;
import com.financial.android.activity.welcome.GuideViewActivity;
import com.financial.android.base.BaseFragment;
import com.financial.android.utils.LogUtil;

public class AccountFragment extends BaseFragment {


    private TextView bar_tv_title;
    private RelativeLayout bar_rl_visible;

    // 头像
    private ImageView mIconHead;
    // 消息
    private RelativeLayout rl_account_message;
    // 设置
    private RelativeLayout rl_account_setting;
    //钱包
    private RelativeLayout rl_account_wallet;


    /**
     * 设置标题
     */
    private void initTitleBar() {
        bar_tv_title.setText("我的账户");
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_account;
    }

    @Override
    protected void initView(View view) {
        bar_tv_title = (TextView) view.findViewById(R.id.bar_tv_title);
        bar_rl_visible = (RelativeLayout) view.findViewById(R.id.bar_rl_visible);
        mIconHead = (ImageView) view.findViewById(R.id.iv_icon_head);
        rl_account_message = (RelativeLayout) view.findViewById(R.id.rl_account_message);
        rl_account_setting = (RelativeLayout) view.findViewById(R.id.rl_account_setting);
        rl_account_wallet = (RelativeLayout) view.findViewById(R.id.rl_account_wallet);
        LogUtil.d("BaseFragment", "AccountFragment");

        Button viewpager = (Button) view.findViewById(R.id.btn_viewpager);
        viewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GuideViewActivity.class));
            }
        });
        rl_account_message.setOnClickListener(this);
        rl_account_setting.setOnClickListener(this);
        rl_account_wallet.setOnClickListener(this);
        mIconHead.setOnClickListener(this);
        initTitleBar();
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
                intent = new Intent(ct, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_icon_head:
                intent = new Intent(ct, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_account_wallet:
                intent = new Intent(ct, WalletActivity.class);
                startActivity(intent);
                break;
            default:

                break;
        }
    }

}
