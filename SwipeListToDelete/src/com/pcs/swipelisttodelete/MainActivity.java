package com.pcs.swipelisttodelete;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity
{
	private AlertDialog.Builder builder;
	private LayoutInflater inflater;
	private AlertDialog alertDialog;

	ArrayAdapter<String> mAdapter;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up ListView example
		String[] items = new String[20];
		for (int i = 0; i < items.length; i++)
		{
			items[i] = "Item " + (i + 1);
		}

		mAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1,
				new ArrayList<String>(Arrays.asList(items)));
		setListAdapter(mAdapter);

		ListView listView = getListView();
		// Create a ListView-specific touch listener. ListViews are given special treatment because
		// by default they handle touches for their list items... i.e. they're in charge of drawing
		// the pressed state (the list selector), handling list item clicks, etc.
		SwipeDismissListViewTouchListener touchListener =
				new SwipeDismissListViewTouchListener(
						listView,
						new SwipeDismissListViewTouchListener.OnDismissCallback() {
							@Override
							public void onDismiss(ListView listView, int[] reverseSortedPositions) {
								for (final int position : reverseSortedPositions) {
									
									//creating DialogBox
									builder = new AlertDialog.Builder(MainActivity.this);
									
									//creating Message to the Dialog Box
									builder.setMessage("Delete")
									
									/***
									 * @param is position of list item
									 * position should bound out of array
									 * setting OnClickListener to the Positive Button in Dialog Box
									 * if Pressed Positive Button then ListView is Deleted from the list
									 */
									.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

										@Override
										public void onClick(DialogInterface dialog, int which) {
											
						
											mAdapter.remove(mAdapter.getItem(position));
											Toast.makeText(getApplicationContext(), "ListView is Removed",Toast.LENGTH_LONG).show();
											
											//dismissing the DialogBox
											alertDialog.dismiss();
											
											}
										})
										
										
									/***
									 * Setting Negative Button to the Dialog Box
									 * creating OnClickListener to the Negative Button in Dialog Box
									 */
									.setNegativeButton("No", new DialogInterface.OnClickListener() {

										@Override
										public void onClick(DialogInterface dialog, int which) {
											
											alertDialog.dismiss();
											Toast.makeText(getApplicationContext(), "List Item Deletion is Cancelled", Toast.LENGTH_LONG).show();
										}
									});

								}
								alertDialog = builder.create();
								alertDialog.show();
								mAdapter.notifyDataSetChanged();
							}
						});

		listView.setOnTouchListener(touchListener);
		// Setting this scroll listener is required to ensure that during ListView scrolling,
		// we don't look for swipes.
		listView.setOnScrollListener(touchListener.makeScrollListener());    

	}

	@Override
	protected void onListItemClick(ListView listView, View view, int position, long id)
	{
		Toast.makeText(this,
				"Clicked " + getListAdapter().getItem(position).toString(),
				Toast.LENGTH_SHORT).show();
	}
}