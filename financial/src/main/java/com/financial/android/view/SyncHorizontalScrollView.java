package com.financial.android.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

public class SyncHorizontalScrollView extends HorizontalScrollView {

	//传入整体布局
	private View view;
	//左阴影图片
	private ImageView leftImage;
	//右阴影图片
	private ImageView rightImage;
	// 屏幕宽度
	private int windowWitdh = 0;
	//父类的活动activity
	private Activity mContext;

	/**
	 * 传入父类布局中的资源文件
	 * @param view
	 * @param leftImage
	 * @param rightImage
	 * @param context
	 */
	public void setSomeParam(View view, ImageView leftImage,
			ImageView rightImage, Activity context) {
		this.mContext = context;
		this.view = view;
		this.leftImage = leftImage;
		this.rightImage = rightImage;
		DisplayMetrics dm = new DisplayMetrics();
		this.mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
		windowWitdh = dm.widthPixels;
	}

	public SyncHorizontalScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public SyncHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}


	/**
	 * 在拖动的时候执行
	 */
	public void showAndHideArrow() {
		if (!mContext.isFinishing() && view != null) {
			this.measure(0, 0);
			if (windowWitdh >= this.getMeasuredWidth()) {
				leftImage.setVisibility(View.GONE);
				rightImage.setVisibility(View.GONE);
			} else {
				if (this.getLeft() == 0) {
					leftImage.setVisibility(View.GONE);
					rightImage.setVisibility(View.VISIBLE);
				} else if (this.getRight() == this.getMeasuredWidth()
						- windowWitdh) {
					leftImage.setVisibility(View.VISIBLE);
					rightImage.setVisibility(View.GONE);
				} else {
					leftImage.setVisibility(View.VISIBLE);
					rightImage.setVisibility(View.VISIBLE);
				}
			}
		}
	}

	/**
	 * 判断左右阴影的显示隐藏效果
	 * @param l
	 * @param t
	 * @param oldl
	 * @param oldt
	 */
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (!mContext.isFinishing() && view != null && rightImage != null
				&& leftImage != null) {
			if (view.getWidth() <= windowWitdh) {
				leftImage.setVisibility(View.GONE);
				rightImage.setVisibility(View.GONE);
			} else {
				if (l == 0) {
					leftImage.setVisibility(View.GONE);
					rightImage.setVisibility(View.VISIBLE);
				} else if (view.getWidth() - l == windowWitdh) {
					leftImage.setVisibility(View.VISIBLE);
					rightImage.setVisibility(View.GONE);
				} else {
					leftImage.setVisibility(View.VISIBLE);
					rightImage.setVisibility(View.VISIBLE);
				}
			}
		}
	}
}
