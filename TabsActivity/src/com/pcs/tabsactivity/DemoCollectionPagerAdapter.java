package com.pcs.tabsactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

public  class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {

	private static final int ANDROID =0;
	private static final int IOS =1;
	private static final int WEB_APPS =2;

	public DemoCollectionPagerAdapter(android.support.v4.app.FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = new DemoObjectFragment();
		Bundle args = new Bundle();
		args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1); 
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		// For this contrived example, we have a 100-object collection.
		return 7;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		
		if(position == ANDROID)
		{
			return "Android";
		}
		else if(position == IOS)
		{
			return "iOS";
		}
		else
			return "WebApps";
	}
}
