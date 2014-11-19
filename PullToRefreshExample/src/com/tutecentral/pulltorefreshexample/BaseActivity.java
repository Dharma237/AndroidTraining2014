package com.tutecentral.pulltorefreshexample;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		

        // Add the Sample Fragment if there is one
        Fragment sampleFragment = getSampleFragment();
        if (sampleFragment != null) {
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, sampleFragment).commit();
        }
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		  getMenuInflater().inflate(R.menu.base_menu, menu);
		  
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	
		// This method is for menu. This menu items will appear in all
		//activities extends this class. I have use this menus to navigate 
		//between activities. You can change this code as you wish 
		// 
		
		
		  switch (item.getItemId()) {
		  
          case R.id.action_listview:
        	  
              Toast.makeText(this, "Pull to Refresh in ListView", Toast.LENGTH_SHORT).show();
              Intent listIntent=new Intent(this,ListViewActivity.class);
              startActivity(listIntent);
              
              return true;
              
          case R.id.action_scrollview:
        	  
              Toast.makeText(this, "Pull to Refresh in Scroll View", Toast.LENGTH_SHORT).show();
             
              Intent scrollViewIntent=new Intent(this,ScrollViewActivity.class);
              startActivity(scrollViewIntent);
              
              return true;
              
      }
		
		return super.onOptionsItemSelected(item);
	}

	
	//This method will override by child class. Then base class can get the fragment
	protected Fragment getSampleFragment() {
        return null;
    }
	
	
}