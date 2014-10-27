package com.pcs.listanimation;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 
public class MainActivity extends ListActivity {
	
	//activity starts with onCreate() method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.activity_main);
        
       
        
        String[] mStrings = getResources().getStringArray(R.array.technologies);
        
        //Array List to the Technologies Array
        setListAdapter(new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, new ArrayList<String>(Arrays.asList(mStrings))));
    }
 
    
}
