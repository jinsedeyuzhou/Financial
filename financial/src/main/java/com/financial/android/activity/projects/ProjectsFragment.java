package com.financial.android.activity.projects;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.financial.android.R;
import com.financial.android.base.BaseFragment;
import com.financial.android.base.FXFragmentPagerAdapter;
import com.financial.android.utils.LogUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * @author wyy
 * 产品列表
 *
 */
public class ProjectsFragment extends BaseFragment {

	private View view;
	@ViewInject(R.id.bar_tv_title)
	private TextView bar_tv_title;
	@ViewInject(R.id.bar_rl_visible)
	private RelativeLayout bar_rl_visible;

	@ViewInject(R.id.projects_viewpager)
	private ViewPager projects_viewpager;
	@ViewInject(R.id.rg_projects)
	private RadioGroup rg_projects;
	@ViewInject(R.id.iv_cursor)
	private ImageView iv_cursor;
	private ArrayList<Fragment> fragProjects;
	// 当前选中fragment
	private int curIndex;
	//屏幕宽度
	private int screenWidth;









	@Override
	protected View initView(LayoutInflater inflater, ViewGroup container) {
		view = inflater.inflate(R.layout.fragment_projects, container, false);
		ViewUtils.inject(this, view);
		initTitleBar();
		LogUtil.d("BaseFragment", "ProjectsFragment");
		return view;
	}

	private void initTitleBar() {
		bar_tv_title.setText("投资");
	}

	@Override
	protected void initData(Bundle savedInstanceState) {
		DisplayMetrics outMetrics=new DisplayMetrics();
		getActivity().getWindow().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		screenWidth=outMetrics.widthPixels;
		LinearLayout.LayoutParams lp=(LinearLayout.LayoutParams) iv_cursor.getLayoutParams();//获取控件的布局参数对象
		lp.width=screenWidth/2;
		iv_cursor.setLayoutParams(lp);

		rg_projects.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
					case R.id.rb_direct:
						projects_viewpager.setCurrentItem(0);
						curIndex=0;
						break;
					case R.id.rb_assignment:
						projects_viewpager.setCurrentItem(1);
						curIndex=1;

						break;

				}
			}
		});
		fragProjects=new ArrayList<Fragment>();
		DirectFragment dpf = new DirectFragment();
		fragProjects.add(dpf);
		AssignmentFragment apf = new AssignmentFragment();
		fragProjects.add(apf);
		projects_viewpager.setOffscreenPageLimit(1); //设置缓存界面
		projects_viewpager.setAdapter(new FXFragmentPagerAdapter(getChildFragmentManager(), fragProjects));
		projects_viewpager.setCurrentItem(0);
		projects_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				LinearLayout.LayoutParams lp=(LinearLayout.LayoutParams) iv_cursor.getLayoutParams();
				//获取组件距离左侧组件的距离
				lp.leftMargin=(int) ((positionOffset+position)*screenWidth/2);
				iv_cursor.setLayoutParams(lp);
			}

			@Override
			public void onPageSelected(int position) {
				switch (position) {
					case 0:
						rg_projects.check(R.id.rb_direct);
						break;
					case 1:
						rg_projects.check(R.id.rb_assignment);
						break;
					default:
						rg_projects.check(R.id.rb_direct);
						break;

				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
	}
//		//spinner 适配
//		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//				getActivity(), R.array.planets_array,
//				android.R.layout.simple_spinner_item);
		// ArrayAdapter<CharSequence> adapter = ArrayAdapter
		// .createFromResource(getActivity(), R.array.planets_array,
		// android.R.layout.simple_spinner_item);

		// Specify the layout to use when the list of choices appears
		// adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner

//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinner1.setAdapter(adapter);
//		spinner1.setGravity(Gravity.CENTER);
//		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view,
//									   int position, long id) {
////				TextView textView = (TextView) view;
////				textView.setTextColor(getResources().getColor(R.color.blue));
////				textView.setGravity(Gravity.CENTER);
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//
//			}
//
//		});
//		spinner2.setAdapter(adapter);
//		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view,
//									   int position, long id) {
////				TextView textView = (TextView) view;
////				textView.setTextColor(getResources().getColor(R.color.blue));
////				textView.setGravity(Gravity.CENTER);
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//
//			}
//		});
//		spinner3.setAdapter(adapter);

//		productsList=new ArrayList<ProductInfo>();
//		ProductInfo info1=new ProductInfo("傻逼","我就是个天才");
//		productsList.add(info1);
//		ProductInfo info2=new ProductInfo("天才","我就是个傻逼");
//		productsList.add(info2);



		//android-pullToRefreshListActivity
		// Set a listener to be invoked when the list should be refreshed.
//		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
//
//			@Override
//			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//				new GetDataTask().execute();
//			}
//		});
//
//		// Add an end-of-list listener
//		mPullRefreshListView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
//
//			@Override
//			public void onLastItemVisible() {
//				showToast("End of List!");
//			}
//		});
//
//		ListView actualListView = mPullRefreshListView.getRefreshableView();
//
//
//		plAdapter = new ProjectsListAdapter(ct, productsList, actualListView);
//		actualListView.setAdapter(plAdapter);
//
//	}
//
//
//
//	private class GetDataTask extends AsyncTask<Void, Void, List<ProductInfo>>
//	{
//
//		@Override
//		protected List<ProductInfo> doInBackground(Void... params) {
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//			}
//			return productsList;
//		}
//
//		@Override
//		protected void onPostExecute(List<ProductInfo> result) {
//
//			//可以将productsList数据清除，重新加载请求后的数据
//			productsList.clear();
//			ProductInfo info2=new ProductInfo("天才","我就是个傻逼");
//			productsList.add(info2);
//
//			plAdapter.notifyDataSetChanged();
//			// Call onRefreshComplete when the list has been refreshed.
//			mPullRefreshListView.onRefreshComplete();
//
//			super.onPostExecute(result);
//		}
//	}


	@Override
	protected void processClick(View v) {

	}









	/**
	 * 原生ListView适配
	 * @author wyy
	 *
	 */
//	class ProjectsListAdapter extends FXBaseAdapter<ProductInfo, ListView> {
//
//		public ProjectsListAdapter(Context context, List<ProductInfo> list,
//								   ListView view) {
//			super(context, list, view);
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			ViewHolder holder = null;
//			ProductInfo product = productsList.get(position);
//			if (convertView == null) {
//
//				convertView = View.inflate(context, R.layout.product_item,
//						null);
//				holder = new ViewHolder(product);
//				ViewUtils.inject(holder, convertView);
//				convertView.setTag(holder);
//			} else {
//				holder = (ViewHolder) convertView.getTag();
//			}
//
//			holder.mProcutName.setText(product.getName());
//			holder.mProDescription.setText(product.getDescription());
//
//			return convertView;
//		}
//
//		class ViewHolder {
//			@ViewInject(R.id.tv_product_name)
//			TextView mProcutName;
//			@ViewInject(R.id.tv_product_description)
//			TextView mProDescription;
//
//			private ProductInfo card;
//			public ViewHolder(ProductInfo card) {
//				this.card = card;
//			}
//		}
//
//	}

}
