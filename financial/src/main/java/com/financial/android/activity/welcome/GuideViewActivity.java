package com.financial.android.activity.welcome;

import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.financial.android.R;
import com.financial.android.activity.main.HomeActivity;
import com.financial.android.base.BaseActivity;
import com.financial.android.utils.SharePrefUtil;
import com.youth.banner.transformer.CubeOutTransformer;

public class GuideViewActivity extends BaseActivity {

	private ViewPager mViewPager;
	private GuideViewPagerAdapter mGuideViewPagerAdapter;
	private ArrayList<View> views;
	private static final int[] pics = { R.drawable.guide_one,
			R.drawable.guide_two, R.drawable.guide_three };

//	private ImageView[] points;
	private int currentIndex;
	private int count;
	private Button mGuide;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide);
	}

	@Override
	public void initView() {

		views = new ArrayList<View>();
		mViewPager = (ViewPager) findViewById(R.id.guide_viewpagers);
		mGuideViewPagerAdapter = new GuideViewPagerAdapter(views,pics);
		mGuide = (Button) findViewById(R.id.btn_guide);
		mGuide.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ct, HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
		mViewPager.setPageTransformer(true, new CubeOutTransformer());
	}

	@Override
	public void initData() {
		SharePrefUtil.saveBoolean(getApplicationContext(),
				SharePrefUtil.KEY.FIRST_LAUNCH, false);
//		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
//				LinearLayout.LayoutParams.MATCH_PARENT,
//				LinearLayout.LayoutParams.MATCH_PARENT);

		// 初始化引导图片列表
//		for (int i = 0; i < pics.length; i++) {
//			ImageView iv = new ImageView(this);
//			iv.setLayoutParams(mParams);
//			// 防止图片不能填满屏幕
//			iv.setScaleType(ScaleType.FIT_XY);
//			// 加载图片资源
//			iv.setImageResource(pics[i]);
//			views.add(iv);
//		}
		// 设置数据
		mViewPager.setAdapter(mGuideViewPagerAdapter);
		// 设置监听
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			/* (non-Javadoc)
			 * 新的页面被选中时调用
			 */
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				// 设置底部小点选中状态
//		        setCurDot(position);
			}

			/* (non-Javadoc)
			 * 当前页面滑动时调用
			 */
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				//点击按钮跳转
				if(position == 2)
				{
					mGuide.setVisibility(View.VISIBLE);
					
				}
				else
				{
					mGuide.setVisibility(View.GONE);
				}
				//点击跳转
//				if(position == 2)
//				{
//					
//					views.get(position).setOnClickListener(new OnClickListener() {
//						
//						@Override
//						public void onClick(View v) {
//							// TODO Auto-generated method stub
//							Intent intent = new Intent(ct, HomeActivity.class);
//							startActivity(intent);
//							finish();
//						}
//					});
//				}
				//滑动跳转
//				if (position == 2 && positionOffset <= 0 && positionOffsetPixels <= 0){
//					count++;
//					if (count > 6){
//						Intent intent = new Intent(ct, HomeActivity.class);
//						startActivity(intent);
//						finish();
//					}
//				}

			}

			/* (non-Javadoc)
			 * 滑动状态改变时调用
			 */
			@Override
			public void onPageScrollStateChanged(int state) {

			}
			
			
		});
		// 初始化底部小点
//		 initPoint();
	}

	@Override
	protected void bindEvent() {

	}

	/**
	 * 初始化底部小点
	 */
//	private void initPoint() {
////		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_container_point);
//		points = new ImageView[pics.length];
//
//		// 循环取得小点图片
//		for (int i = 0; i < pics.length; i++) {
//			// 得到一个LinearLayout下面的每一个子元素
////			points[i] = (ImageView) linearLayout.getChildAt(i);
//			// 默认都设为灰色
//			points[i].setEnabled(true);
//			// 给每个小点设置监听
//			points[i].setOnClickListener(this);
//			// 设置位置tag，方便取出与当前位置对应
//			points[i].setTag(i);
//		}

//		// 设置当面默认的位置
//		currentIndex = 0;
//		// 设置为白色，即选中状态
//		points[currentIndex].setEnabled(false);
//	}

	@Override
	public void processClick(View v) {
		int position=(Integer) v.getTag();
		setCurView(position);
//		setCurDot(position);
	}
	/**
	 * 设置当前的页面的位置
	 * @param position
	 */
	private void setCurView(int position)
	{
		 if (position < 0 || position >= pics.length) {
	            return;
	        }
	        mViewPager.setCurrentItem(position);
	}
	/**
	 * 设置当前的小点的位置
	 * @param position
	 */
//	private void setCurDot(int position)
//	{
//		if (position < 0 || position > pics.length - 1 || currentIndex == position) {
//            return;
//        }
//        points[position].setEnabled(false);
//        points[currentIndex].setEnabled(true);
//
//        currentIndex = position;
//	}
	/**
	 * GuideViewPagerAdapter适配
	 * 
	 * @author wyy
	 * 
	 */
	public class GuideViewPagerAdapter extends PagerAdapter {
		// 界面列表
		private int[] images;
		private ArrayList<View> views;
		private Bitmap bm;

		public GuideViewPagerAdapter(ArrayList<View> views,int[] images) {
			super();
			this.views = views;
			this.images=images;
		}

		/*
		 * 获取当前界面数
		 */
		@Override
		public int getCount() {
//			if (views != null) {
//				return views.size();
//			}
//			return 0;
			return images.length;
		}

		/*
		 * 判断是否由对象生成界面 
		 * 
		 */
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return (view == object);
		}

		/*
		 *  销毁position位置的界面
		 * 
		 * @see
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
//			((ViewPager) container).removeView(views.get(position));
			((ViewPager) container).removeView((View)object);
			
		}

		/*
		 * 初始化position位置的界面
		 * 将初始化控件位置写入到适配中，可以避免内存异常，这样限制初始化ImageView的数量
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView iv = new ImageView(ct);
			iv.setScaleType(ScaleType.CENTER_CROP);
			// 设置bitmap加载图片
			BitmapFactory.Options opt = new BitmapFactory.Options();
			opt.inPreferredConfig = Bitmap.Config.RGB_565;
			opt.inPurgeable = true;
			opt.inInputShareable = true;
			InputStream is = ct.getResources()
					.openRawResource(images[position]);
			bm = BitmapFactory.decodeStream(is, null, opt);
			BitmapDrawable bd = new BitmapDrawable(ct.getResources(), bm);
			iv.setImageDrawable(bd);
			container.addView(iv);
			if (position == 2) {
				iv.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(ct, HomeActivity.class);
						ct.startActivity(intent);
						((Activity) ct).finish();
					}
				});
			}
			return iv;
			
//			((ViewPager) container).addView(views.get(position),0);
//			return views.get(position);
		}

	}
}
