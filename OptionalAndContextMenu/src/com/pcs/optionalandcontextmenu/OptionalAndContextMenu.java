package com.pcs.optionalandcontextmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;

public class OptionalAndContextMenu extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
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
			MenuInflater inflaterContextMenu = getMenuInflater();
			inflaterContextMenu.inflate(R.menu.contextmenu, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

}
