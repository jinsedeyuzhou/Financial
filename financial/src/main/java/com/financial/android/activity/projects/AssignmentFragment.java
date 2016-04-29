package com.financial.android.activity.projects;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.financial.android.R;
import com.financial.android.adapter.ProjectsListAdapter;
import com.financial.android.base.BaseFragment;
import com.financial.android.bean.ProductInfo;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyy on 2016/1/26.
 * 直投项目
 */
public class AssignmentFragment extends BaseFragment {

    @ViewInject(R.id.pull_refresh_list_assign)
    private PullToRefreshListView pull_refresh_list_assign;

    private View view;
    //数据源
    private ArrayList<ProductInfo> productsList;
    private ProjectsListAdapter plAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.frag_assignment_projects,container,false);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        productsList=new ArrayList<ProductInfo>();
        ProductInfo pi1=new ProductInfo("lisi","30");
        ProductInfo pi2=new ProductInfo("傻逼","50");
        productsList.add(pi1);
        productsList.add(pi2);

        pull_refresh_list_assign.setOnRefreshListener(new OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new GetDataTask().execute();
            }
        });

        pull_refresh_list_assign.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                showToast("End of List!");
            }
        });
        pull_refresh_list_assign.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(ct,ProductActivity.class);
                startActivity(intent);
            }
        });

        ListView listViewAssign = pull_refresh_list_assign.getRefreshableView();
        plAdapter = new ProjectsListAdapter(ct, productsList, listViewAssign);
        listViewAssign.setAdapter(plAdapter);

    }


    private class GetDataTask extends AsyncTask<Void, Void, List<ProductInfo>>
    {

        @Override
        protected List<ProductInfo> doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return productsList;
        }

        @Override
        protected void onPostExecute(List<ProductInfo> result) {

            //可以将productsList数据清除，重新加载请求后的数据
            productsList.clear();
            ProductInfo info2=new ProductInfo("天才","我就是个傻逼");
            productsList.add(info2);

            plAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            pull_refresh_list_assign.onRefreshComplete();

            super.onPostExecute(result);
        }
    }
    @Override
    protected void processClick(View v) {

    }
}
