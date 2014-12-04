package com.pcs.navigationdrawer;

import java.util.ArrayList;

import com.pcs.adapters.NavigationDrawerAdapter;
import com.pcs.helper.NavigationDrawerItem;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener{
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] navDrawerTitles;
	private TypedArray navDrawerIcons;
	private Fragment fragment;
	private ArrayList<NavigationDrawerItem> navDrawerItems;
	private NavigationDrawerAdapter navAdapter;

	private ImageView navDrawerImage;
	private TextView headerTxt;

	private static final int HOME=0;
	private static final int SMARTCOOK=1;
	private static final int SHOPPING_LIST=2;
	private static final int INVENTORY=3;
	private static final int NEARBY_SUPERMARKETS=4;
	private static final int BARCODE_SCANNER=5;
	private static final int CONVERTER=6;
	private static final int LOGOUT=7;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.navList);

		navDrawerImage = (ImageView)findViewById(R.id.navigation_drawer_img);
		headerTxt = (TextView)findViewById(R.id.header_txt);

		navDrawerImage.setOnClickListener(this);

		setUpNavigationDrawer();

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.navigation_drawer_img:

			setUpNavigationDrawer();

			if (mDrawerLayout.isDrawerVisible(Gravity.RIGHT)) {
				return;
			} else {
				mDrawerLayout.openDrawer(Gravity.RIGHT);
			}

			break;

		default:
			break;
		}

	}

	private void setUpNavigationDrawer() {

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerToggle = new ActionBarDrawerToggle(
				this,                 
				mDrawerLayout,        
				R.drawable.ic_drawer, 
				R.string.drawer_open, 
				R.string.drawer_close 
				) {
			public void onDrawerClosed(View view) {

				invalidateOptionsMenu(); 
			}

			public void onDrawerOpened(View drawerView) {
				invalidateOptionsMenu(); 
			}
			@Override
			public boolean onOptionsItemSelected(MenuItem item) {
				if (item != null && item.getItemId() == android.R.id.home) {
					if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
						mDrawerLayout.closeDrawer(Gravity.RIGHT);
					} else {
						mDrawerLayout.openDrawer(Gravity.RIGHT);
					}
				}
				return false;
			}
		};


		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.END);

		navDrawerItems = new ArrayList<NavigationDrawerItem>();

		navDrawerTitles = getResources().getStringArray(R.array.navDrawerItems);
		navDrawerIcons = getResources().obtainTypedArray(R.array.navDrawerIcons);


		for(int i=0;i<navDrawerTitles.length;i++)
		{
			navDrawerItems.add(new NavigationDrawerItem(navDrawerTitles[i], navDrawerIcons.getResourceId(i, -1)));

		}

		navDrawerIcons.recycle();

		navAdapter = new NavigationDrawerAdapter(getApplicationContext(),navDrawerItems);

		mDrawerList.setAdapter(navAdapter);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerLayout.setDrawerListener(mDrawerToggle);

	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


			switch (position) {

			case HOME:

				fragment = new HomeActivity();

				headerTxt.setText(getResources().getString(R.string.home));

				Toast.makeText(getApplicationContext(),getResources().getString(R.string.home), Toast.LENGTH_SHORT).show();

				mDrawerLayout.closeDrawers();

				break;

			case SMARTCOOK:

				fragment = new SmartCookActivity();

				headerTxt.setText(getResources().getString(R.string.smart_cook));

				Toast.makeText(getApplicationContext(),getResources().getString(R.string.smart_cook), Toast.LENGTH_SHORT).show();

				mDrawerLayout.closeDrawers();

				break;

			case SHOPPING_LIST:

				fragment = new ShoppingListActivity();

				headerTxt.setText(getResources().getString(R.string.shopping_list));

				Toast.makeText(getApplicationContext(),getResources().getString(R.string.shopping_list), Toast.LENGTH_SHORT).show();


				break;

			case INVENTORY:

				fragment = new InventoryActivity();

				headerTxt.setText(getResources().getString(R.string.inventory));

				Toast.makeText(getApplicationContext(),getResources().getString(R.string.inventory), Toast.LENGTH_SHORT).show();

				break;

			case NEARBY_SUPERMARKETS:

				fragment = new NearbySupermarketsActivity();

				headerTxt.setText(getResources().getString(R.string.nearby_supermarkets));

				Toast.makeText(getApplicationContext(),getResources().getString(R.string.nearby_supermarkets), Toast.LENGTH_SHORT).show();

				break;

			case BARCODE_SCANNER:

				fragment = new BarcodeScanner();

				headerTxt.setText(getResources().getString(R.string.barcode_scanner));

				Toast.makeText(getApplicationContext(),getResources().getString(R.string.barcode_scanner), Toast.LENGTH_SHORT).show();

				break;

			case CONVERTER:

				fragment = new ConverterActivity();

				headerTxt.setText(getResources().getString(R.string.converter));

				Toast.makeText(getApplicationContext(),getResources().getString(R.string.converter), Toast.LENGTH_SHORT).show();

				break;

			case LOGOUT:

				fragment = new LogoutActivity();

				headerTxt.setText(getResources().getString(R.string.logout));

				Toast.makeText(getApplicationContext(),getResources().getString(R.string.logout), Toast.LENGTH_SHORT).show();

				break;

			default:
				break;

			}

			getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, fragment).commit();
			mDrawerLayout.closeDrawers();
		}
	}
}