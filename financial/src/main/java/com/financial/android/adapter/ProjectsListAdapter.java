package com.financial.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.base.FXBaseAdapter;
import com.financial.android.bean.ProductInfo;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;


/**
 * Created by wyy on 2016/1/26.
 */
public /**
 * 原生ListView适配
 * @author wyy
 *
 */
class ProjectsListAdapter extends FXBaseAdapter<ProductInfo, ListView> {
//    private Context context;
    private List<ProductInfo> productsList;

    public ProjectsListAdapter(Context context, List<ProductInfo> list,
                               ListView view) {
        super(context, list, view);
        this.productsList=list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ProductInfo product = productsList.get(position);
        if (convertView == null) {

            convertView = View.inflate(context, R.layout.product_item,
                    null);
            holder = new ViewHolder(product);
            ViewUtils.inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mProcutName.setText(product.getName());
        holder.mProDescription.setText(product.getDescription());

        return convertView;
    }

    class ViewHolder {
        @ViewInject(R.id.tv_product_name)
        TextView mProcutName;
        @ViewInject(R.id.tv_product_description)
        TextView mProDescription;

        private ProductInfo card;
        public ViewHolder(ProductInfo card) {
            this.card = card;
        }
    }

}
