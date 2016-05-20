package com.financial.android.activity.home;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.financial.android.R;
import com.financial.android.activity.account.SettingActivity;
import com.financial.android.activity.other.WebViewActivity;
import com.financial.android.activity.projects.ProductActivity;
import com.financial.android.activity.test.VerticalViewActivity;
import com.financial.android.activity.test.VerticalViewPagerActivity;
import com.financial.android.activity.welcome.LockSetupActivity;
import com.financial.android.adapter.GridViewItemAdapter;
import com.financial.android.base.BaseFragment;
import com.financial.android.bean.GridViewItem;
import com.financial.android.view.RefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 不带滚动动画的也就是原点不滑动的效果
 */
public class HomeFragment extends BaseFragment {

    @ViewInject(R.id.bar_tv_title)
    private TextView bar_tv_title;
    @ViewInject(R.id.bar_rl_visible)
    private RelativeLayout bar_rl_visible;
    @ViewInject(R.id.viewpager)
    private ViewPager viewpager;
    // 设置左侧标题
    @ViewInject(R.id.bar_tv_left)
    private TextView bar_tv_left;
    @ViewInject(R.id.bar_rl_left)
    private RelativeLayout bar_rl_left;
    // 设置左侧图片
    @ViewInject(R.id.bar_iv_left)
    private ImageView bar_iv_left;
    // 右侧bar
    @ViewInject(R.id.bar_rl_right)
    private RelativeLayout bar_rl_right;
    // 右侧图片
    @ViewInject(R.id.bar_iv_right)
    private ImageView bar_iv_right;
    @ViewInject(R.id.main_notice)
    private FrameLayout main_notice;
    @ViewInject(R.id.gridview)
    private GridView gridview;


    private View view;
    // 本地图片集合
    //GridView布局
    private int[] imgIds = new int[]{R.drawable.home_account, R.drawable.home_bankcard, R.drawable.home_assign,
            R.drawable.message, R.drawable.home_activity, R.drawable.home_friends, R.drawable.home_message,
            R.drawable.home_more};
    private String[] title = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
    private ArrayList<GridViewItem> gridItemList;


    // 图片列表地址
    private ArrayList<String> imageUrlList;
    // 网络地址
    private ArrayList<String> linkUrlArray;
    private ArrayList<View> dots;

    private LinearLayout notice_ll;
    private ViewFlipper notice_vf;
    //通知栏
    private ArrayList<String> titleList;
    private GridViewItemAdapter imageAdapter;
    private BannerPagerAdapter bannerAdapter;
    private int currentItem; // 当前页面

    private final int FAKE_BANNER_SIZE = 100;
    private final int DEFAULT_BANNER_SIZE = 2;
    //ImageLoader 加载图片
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private ScheduledExecutorService scheduledExecutorService;

    private int mCurrPos;

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {


            if (currentItem == FAKE_BANNER_SIZE - 1) {
                viewpager.setCurrentItem(DEFAULT_BANNER_SIZE - 1, false);
            } else {
                viewpager.setCurrentItem(currentItem);
            }
            moveNext();
        }

        ;

    };

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ViewUtils.inject(this, view);
        initTitleBar();
        return view;
    }

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
        imageUrlList = new ArrayList<String>();
        linkUrlArray = new ArrayList<String>();

        imageUrlList
                .add("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg");
        imageUrlList
                .add("http://g.hiphotos.baidu.com/image/pic/item/6159252dd42a2834da6660c459b5c9ea14cebf39.jpg");

        linkUrlArray
                .add("http://blog.csdn.net/finddreams/article/details/44648121");
        linkUrlArray
                .add("http://blog.csdn.net/finddreams/article/details/44619589");

        // 显示的点
        dots = new ArrayList<View>();
        dots.add(view.findViewById(R.id.dot_1));
        dots.add(view.findViewById(R.id.dot_2));
        bannerAdapter = new BannerPagerAdapter(imageUrlList, linkUrlArray);
        viewpager.setAdapter(bannerAdapter);
        viewpager.setOnPageChangeListener(bannerAdapter);
        titleList = new ArrayList<String>();
        titleList.add("常见Android进阶笔试题");
        titleList.add("GridView之仿支付宝钱包首页");
        initRollNotice();
        gridItemList = new ArrayList<GridViewItem>();//不能全局初始化
        for (int i = 0; i < imgIds.length; i++) {
            GridViewItem gridItem = new GridViewItem(title[i], imgIds[i]);
            gridItemList.add(gridItem);
        }
        imageAdapter = new GridViewItemAdapter(ct, gridItemList, gridview);
        gridview.setAdapter(imageAdapter);
        gridview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "position" + position, Toast.LENGTH_SHORT).show();
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(ct, LockSetupActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(ct, RefreshListViewActivity.class);
                        startActivity(intent);
                        break;

                    default:
                        break;
                }

            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        // 用一个定时器 来完成图片切换
        // Timer 与 ScheduledExecutorService 实现定时器的效果
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 通过定时器 来完成 每2秒钟切换一个图片
        // 经过指定的时间后，执行所指定的任务
        // scheduleAtFixedRate(command, initialDelay, period, unit)
        // command 所要执行的任务
        // initialDelay 第一次启动时 延迟启动时间
        // period 每间隔多次时间来重新启动任务
        // unit 时间单位
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 1,
                5, TimeUnit.SECONDS);
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
            case R.id.ll_bankcard1:
                intent = new Intent(ct, HelpActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStop() {
        super.onStop();
        scheduledExecutorService.shutdown();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    class BannerPagerAdapter extends PagerAdapter implements OnPageChangeListener {
        private LayoutInflater mInflater;
        private List<String> imageUrlList;
        private List<String> linkUrlArray;

        public BannerPagerAdapter(List<String> imageUrlList, List<String> linkUrlArray) {
            mInflater = LayoutInflater.from(ct);
            this.imageUrlList = imageUrlList;
            this.linkUrlArray = linkUrlArray;
            // 初始化imageLoader 否则会报错
            imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(ct));
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.meinv) // 设置图片下载期间显示的图片
                    .showImageForEmptyUri(R.drawable.meinv) // 设置图片Uri为空或是错误的时候显示的图片
                    .showImageOnFail(R.drawable.meinv) // 设置图片加载或解码过程中发生错误显示的图片
                    .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                    .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                    .build();
        }

        @Override
        public int getCount() {
            return FAKE_BANNER_SIZE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= DEFAULT_BANNER_SIZE;
            View view = mInflater.inflate(R.layout.banner_item, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            imageLoader.displayImage((String) imageUrlList.get(position),
                    imageView, options);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            container.addView(view);
            return view;

        }

        @Override
        public void finishUpdate(ViewGroup container) {
            int position = viewpager.getCurrentItem();
            if (position == 0) {
                position = DEFAULT_BANNER_SIZE;
                viewpager.setCurrentItem(position, false);
            } else if (position == FAKE_BANNER_SIZE - 1) {
                position = DEFAULT_BANNER_SIZE - 1;
                viewpager.setCurrentItem(position, false);
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            setIndicator(currentItem);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    }

    private void setIndicator(int position) {
        position %= DEFAULT_BANNER_SIZE;
        for (View indicator : dots) {
            indicator.setBackgroundResource(R.drawable.dot_normal_shape);
        }
        dots.get(position).setBackgroundResource(R.drawable.dot_focused_shape);
    }

    private class ViewPagerTask implements Runnable {

        @Override
        public void run() {
            synchronized (viewpager) {
                currentItem++;
//				 if (currentItem == FAKE_BANNER_SIZE - 1) {
//			            viewpager.setCurrentItem(DEFAULT_BANNER_SIZE - 1, false);
//			        } else {
//			        	viewpager.setCurrentItem(currentItem);
//			        }
                // 更新界面
//				 handler.sendEmptyMessage(0);
                handler.obtainMessage().sendToTarget();

            }
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();


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
