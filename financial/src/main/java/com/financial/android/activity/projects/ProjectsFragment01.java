package com.financial.android.activity.projects;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.base.BaseFragment;
import com.financial.android.base.FXBaseAdapter;
import com.financial.android.bean.ProductInfo;
import com.financial.android.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wyy
 * 产品列表
 *
 */
public class ProjectsFragment01 extends BaseFragment {

	private View view;
	private TextView bar_tv_title;
	private RelativeLayout bar_rl_visible;

	// spinner
	private Spinner spinner1;
	private Spinner spinner2;
	private Spinner spinner3;

	// listView
//	@ViewInject(R.id.listview_projects)
//	private ListView listView;
	
	
	//android-pullToRefreshListActivity

	//数据源 
	private ArrayList<ProductInfo> productsList;
	private ProjectsListAdapter plAdapter;
	

	private void initTitleBar() {
		bar_tv_title.setText("投资");
	}

	@Override
	protected void bindEvent() {

	}

	@Override
	protected int getLayoutID() {
		return R.layout.fragment_projects01;
	}

	@Override
	protected void initView(View view) {
		bar_tv_title= (TextView) view.findViewById(R.id.bar_tv_title);
		bar_rl_visible= (RelativeLayout) view.findViewById(R.id.bar_rl_visible);
		spinner1= (Spinner) view.findViewById(R.id.Spinner1);
		spinner2= (Spinner) view.findViewById(R.id.Spinner2);
		spinner3= (Spinner) view.findViewById(R.id.Spinner3);
		initTitleBar();
		LogUtil.d("BaseFragment", "ProjectsFragment");
	}

	@Override
	protected void initData(Bundle savedInstanceState) {
		//spinner 适配
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity(), R.array.planets_array,
				android.R.layout.simple_spinner_item);
		// ArrayAdapter<CharSequence> adapter = ArrayAdapter
		// .createFromResource(getActivity(), R.array.planets_array,
		// android.R.layout.simple_spinner_item);

		// Specify the layout to use when the list of choices appears
		// adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter);
		spinner1.setGravity(Gravity.CENTER);
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
//				TextView textView = (TextView) view;
//				textView.setTextColor(getResources().getColor(R.color.blue));
//				textView.setGravity(Gravity.CENTER);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});
		spinner2.setAdapter(adapter);
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
//				TextView textView = (TextView) view;
//				textView.setTextColor(getResources().getColor(R.color.blue));
//				textView.setGravity(Gravity.CENTER);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		spinner3.setAdapter(adapter);
		
		productsList=new ArrayList<ProductInfo>();
		ProductInfo info1=new ProductInfo("傻逼","我就是个天才");
		productsList.add(info1);
		ProductInfo info2=new ProductInfo("天才","我就是个傻逼");
		productsList.add(info2);
		
		
		

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

			super.onPostExecute(result);
		}
	}
	
	
	@Override
	protected void processClick(View v) {

	}

	
	
	
	
	
	
	
	
	/**
	 * 原生ListView适配
	 * @author wyy
	 *
	 */
	class ProjectsListAdapter extends FXBaseAdapter<ProductInfo, ListView> {

		public ProjectsListAdapter(Context context, List<ProductInfo> list,
				ListView view) {
			super(context, list, view);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			ProductInfo product = productsList.get(position);
			if (convertView == null) {

				convertView = View.inflate(context, R.layout.product_item,
						null);
				holder = new ViewHolder(product);
				holder.mProcutName= (TextView) convertView.findViewById(R.id.tv_product_name);
				holder.mProDescription= (TextView) convertView.findViewById(R.id.tv_product_description);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.mProcutName.setText(product.getName());
			holder.mProDescription.setText(product.getDescription());

			return convertView;
		}

		class ViewHolder {
			TextView mProcutName;
			TextView mProDescription;

			private ProductInfo card;
			public ViewHolder(ProductInfo card) {
				this.card = card;
			}
		}

	}

}
