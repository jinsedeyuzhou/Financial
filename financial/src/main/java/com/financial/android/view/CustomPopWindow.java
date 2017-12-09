package com.financial.android.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.financial.android.R;
import com.financial.android.utils.SystemUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 * 使用方式一、
 * cancelReasons = new ArrayList<>();
 cancelReasons.add("报价信息异常，不能出单");
 cancelReasons.add("客户无意愿出单");
 cancelReasons.add("想更换投保公司");
 cancelReasons.add("其它");
 CustomPopWindow customPop = new CustomPopWindow(this);

 customPop.setTitle("选择取消原因")
 .setData(cancelReasons)
 .setPopupWindow()
 .setOnItemClickListener(new AdapterView.OnItemClickListener() {
@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
cancelOrderRequest(cancelReasons.get(position));
customPop.dismiss();

}
}).openPopWindow();

 使用方式二、
 new CustomPopWindow().init(this).
 setTitle("选择取消原因")
 .setData(cancelReasons)
 .setPopupWindow()
 .setOnItemClickListener(new AdapterView.OnItemClickListener() {
@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
cancelOrderRequest(cancelReasons.get(position));

}
}).openPopWindow();
 */

public class CustomPopWindow extends PopupWindow {
    private Context mContext;
    private View mPopView;
    private GridView mGridViewPop;
    private List<String> strs = new ArrayList<>();
    private PopAdapter popAdapter;
    private AdapterView.OnItemClickListener onItemClickListeners;
    private TextView mTvCancel;
    private TextView mTvTitle;

    public CustomPopWindow() {

    }

    public CustomPopWindow(Context context) {
        super(context);
        mContext = context;
        init();
    }

    /**
     * 初始化布局
     */
    private void init() {
        mPopView = LayoutInflater.from(mContext).inflate(R.layout.layout_custom_pop, null);
        mGridViewPop = (GridView) mPopView.findViewById(R.id.gv_pop);
        mTvTitle = (TextView) mPopView.findViewById(R.id.tv_title);
        mTvCancel = (TextView) mPopView.findViewById(R.id.tv_cancel);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        popAdapter = new PopAdapter();
        mGridViewPop.setAdapter(popAdapter);
        mGridViewPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(strs.get(position), position);
                    dismiss();
                }
            }
        });


    }

    /**
     * 初始化布局
     */
    public CustomPopWindow init(Context context) {
        this.mContext = context;
        mPopView = LayoutInflater.from(mContext).inflate(R.layout.layout_custom_pop, null);
        mGridViewPop = (GridView) mPopView.findViewById(R.id.gv_pop);
        mTvTitle = (TextView) mPopView.findViewById(R.id.tv_title);
        mTvCancel = (TextView) mPopView.findViewById(R.id.tv_cancel);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        popAdapter = new PopAdapter();
        mGridViewPop.setAdapter(popAdapter);
        mGridViewPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(strs.get(position), position);
                    dismiss();
                }
            }
        });

        return this;

    }

    /**
     * 测试中，谨慎使用
     *
     * @param numColumns
     * @return
     */
    public CustomPopWindow setGridView(int numColumns) {
        mGridViewPop.setNumColumns(numColumns);
        return this;
    }

    /**
     * 初始化popwindow
     */
    public CustomPopWindow setPopupWindow() {
        this.setContentView(mPopView);// 设置View
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        //点击外部消失
//        this.setOutsideTouchable(true);
        this.setClippingEnabled(false);
        this.setAnimationStyle(R.style.AnimBottom);// 设置动画
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x88000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);// 设置背景透明
        return this;
    }



    /**
     * 点击外部消失，由于弹出窗口时全屏，此设置无效 需要改变布局
     *
     * @return
     */
    public CustomPopWindow setOutsideCancel(boolean isout) {
        this.setOutsideTouchable(isout);
        return this;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public CustomPopWindow setTitle(String title) {
        if (title != null) {
            mTvTitle.setText(title);
            mTvTitle.setVisibility(View.VISIBLE);
        }else
        {
            mTvTitle.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 初始化数据
     *
     * @param strs
     * @return
     */
    public CustomPopWindow setData(List<String> strs) {
        if (strs != null)
            this.strs = strs;

        popAdapter.notifyDataSetChanged();
        return this;
    }

   /**
     * 初始化数据
     *
     * @param strs
     * @return
     */
    public CustomPopWindow setData(List<String> strs,boolean istrcrol) {
        if (strs != null)
            this.strs = strs;
        if (istrcrol&&strs.size()>3)
        {
            ViewGroup.LayoutParams params = mGridViewPop.getLayoutParams();
            // 设置高度
            params.height = SystemUtils.getScreenHeight(mContext)/4;

            // 设置margin
            // 设置参数
            mGridViewPop.setLayoutParams(params);
        }
        popAdapter.notifyDataSetChanged();
        return this;
    }


    /**
     * 按钮的监听
     *
     * @param
     */
    public CustomPopWindow openPopWindow() {
        //从底部显示
        showAtLocation(mPopView, Gravity.BOTTOM, 0, 0);
        return this;
    }

    /**
     * 设置点击事件
     *
     * @param onItemClickListeners
     * @return
     */
    public CustomPopWindow setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListeners) {
        if (onItemClickListeners != null) {
            mGridViewPop.setOnItemClickListener(onItemClickListeners);
        }
        return this;
    }

    class PopAdapter extends BaseAdapter {

        private TextView mTvButton;

        @Override
        public int getCount() {
            return strs.size();
        }

        @Override
        public Object getItem(int position) {
            return strs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.item_gridview, null);
                mTvButton = (TextView) convertView.findViewById(R.id.tv_custom_popwindow);
                convertView.setTag(mTvButton);
            } else {
                mTvButton = (TextView) convertView.getTag();
            }
            mTvButton.setText(strs.get(position));
            return convertView;
        }
    }

    private OnItemClickListener onItemClickListener;

    public CustomPopWindow setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public interface OnItemClickListener {
        void onItemClick(String title, int position);
    }
}
