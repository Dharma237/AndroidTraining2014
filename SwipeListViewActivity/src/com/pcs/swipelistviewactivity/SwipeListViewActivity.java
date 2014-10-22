package com.pcs.swipelistviewactivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.widget.ListAdapter;
import android.widget.ListView;

public abstract class SwipeListViewActivity extends Activity{
	
	private ListView listview;
	private int REL_SWIPE_MIN_DISTANCE;
	private int REL_SWIPE_MAX_OFF_DISTANCE;
	private int REL_SWIPE_THRESOLD_VELOCITY;
	
	
	public abstract ListView getListView();
	

	public void getSwipeItem(boolean isRight, int position) {
		
	}
	
	public abstract void onItemClickListener(ListAdapter adapter, int position);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
		
		REL_SWIPE_MIN_DISTANCE = (int)(120.0f * displayMetrics.densityDpi/160.0f+0.5);
		REL_SWIPE_MAX_OFF_DISTANCE = (int)(120.0f * displayMetrics.densityDpi/160.0f+0.5);
		REL_SWIPE_THRESOLD_VELOCITY = (int)(200.0f*displayMetrics.densityDpi/160.0f+0.5);
		
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		listview = getListView();
		
		/***
		 * Checks for listview is null or not
		 * if null throws ListView not set Exception
		 */
		if(listview==null)
		{
			new Throwable("ListView not Set Exception");
			
		}
		
		GestureDetector gestureDetector = new GestureDetector(new MyGestureDetector());
		
		
		
	}

}
