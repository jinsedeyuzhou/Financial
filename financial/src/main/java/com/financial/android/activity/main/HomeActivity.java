package com.financial.android.activity.main;

import java.util.ArrayList;

import com.financial.android.R;
import com.financial.android.activity.account.AccountFragment;
import com.financial.android.activity.home.HomeFragment;
import com.financial.android.activity.home.HomeFragment02;
import com.financial.android.activity.projects.ProjectsFragment;
import com.financial.android.base.BaseActivity;
import com.financial.android.base.FXFragmentPagerAdapter;
import com.financial.android.restful.config.service.MyPushIntentService;
import com.financial.android.view.CustomViewPager;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class HomeActivity extends BaseActivity {

	private CustomViewPager viewPager;
	private RadioGroup radioGroup;
//	private RadioButton mHomeButton;
//	private RadioButton mProjectsButton;
//	private RadioButton mAccountButton;
	private ArrayList<Fragment> fragmentLists;
	// 设置Titlebar可见性
	//	private RelativeLayout bar_rl_visible;
	// 标题
	//	protected TextView titleTv;
	// 当前选中fragment
	private int curIndex;
	// 定义一个变量，来标识是否退出
	private static boolean isExit = false;

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit = false;
		}
	};
	private PushAgent mPushAgent;
	private ServiceRecevier serviceRecevier;

//	private void initTitleBar() {
//		titleTv = (TextView) findViewById(R.id.bar_tv_title);
//		titleTv.setText("杨凌农商银行");
//		bar_rl_visible = (RelativeLayout) findViewById(R.id.bar_rl_visible);
//	}

	/*
	 * 初始化界面
	 */
	@Override
	public void initView() {
		setContentView(R.layout.activity_home);
		mPushAgent = PushAgent.getInstance(this);
		PushAgent.getInstance(getApplicationContext()).onAppStart();
		//开启推送并设置注册的回调处理
		mPushAgent.enable(new IUmengRegisterCallback() {

			@Override
			public void onRegistered(String registrationId) {
				//onRegistered方法的参数registrationId即是device_token

				System.out.println("registrationId:" + registrationId);
			}
		});
		String device_token = UmengRegistrar.getRegistrationId(this);
		System.out.println("device_token:" + device_token);
		serviceRecevier = new ServiceRecevier();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("com.study.shabi");
		registerReceiver(serviceRecevier, intentFilter);
		mPushAgent.setPushIntentServiceClass(MyPushIntentService.class);

//		initTitleBar();
		viewPager = (CustomViewPager) findViewById(R.id.home_viewpager);
		radioGroup = (RadioGroup) findViewById(R.id.main_radio);
//		mHomeButton = (RadioButton) findViewById(R.id.rb_home);
//		mProjectsButton = (RadioButton) findViewById(R.id.rb_projects);
//		mAccountButton = (RadioButton) findViewById(R.id.rb_account);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_home:
					viewPager.setCurrentItem(0, false);
//					viewPager.setClickable(false);
//					titleTv.setText("杨凌农商银行");
					curIndex = 0;
					break;
				case R.id.rb_projects:
					viewPager.setCurrentItem(1, false);
//					viewPager.setClickable(false);
//					titleTv.setText("投资列表");
					curIndex = 1;
					break;
				case R.id.rb_account:
					viewPager.setCurrentItem(2, false);
//					viewPager.setClickable(false);
//					titleTv.setText("我的账户");
					curIndex = 2;
					break;

				default:
					break;
				}
			}
		});
		
		initViewPager();
		
	}

	/**
	 * 初始化viewPager
	 */
	private void initViewPager() {
		HomeFragment02 homeFragment = new HomeFragment02();
//		HomeFragment homeFragment = new HomeFragment();
		ProjectsFragment projectsFragment = new ProjectsFragment();
		AccountFragment accountFragment = new AccountFragment();
		fragmentLists = new ArrayList<Fragment>();
		fragmentLists.add(homeFragment);
		fragmentLists.add(projectsFragment);
		fragmentLists.add(accountFragment);
		// viewPager 设置适配器
//		viewPager.setOffscreenPageLimit(4); //设置缓存界面,默认为1，不可小于1
		viewPager.setAdapter(new FXFragmentPagerAdapter(getSupportFragmentManager(), fragmentLists));
		// 设置当前页
		viewPager.setCurrentItem(0);
		// viewpager页面切换监听
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					radioGroup.check(R.id.rb_home);
					break;
				case 1:
					radioGroup.check(R.id.rb_projects);
					break;
				case 2:
					radioGroup.check(R.id.rb_account);
					break;

				default:
//					radioGroup.check(R.id.rb_home);
					break;
				}

			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

	}

	/**
	 * 按两次退出
	 */
	private void exit() {
		if (!isExit) {
			isExit = true;

			Toast.makeText(getApplicationContext(), "再按一次退出程序",
					Toast.LENGTH_SHORT).show();
			// 利用handler延迟发送更改状态信息
			mHandler.sendEmptyMessageDelayed(0, 2000);
		} else {
			finish();
			System.exit(0);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void initData() {

	}

	@Override
	public void processClick(View v) {


	}


	public class ServiceRecevier extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent) {
			String action=intent.getAction();

			showUpdateDialog1();
			System.out.println("cust");
			if ("com.study.android".equals(action)) {

			}
		}
	}

	public void showUpdateDialog1() {
		AlertDialog.Builder bulider = new AlertDialog.Builder(this);
		bulider.setTitle("发现新版本");
		bulider.setMessage("asdfkjdajf");
		bulider.setPositiveButton("升级", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		bulider.setNegativeButton("忽略", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		bulider.setOnCancelListener(new DialogInterface.OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
			}
		});
		bulider.show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(serviceRecevier);
	}
}
