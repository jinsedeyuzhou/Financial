package com.financial.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.base.FXBaseAdapter;
import com.financial.android.bean.Product;
import com.financial.android.utils.StringUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wyy on 2016/1/26.
 */
public class ProjectsListAdapter extends FXBaseAdapter<Product, ListView> {
    private List<Product> products;

    public ProjectsListAdapter(Context context, ArrayList<Product> list,
                               ListView view) {
        super(context, list, view);
        this.products = list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Product product = products.get(position);
        if (convertView == null) {

            convertView = View.inflate(context, R.layout.item_product_direct,
                    null);
            holder = new ViewHolder(product);
            ViewUtils.inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_product_itemlist.setText(product.getItem_prefix() + "  " + product.getItem_name());
        if ("01".equals(product.getSpecial_flag())) {
            holder.tv_product_zhuan.setVisibility(View.VISIBLE);
        } else {
            holder.tv_product_zhuan.setVisibility(View.GONE);
        }
        holder.tv_project_yield.setText(product.getInvester_year_rate() + "%");
        holder.tv_product_period.setText(product.getFinance_period() + "天");
        if ("01".equals(product.getStatus())) {
            holder.tv_product_status.setText("预热");
            holder.tv_product_status.setBackgroundResource(R.drawable.shap_point_orange);
        } else if ("02".equals(product.getStatus())) {
            holder.tv_product_status.setText("购买");
            holder.tv_product_status.setBackgroundResource(R.drawable.shap_point_red);
        } else if ("03".equals(product.getStatus())) {
            holder.tv_product_status.setText("售罄");
            holder.tv_product_status.setBackgroundResource(R.drawable.shap_point_grey);
        } else if ("04".equals(product.getStatus())) {
            holder.tv_product_status.setText("完成");
            holder.tv_product_status.setBackgroundResource(R.drawable.shap_point_gainsboro);
        }
        holder.pb_product_progress.setProgress(Integer.parseInt(product.getProgress()));
        if ("01".equals(product.getAssign())) {
            holder.tv_projects_assign.setVisibility(View.VISIBLE);
        } else {
            holder.tv_projects_assign.setVisibility(View.GONE);
        }

        holder.tv_product_minpayment.setText(product.getMinInvest() + "元起投");
        holder.tv_product_suplus.setText("剩" + StringUtil.formatPercent(product.getSuplus() / 10000) + "万/" + StringUtil.formatPercent7(product.getMax_finance_money() / 10000) + "万");
        holder.pb_product_progress.setProgress(Integer.valueOf(product.getProgress()));

        return convertView;
    }

    class ViewHolder {
        //项目名称和编号
        @ViewInject(R.id.tv_product_itemlist)
        TextView tv_product_itemlist;
        //年华收益率
        @ViewInject(R.id.tv_project_yield)
        TextView tv_project_yield;
        //是否专
        @ViewInject(R.id.tv_product_zhuan)
        TextView tv_product_zhuan;
        //投资期限
        @ViewInject(R.id.tv_product_period)
        TextView tv_product_period;
        //项目状态
        @ViewInject(R.id.tv_product_status)
        TextView tv_product_status;
        //最低限额
        @ViewInject(R.id.tv_product_minpayment)
        TextView tv_product_minpayment;
        //剩余金额
        @ViewInject(R.id.tv_product_suplus)
        TextView tv_product_suplus;
        //进度
        @ViewInject(R.id.pb_product_progress)
        ProgressBar pb_product_progress;
        //是否可以转让
        @ViewInject(R.id.tv_projects_assign)
        TextView tv_projects_assign;

        private Product card;
        public ViewHolder(Product card) {
            this.card = card;
        }
    }

}
