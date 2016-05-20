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
import com.financial.android.bean.Product;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyy on 2016/1/26.
 * 直投项目
 */
public class AssignmentFragment extends BaseFragment {

    @ViewInject(R.id.pull_refresh_list_assign)
    private PullToRefreshListView pull_refresh_list_assign;

    //数据源
    private ArrayList<Product> products;
    private ProjectsListAdapter plAdapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
     View view = inflater.inflate(R.layout.frag_assignment_projects,container,false);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        products=new ArrayList<Product>();


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
        plAdapter = new ProjectsListAdapter(ct, products, listViewAssign);
        listViewAssign.setAdapter(plAdapter);

    }


    private class GetDataTask extends AsyncTask<Void, Void, List<Product>>
    {

        @Override
        protected List<Product> doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Product> result) {

            //可以将productsList数据清除，重新加载请求后的数据

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
