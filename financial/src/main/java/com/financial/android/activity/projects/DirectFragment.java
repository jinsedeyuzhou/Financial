package com.financial.android.activity.projects;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.financial.android.R;
import com.financial.android.adapter.ProjectsListAdapter;
import com.financial.android.base.BaseFragment;
import com.financial.android.bean.ErrorMessage;
import com.financial.android.bean.Product;
import com.financial.android.bean.ProductInfo;
import com.financial.android.restful.config.AppConfig;
import com.financial.android.utils.DesUtil2;
import com.financial.android.utils.LogUtil;
import com.financial.android.utils.NetUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyy on 2016/1/26.
 * 直投项目
 */
public class DirectFragment extends BaseFragment {
    private static final String TAG ="DirectFragment";

    private ArrayList<Product> products;
    private ProjectsListAdapter plAdapter;


    //加载数据 每页数据，页码
    private String postParam = "{PageSize:%d,PageIndex:%d}";
    private int PageIndex = 1;
    //改变每页数量
    private int pageSize = 10;
    private int totalPage;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    plAdapter.notifyDataSetChanged();
                    // Call onRefreshComplete when the list has been refreshed.
                    break;
            }
        }
    };

    @Override
    protected void bindEvent() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.frag_direct_projects;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        products = new ArrayList<Product>();


    }


    private class GetDataTask extends AsyncTask<Void, Void, List<Product>> {

        @Override
        protected List<Product> doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
//                loadData();
            } catch (InterruptedException e) {
            }
            return products;
        }

        @Override
        protected void onPostExecute(List<Product> result) {

            //可以将productsList数据清除，重新加载请求后的数据

//            plAdapter.notifyDataSetChanged();
//            // Call onRefreshComplete when the list has been refreshed.
//            pull_refresh_list_direct.onRefreshComplete();

            super.onPostExecute(result);
        }
    }




    @Override
    protected void processClick(View v) {

    }
}
