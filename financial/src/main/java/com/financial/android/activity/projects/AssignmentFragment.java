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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyy on 2016/1/26.
 * 直投项目
 */
public class AssignmentFragment extends BaseFragment {


    //数据源
    private ArrayList<Product> products;
    private ProjectsListAdapter plAdapter;


    @Override
    protected void bindEvent() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.frag_assignment_projects;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        products=new ArrayList<Product>();



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

            super.onPostExecute(result);
        }
    }
    @Override
    protected void processClick(View v) {

    }
}
