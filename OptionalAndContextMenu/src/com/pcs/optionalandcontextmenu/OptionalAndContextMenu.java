package com.pcs.optionalandcontextmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class OptionalAndContextMenu extends Activity{

	private LinearLayout firstSong;
	private LinearLayout secondSong;
	private LinearLayout thirdSong;
	private LinearLayout fourthSong;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		firstSong = (LinearLayout)findViewById(R.id.first_song);
		secondSong = (LinearLayout)findViewById(R.id.second_song);
		thirdSong = (LinearLayout)findViewById(R.id.third_song);
		fourthSong = (LinearLayout)findViewById(R.id.fourth_song);
		registerForContextMenu(firstSong);
		registerForContextMenu(secondSong);
		registerForContextMenu(thirdSong);
		registerForContextMenu(fourthSong);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflaterOptionMenu = getMenuInflater();
		inflaterOptionMenu.inflate(R.menu.optionmenu, menu);

		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflaterContextMenu = getMenuInflater();
		inflaterContextMenu.inflate(R.menu.contextmenu, menu);
	}
	/**
	 * Handles every Item in the ContextMenu
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {


		case R.id.shareTrack_bluetooth:
			Toast.makeText(OptionalAndContextMenu.this, getResources().getString(R.string.bluetooth), Toast.LENGTH_LONG).show();
			break;
			
		case R.id.shareTrack_message:
			Toast.makeText(OptionalAndContextMenu.this, getResources().getString(R.string.message), Toast.LENGTH_LONG).show();
			break;
			
		case R.id.setAs_alarmTone:
			Toast.makeText(OptionalAndContextMenu.this, getResources().getString(R.string.alarmTone), Toast.LENGTH_LONG).show();
			break;
			
		case R.id.setAs_callerTone:
			Toast.makeText(OptionalAndContextMenu.this, getResources().getString(R.string.callerTone), Toast.LENGTH_LONG).show();
			break;
			

		case R.id.suffle_on:
			Toast.makeText(OptionalAndContextMenu.this, getResources().getString(R.string.on), Toast.LENGTH_LONG).show();
			break;
			
		case R.id.suffle_off:
			Toast.makeText(OptionalAndContextMenu.this, getResources().getString(R.string.off), Toast.LENGTH_LONG).show();
			break;
			
		case R.id.context_details:
			Toast.makeText(OptionalAndContextMenu.this, getResources().getString(R.string.details), Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}
		
		return super.onContextItemSelected(item);
	}
	/**
	 * Handles EveryItem in the OptionsMenu
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.option_allTracks:
			Toast.makeText(OptionalAndContextMenu.this, getResources().getString(R.string.allTracks), Toast.LENGTH_LONG).show();
			break;

		case R.id.option_albums:
			Toast.makeText(OptionalAndContextMenu.this, getResources().getString(R.string.albums), Toast.LENGTH_LONG).show();
			break;

		case R.id.option_artists:
			Toast.makeText(OptionalAndContextMenu.this, getResources().getString(R.string.artists), Toast.LENGTH_LONG).show();
			break;

		case R.id.option_genres:
			Toast.makeText(OptionalAndContextMenu.this, getResources().getString(R.string.genres), Toast.LENGTH_LONG).show();
			break;

		case R.id.option_years:
			Toast.makeText(OptionalAndContextMenu.this, getResources().getString(R.string.years), Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
