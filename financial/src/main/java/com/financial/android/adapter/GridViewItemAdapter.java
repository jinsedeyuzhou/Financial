package com.financial.android.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.base.FXBaseAdapter;
import com.financial.android.bean.GridViewItem;


/**
 * fragment主页 gridview适配
 * 
 * @author wyy
 * 
 */
public class GridViewItemAdapter extends FXBaseAdapter<GridViewItem, GridView> {
	private List<GridViewItem> gridItemList;
	public GridViewItemAdapter(Context context, List<GridViewItem> gridItemList,
			GridView view) {
		super(context, gridItemList, view);
		this.gridItemList=gridItemList;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		GridViewItem gridItem=gridItemList.get(position);
		
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.gridview_item, null);
			holder = new ViewHolder(gridItem);
			holder.img = (ImageView) convertView
					.findViewById(R.id.iv_home_gridview);
			holder.tv_name = (TextView) convertView
					.findViewById(R.id.tv_gridview_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
//			convertView.getTag();
		}
		holder.img.setImageResource(gridItem.getImgId());
		holder.tv_name.setText(gridItem.getTitle());

		return convertView;
	}

	class ViewHolder {
		ImageView img;
		TextView tv_name;
		private GridViewItem gridItem;
		
		public ViewHolder(GridViewItem gridItem) {
			this.gridItem = gridItem;
		}
		
	}
}
