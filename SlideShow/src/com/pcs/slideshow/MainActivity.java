package com.pcs.slideshow;


import com.viewpagerindicator.CirclePageIndicator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener{

	private RelativeLayout mTakeATourLayout;
	private RelativeLayout mUpdateScreensLayout;
	private Button takeATourButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mTakeATourLayout = (RelativeLayout) findViewById(R.id.take_a_tour);
		mUpdateScreensLayout = (RelativeLayout) findViewById(R.id.update_screens);
		takeATourButton = (Button) findViewById(R.id.takeatour_button);

		mTakeATourLayout.setClickable(true);

		initializeWelcomeScreens(6);


		takeATourButton.setOnClickListener(this);

	}

	private void initializeWelcomeScreens(int itemsCount) {

		final ViewPager viewPager = (ViewPager) findViewById(R.id.welcome_screens);
		
		WelcomeScreensViewPageAdapter adapter = new WelcomeScreensViewPageAdapter(
				this, itemsCount);
		viewPager.setAdapter(adapter);

		final CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
		// indicator.setBackgroundColor(Color.GRAY);
		indicator.setPageColor(Color.GRAY);
		int color = -1;
		try {
			color = Color.parseColor("#5c8cdf");
		} catch (Exception e) {
			color = Color.BLUE;
		}
		indicator.setFillColor(color);
		indicator.setViewPager(viewPager);
	}

	public void hideUpdateScreens() {

		if (mTakeATourLayout != null) {
			mTakeATourLayout.setVisibility(View.GONE);
		}
		if (mUpdateScreensLayout != null) {
			mUpdateScreensLayout.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {

		mUpdateScreensLayout.setVisibility(View.VISIBLE);
		takeATourButton.setVisibility(View.INVISIBLE);

	}
}
