package com.financial.android.base;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 主页Fragment 适配,其他有关fragment也可以用
 * 
 * @author wyy
 * 
 */
public class FXFragmentPagerAdapter extends FragmentPagerAdapter {

	ArrayList<Fragment> list;

	public FXFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
		super(fm);
		this.list = list;
	}

	@Override
	public Fragment getItem(int position) {
		return list.get(position);
	}

	@Override
	public int getCount() {
		return list.size();
	}

}
