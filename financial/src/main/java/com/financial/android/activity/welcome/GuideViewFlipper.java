package com.financial.android.activity.welcome;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.financial.android.R;

/**
 * Created by wyy on 2016/3/25.
 */
public class GuideViewFlipper extends Activity  {

    private ViewFlipper viewflipper;
    private Context ct;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_viewflipper);
        ct=this;
        initView();
        initData();


    }

    private void initView() {
        int[] images=new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d};
        viewflipper = (ViewFlipper) findViewById(R.id.viewflipper);
        ImageView imageView;
        for (int i=0;i<images.length;i++) {
            imageView=new ImageView(this);
            imageView.setImageResource(images[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewflipper.addView(imageView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }
        viewflipper.setAutoStart(true);
        //设置View播放的时间间隔，如3000（3秒）
        viewflipper.setFlipInterval(1000);
        if (viewflipper.isAutoStart() && !viewflipper.isFlipping()) {
            //开始自动播放
            viewflipper.startFlipping();
        }

    }

    private void initData() {
        gestureDetector = new GestureDetector(this,new MyGestureDetector());
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewflipper.stopFlipping(); // 点击事件后，停止自动播放
        viewflipper.setAutoStart(false);
        return gestureDetector.onTouchEvent(event); // 注册手势事件
    }

    class MyGestureDetector implements GestureDetector.OnGestureListener
    {

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e2.getX() - e1.getX() > 120) { // 从左向右滑动（左进右出）
                Animation rInAnim = AnimationUtils.loadAnimation(ct,
                        R.anim.push_right_in); // 向右滑动左侧进入的渐变效果（alpha 0.1 -> 1.0）
                Animation rOutAnim = AnimationUtils.loadAnimation(ct,
                        R.anim.push_right_out); // 向右滑动右侧滑出的渐变效果（alpha 1.0 -> 0.1）

                viewflipper.setInAnimation(rInAnim);
                viewflipper.setOutAnimation(rOutAnim);
                viewflipper.showPrevious();
                return true;
            } else if (e2.getX() - e1.getX() < -120) { // 从右向左滑动（右进左出）
                Animation lInAnim = AnimationUtils.loadAnimation(ct,
                        R.anim.push_left_in); // 向左滑动左侧进入的渐变效果（alpha 0.1 -> 1.0）
                Animation lOutAnim = AnimationUtils.loadAnimation(ct,
                        R.anim.push_left_out); // 向左滑动右侧滑出的渐变效果（alpha 1.0 -> 0.1）

                viewflipper.setInAnimation(lInAnim);
                viewflipper.setOutAnimation(lOutAnim);
                viewflipper.showNext();
                return true;
            }
            return true;
        }
    }
}
