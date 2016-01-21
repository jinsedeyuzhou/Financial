package com.financial.android.base;

import java.util.List;


import android.content.Context;
import android.widget.BaseAdapter;

/**
 * Adapter基类
 * 
 * @author lxj
 * @version 1.0
 * @param <T>
 * @param <Q>
 */
public abstract class FXBaseAdapter<T, Q> extends BaseAdapter {

	public Context context;
	public List<T> list;//
	public Q view; // 这里不一定是ListView,比如GridView,CustomListView


	public FXBaseAdapter(Context context, List<T> list, Q view) {
		this.context = context;
		this.list = list;
		this.view = view;
	}

	public FXBaseAdapter(Context context, List<T> list) {
		this.context = context;
		this.list = list;
		
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


}
