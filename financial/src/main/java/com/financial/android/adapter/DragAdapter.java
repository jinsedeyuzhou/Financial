package com.financial.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.financial.android.R;
import com.financial.android.bean.GridViewItem;
import com.financial.android.view.DragGridBaseAdapter;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author xiaanming
 * @blog http://blog.csdn.net/xiaanming
 */
public class DragAdapter extends BaseAdapter implements DragGridBaseAdapter {
    private List<HashMap<String, Object>> list;
    private LayoutInflater mInflater;
    private int mHidePosition = -1;
    private Context context;

    public DragAdapter(Context context, List<HashMap<String, Object>> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        convertView = View.inflate(context, R.layout.gridview_item, null);
        holder = new ViewHolder();
        ViewUtils.inject(holder, convertView);
        holder.img.setImageResource((Integer) list.get(position).get("item_image"));
        holder.tv_name.setText((CharSequence) list.get(position).get("item_text"));

//		ImageView mImageView = (ImageView) convertView.findViewById(R.id.iv_home_gridview);
//		TextView mTextView = (TextView) convertView.findViewById(R.id.tv_gridview_name);
//
//		mImageView.setImageResource((Integer) list.get(position).get("item_image"));
//		mTextView.setText((CharSequence) list.get(position).get("item_text"));

        if (position == mHidePosition) {
            convertView.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    class ViewHolder {
        @ViewInject(R.id.iv_home_gridview)
        ImageView img;
        @ViewInject(R.id.tv_gridview_name)
        TextView tv_name;


    }

    @Override
    public void reorderItems(int oldPosition, int newPosition) {
        HashMap<String, Object> temp = list.get(oldPosition);
        if (oldPosition < newPosition) {
            for (int i = oldPosition; i < newPosition; i++) {
                Collections.swap(list, i, i + 1);
            }
        } else if (oldPosition > newPosition) {
            for (int i = oldPosition; i > newPosition; i--) {
                Collections.swap(list, i, i - 1);
            }
        }

        list.set(newPosition, temp);
    }

    @Override
    public void setHideItem(int hidePosition) {
        this.mHidePosition = hidePosition;
        notifyDataSetChanged();
    }


}
