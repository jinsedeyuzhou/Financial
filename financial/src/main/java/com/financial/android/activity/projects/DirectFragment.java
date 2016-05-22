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
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

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
    @ViewInject(R.id.pull_refresh_list_direct)
    private PullToRefreshListView pull_refresh_list_direct;

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
                    pull_refresh_list_direct.onRefreshComplete();
                    break;
            }
        }
    };

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.frag_direct_projects, container, false);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        products = new ArrayList<Product>();

        loadData();
        pull_refresh_list_direct.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PageIndex = 1;
                if (products!=null)
                {
                    products.clear();
                }
                loadData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (!NetUtil.isConnectionAvailable(ct))
                {
                    showToast("网络异常");
                }
                else if (PageIndex <= totalPage) {
                    loadData();
                } else {
//                    pull_refresh_list_direct.onRefreshComplete();
                    showToast("End of List!");
                }
                handler.sendEmptyMessageDelayed(0,2000);

            }


        });

        pull_refresh_list_direct.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {

            }
        });

        pull_refresh_list_direct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ct, position - 1 + "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ct, ProductActivity.class);
                startActivity(intent);
            }
        });
        ListView listViewAssign = pull_refresh_list_direct.getRefreshableView();
        plAdapter = new ProjectsListAdapter(ct, products, listViewAssign);
        listViewAssign.setAdapter(plAdapter);

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


    /**
     * 加载数据
     */
    private void loadData() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        DesUtil2 desObj = new DesUtil2();

        params.addQueryStringParameter(
                "baseUrl",
                desObj.strEnc(AppConfig.INVEST
                        + String.format(postParam, pageSize, PageIndex++)));
        httpUtils.configCurrentHttpCacheExpiry(1000 * 10);

        httpUtils.send(HttpRequest.HttpMethod.POST, AppConfig.BASE_URL, params, new RequestCallBack<String>() {



            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                LogUtil.v(TAG, responseInfo.result.toString());

                try {
                    JSONObject json = null;
                    json = new JSONObject(responseInfo.result);
                    if ("ok".equals(json.getString("result"))) {
                        JSONObject response = json.getJSONObject("response");
                        int pageNumber = response.getInt("pageNumber");
                        int pageSize = response.getInt("pageSize");
                        int totalCount = response.getInt("totalCount");
                        int totalPage = response.getInt("totalPage");
                        JSONArray datas = response.getJSONArray("datas");
                        for (int i = 0, j = 0; i < datas.length(); i++) {
                            JSONArray data = datas.getJSONArray(i);
                            Product product = new Product(data.get(j).toString(), data.get(j + 1).toString(), Double.valueOf(data.get(j + 2).toString()), Double.valueOf(data.get(j + 3).toString()), data.get(j + 4).toString(), data.get(j + 5).toString(), data.get(j + 6).toString(), data.get(j + 7).toString(), Double.valueOf(data.get(j + 8).toString()), Double.valueOf(data.get(j + 9).toString()), data.get(j + 10).toString(), data.get(j + 11).toString());
                            products.add(product);
                            handler.sendEmptyMessage(0);
                        }
                    } else if ("fail".equals(json.getString("result"))) {
                        JSONObject response = json.getJSONObject("response");
                        Gson gson = new Gson();
                        ErrorMessage error = gson.fromJson(response.toString(), ErrorMessage.class);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                showToast("网络异常");
            }
        });

    }

    @Override
    protected void processClick(View v) {

    }
}
