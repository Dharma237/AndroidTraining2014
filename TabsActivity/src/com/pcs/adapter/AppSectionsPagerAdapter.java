package com.pcs.adapter;

import com.pcs.tabsactivity.DummySectionFragment;
import com.pcs.tabsactivity.LaunchpadSectionFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;


public  class AppSectionsPagerAdapter extends FragmentPagerAdapter {

	private static final int ANDROID =0;
	private static final int IOS =1;
	private static final int WEB_APPS =2;


	public AppSectionsPagerAdapter(android.support.v4.app.FragmentManager fragmentManager) {

		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int i) {
		switch (i) {
		case 0:

			return new LaunchpadSectionFragment();

		default:

			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
			fragment.setArguments(args);
			return fragment;
		}
	}

	@Override
	public int getCount() {
		return 3;
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