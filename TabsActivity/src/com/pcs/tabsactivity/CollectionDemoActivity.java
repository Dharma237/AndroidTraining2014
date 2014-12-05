package com.pcs.tabsactivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

public class CollectionDemoActivity extends FragmentActivity {

	
	DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;

	
	ViewPager mViewPager;

	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_collection_demo);

		
		mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());

		// Set up action bar.
		final ActionBar actionBar = getActionBar();

		actionBar.setDisplayHomeAsUpEnabled(true);

		// Set up the ViewPager, attaching the adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mDemoCollectionPagerAdapter);
	}

	@SuppressLint("NewApi")
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			
			Intent upIntent = new Intent(this, MainActivity.class);
			
			if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
				
				TaskStackBuilder.create(this)
				
				.addNextIntent(upIntent)
				
				.startActivities();
				
				finish();
				
			} else {
				
				NavUtils.navigateUpTo(this, upIntent);
			}
			
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	
}