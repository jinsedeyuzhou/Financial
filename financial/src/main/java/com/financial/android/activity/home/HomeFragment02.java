package com.financial.android.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.financial.android.R;
import com.financial.android.activity.account.SettingActivity;
import com.financial.android.activity.effects.BannerActivity;
import com.financial.android.activity.effects.CardFoldActivity;
import com.financial.android.activity.effects.IssueActivity;
import com.financial.android.activity.effects.PersonCenterActivity;
import com.financial.android.activity.effects.RePastActivity;
import com.financial.android.activity.other.JavaScriptActivity;
import com.financial.android.activity.other.WebViewActivity;
import com.financial.android.activity.test.VerticalViewActivity;
import com.financial.android.activity.test.VerticalViewPagerActivity;
import com.financial.android.activity.projects.ProductActivity;
import com.financial.android.activity.welcome.GuideViewFlipper;
import com.financial.android.activity.welcome.LockActivity;
import com.financial.android.activity.welcome.LockSetupActivity;
import com.financial.android.adapter.DragAdapter;
import com.financial.android.adapter.GridViewItemAdapter;
import com.financial.android.adapter.ImagePagerAdapter;
import com.financial.android.base.BaseFragment;
import com.financial.android.bean.GridViewItem;
import com.financial.android.utils.LogUtil;
import com.financial.android.view.CircleFlowIndicator;
import com.financial.android.view.DragGridView;
import com.financial.android.view.ViewFlow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment02 extends BaseFragment {

    private TextView bar_tv_title;
    //	@ViewInject(R.id.bar_rl_visible)
//	private RelativeLayout bar_rl_visible;
    // 设置左侧标题
    private TextView bar_tv_left;
    private RelativeLayout bar_rl_left;
    // 设置左侧图片
    private ImageView bar_iv_left;
    // 右侧bar
    private RelativeLayout bar_rl_right;
    // 右侧图片
    private ImageView bar_iv_right;
    private FrameLayout main_notice;
    //gridview
//    private CustomGridView gridview;
    private DragGridView gridview;

    // bannerview
    private ViewFlow mViewFlow;
    private LinearLayout notice_ll;
    // 点
    private CircleFlowIndicator mFlowIndicator;

    //线性布局
    private LinearLayout ll_asset1;
    private LinearLayout ll_bankcard1;
    private LinearLayout ll_invest1;
    private LinearLayout ll_message1;


    private View view;
    // 本地图片集合
    //GridView布局
    private int[] imgIds = new int[]{R.drawable.home_account, R.drawable.home_bankcard, R.drawable.home_assign,
            R.drawable.home_calculator, R.drawable.home_activity, R.drawable.home_friends, R.drawable.home_message, R.drawable.home_loan, R.drawable.home_invest,
            R.drawable.home_more};
    private String[] title = new String[]{"我的钱包", "一键绑定", "债权转让", "计算器", "热门活动", "我的好友", "我的消息", "我的钱包", "项目列表", "更多"};


    //GridView集合
    private ArrayList<GridViewItem> gridItemList;
    // 图片列表地址
    private ArrayList<String> imageUrlList;
    // 网络地址
    private ArrayList<String> linkUrlArray;
    //通知栏
    private ArrayList<String> titleList;
    //点
    private ViewFlipper notice_vf;
    //GridViewItemAdapter
    private GridViewItemAdapter imageAdapter;
    private Timer mTimer;
    private int mCurrPos;

    private List<HashMap<String, Object>> dataSourceList;
    private TimerTask task;


    /**
     * 设置Title
     */
    private void initTitleBar() {
        bar_rl_left.setVisibility(View.VISIBLE);
        bar_iv_left.setImageDrawable(ct.getResources().getDrawable(
                R.drawable.home_map));
        bar_tv_left.setVisibility(View.VISIBLE);
        bar_tv_left.setText("乌鲁木齐");
        bar_rl_right.setVisibility(View.VISIBLE);
        bar_iv_right.setImageDrawable(getResources().getDrawable(
                R.drawable.fragment_person));
        bar_tv_title.setText("杨凌农商银行");
        bar_rl_left.setOnClickListener(this);
        bar_rl_right.setOnClickListener(this);
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        mTimer = new Timer();
        imageUrlList = new ArrayList<String>();
        linkUrlArray = new ArrayList<String>();
        titleList = new ArrayList<String>();
        dataSourceList = new ArrayList<HashMap<String, Object>>();

        imageUrlList.add("http://img2.mtime.cn/up/1077/213077/92A83395-C199-4311-8D97-D59C12A3BAD1_500.jpg");
        imageUrlList.add("http://www.people.com.cn/mediafile/pic/20141012/24/12968181869281415500.jpg");

        linkUrlArray.add("https://github.com/jinsedeyuzhou");
        linkUrlArray.add("http://stackoverflow.com/users/5648747/jinsedeyuzhou");

        titleList.add("GitHub常见实例");
        titleList.add("常用的自定义控件");

        initBanner(imageUrlList);
        initRollNotice();

        ll_asset1.setOnClickListener(this);
        ll_bankcard1.setOnClickListener(this);
        ll_message1.setOnClickListener(this);
        ll_invest1.setOnClickListener(this);


        gridItemList = new ArrayList<GridViewItem>();   //不能全局初始化
        for (int i = 0; i < imgIds.length; i++) {
            GridViewItem gridItem = new GridViewItem(title[i], imgIds[i]);
            gridItemList.add(gridItem);
            HashMap<String, Object> itemHashMap = new HashMap<String, Object>();
            itemHashMap.put("item_image", imgIds[i]);
            itemHashMap.put("item_text", title[i]);
            dataSourceList.add(itemHashMap);
        }
        final DragAdapter mDragAdapter = new DragAdapter(ct, dataSourceList);

        gridview.setAdapter(mDragAdapter);

//        imageAdapter = new GridViewItemAdapter(ct, gridItemList, gridview);
//        gridview.setAdapter(imageAdapter);
        gridview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "position" + title[position], Toast.LENGTH_SHORT).show();
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(ct, CardFoldActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(ct, RefreshListViewActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(ct, LockActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(ct, JavaScriptActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(ct, GuideViewFlipper.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(ct, SpinnerSelectActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(ct, PersonCenterActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent = new Intent(ct, RePastActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        intent = new Intent(ct, BannerActivity.class);
                        startActivity(intent);
                        break;

                    case 9:
                        intent = new Intent(ct, IssueActivity.class);
                        startActivity(intent);

                    default:
                        break;
                }

            }
        });


    }


    @Override
    protected void bindEvent() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_home02;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10) {
            String cityName = data.getStringExtra("cityName");
            bar_tv_left.setText(cityName);
        }

    }

    @Override
    public void onStop() {
        mTimer.cancel();
        super.onStop();

    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void initView(View view) {
        bar_tv_title = (TextView) view.findViewById(R.id.bar_tv_title);
        // 设置左侧标题
        bar_tv_left = (TextView) view.findViewById(R.id.bar_tv_left);
        bar_rl_left = (RelativeLayout) view.findViewById(R.id.bar_rl_left);
        // 设置左侧图片
        bar_iv_left = (ImageView) view.findViewById(R.id.bar_iv_left);
        // 右侧bar
        bar_rl_right = (RelativeLayout) view.findViewById(R.id.bar_rl_right);
        // 右侧图片
        bar_iv_right = (ImageView) view.findViewById(R.id.bar_iv_right);
        main_notice = (FrameLayout) view.findViewById(R.id.main_notice);
        //gridview
        gridview = (DragGridView) view.findViewById(R.id.gridview);

        // bannerview
        mViewFlow = (ViewFlow) view.findViewById(R.id.viewflow);
        mFlowIndicator = (CircleFlowIndicator) view.findViewById(R.id.viewflowindic);

        //线性布局
        ll_asset1 = (LinearLayout) view.findViewById(R.id.ll_asset1);
        ll_bankcard1 = (LinearLayout) view.findViewById(R.id.ll_bankcard1);
        ll_invest1 = (LinearLayout) view.findViewById(R.id.ll_invest1);
        ll_message1 = (LinearLayout) view.findViewById(R.id.ll_message1);

        initTitleBar();
    }

    /**
     * 填充banner
     *
     * @param imageUrlList
     */
    private void initBanner(ArrayList<String> imageUrlList) {
        mViewFlow.setAdapter(new ImagePagerAdapter(getActivity(), imageUrlList,
                linkUrlArray, titleList).setInfiniteLoop(true));
        mViewFlow.setmSideBuffer(imageUrlList.size()); // 实际图片张数，
        // 我的ImageAdapter实际图片张数为3

        mViewFlow.setFlowIndicator(mFlowIndicator);
        mViewFlow.setTimeSpan(4500);
        mViewFlow.setSelection(imageUrlList.size() * 1000); // 设置初始位置
        mViewFlow.startAutoFlowTimer(); // 启动自动播放
    }

    @Override
    protected void processClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            // 左侧按钮
            case R.id.bar_rl_left:
                intent = new Intent(getActivity(), PositionActivity.class);
                startActivityForResult(intent, 100);
                break;
            // 右侧按钮
            case R.id.bar_rl_right:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_message1:
                intent = new Intent(ct, ProductActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_invest1:
                intent = new Intent(ct, VerticalViewActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_asset1:
                intent = new Intent(ct, VerticalViewPagerActivity.class);
                startActivity(intent);
                break;
            //scrollView 下拉刷新
            case R.id.ll_bankcard1:
                intent = new Intent(ct, HelpActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 通知栏
     */
    private void initRollNotice() {
        LinearLayout notice_parent_ll = (LinearLayout) getActivity().getLayoutInflater().inflate(
                R.layout.layout_notice, null);
        notice_ll = ((LinearLayout) notice_parent_ll
                .findViewById(R.id.homepage_notice_ll));
        notice_vf = ((ViewFlipper) notice_parent_ll
                .findViewById(R.id.homepage_notice_vf));
        main_notice.addView(notice_parent_ll);

        task = new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        moveNext();
                        LogUtil.d("Task", "下一个");
                    }
                });

            }
        };
        mTimer.schedule(task, 0, 4000);
    }

    private void moveNext() {
        setView(mCurrPos, mCurrPos + 1);
        notice_vf.setInAnimation(ct, R.anim.in_bottomtop);
        this.notice_vf.setOutAnimation(ct, R.anim.out_bottomtop);
        this.notice_vf.showNext();
    }

    private void setView(int curr, int next) {

        View noticeView = getActivity().getLayoutInflater().inflate(R.layout.notice_item,
                null);
        TextView notice_tv = (TextView) noticeView.findViewById(R.id.notice_tv);
        if ((curr < next) && (next > (titleList.size() - 1))) {
            next = 0;
        } else if ((curr > next) && (next < 0)) {
            next = titleList.size() - 1;
        }
        notice_tv.setText(titleList.get(next));
        notice_tv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Bundle bundle = new Bundle();
                bundle.putString("url", linkUrlArray.get(mCurrPos));
                bundle.putString("title", titleList.get(mCurrPos));
                Intent intent = new Intent(ct,
                        WebViewActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        if (notice_vf.getChildCount() > 1) {
            notice_vf.removeViewAt(0);
        }
        notice_vf.addView(noticeView, notice_vf.getChildCount());
        mCurrPos = next;
    }

}
