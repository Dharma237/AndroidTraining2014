package com.pcs.slideshow;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class WelcomeScreensViewPageAdapter extends PagerAdapter {
	private MainActivity mActivity;
	private int mCount;

	public WelcomeScreensViewPageAdapter( MainActivity activity, int count) {
		
		this.mActivity = activity;
		this.mCount = count;
	}

	@Override
	public int getCount() {
		return mCount;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((FrameLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		if (mActivity == null) {
			return null;
		}
		if (mCount == 6) {
			if (position == 0) {
				FrameLayout updateScreen1 = (FrameLayout) LayoutInflater.from(
						mActivity).inflate(R.layout.update_screens_1_layout,
						null);
				((ViewPager) container).addView(updateScreen1, 0);
				return updateScreen1;
			} else if (position == 1) {
				FrameLayout updateScreen2 = (FrameLayout) LayoutInflater.from(
						mActivity).inflate(R.layout.update_screens_2_layout,
						null);
				((ViewPager) container).addView(updateScreen2, 0);
				return updateScreen2;
			} else if (position == 2) {
				FrameLayout updateScreen3 = (FrameLayout) LayoutInflater.from(
						mActivity).inflate(R.layout.update_screens_3_layout,
						null);
				((ViewPager) container).addView(updateScreen3, 0);
				return updateScreen3;
			} else if (position == 3) {
				FrameLayout updateScreen4 = (FrameLayout) LayoutInflater.from(
						mActivity).inflate(R.layout.update_screens_4_layout,
						null);
				((ViewPager) container).addView(updateScreen4, 0);
				return updateScreen4;
			} else if (position == 4) {
				FrameLayout updateScreen5 = (FrameLayout) LayoutInflater.from(
						mActivity).inflate(R.layout.update_screens_5_layout,
						null);
				((ViewPager) container).addView(updateScreen5, 0);
				return updateScreen5;
			} else {
				FrameLayout updateScreen6 = (FrameLayout) LayoutInflater.from(
						mActivity).inflate(R.layout.update_screens_6_layout,
						null);
				Button cancel = (Button) updateScreen6
						.findViewById(R.id.cancel_button);
				cancel.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						if (mActivity != null) {
							mActivity.hideUpdateScreens();
							Toast.makeText(mActivity, R.string.success, Toast.LENGTH_SHORT).show();
						}
					}
				});
				((ViewPager) container).addView(updateScreen6, 0);
				return updateScreen6;
			}
		} else if (mCount == 1) {
			FrameLayout updateScreen6 = (FrameLayout) LayoutInflater.from(
					mActivity).inflate(R.layout.update_screens_6_layout, null);
			Button cancel = (Button) updateScreen6
					.findViewById(R.id.cancel_button);
			cancel.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					if (mActivity != null) {
						mActivity.hideUpdateScreens();
					}
				}
			});
			((ViewPager) container).addView(updateScreen6, 0);
			return updateScreen6;
		} else {
			return null;
		}
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((FrameLayout) object);
	}

}
