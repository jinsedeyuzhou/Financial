package com.financial.android.base;

import java.util.List;

import com.financial.android.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Banner viewpager适配
 * 网络获取图片
 * @author wyy
 * 
 */
public class FXPagerAdapter extends PagerAdapter {
	private Context ct;
	private LayoutInflater mInflater;
	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	// 图片列表地址
	private List<String> imageUrlList;
	// 网络地址 点击进入
	private List<String> linkUrlArray;
//	private int currentPosition;

	public FXPagerAdapter(Context ct, List<String> imageUrlList,
			List<String> linkUrlArray) {
		super();
		mInflater = LayoutInflater.from(ct);
		this.ct=ct;
		this.imageUrlList=imageUrlList;
		this.linkUrlArray=linkUrlArray;
		// 初始化imageLoader 否则会报错
				imageLoader = ImageLoader.getInstance();
				imageLoader.init(ImageLoaderConfiguration.createDefault(ct));
				options = new DisplayImageOptions.Builder()
						.showImageOnLoading(R.drawable.ic_launcher) // 设置图片下载期间显示的图片
						.showImageForEmptyUri(R.drawable.meinv) // 设置图片Uri为空或是错误的时候显示的图片
						.showImageOnFail(R.drawable.meinv) // 设置图片加载或解码过程中发生错误显示的图片
						.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
						.cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
						.build();
	}

	/**
	 * 获得当前界面数
	 */
	@Override
	public int getCount() {
		return imageUrlList.size();
	}

	/**
	 * 判断是否由对象生成界面
	 */
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return (view == object);
	}

	/**
	 * 销毁position位置的界面
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
	}

	/**
	 * 初始化position位置的界面
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = mInflater.inflate(R.layout.banner_item, container, false);
           ImageView imageView = (ImageView) view.findViewById(R.id.image);
           imageLoader.displayImage((String)imageUrlList.get(position),
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
		
	}

}
