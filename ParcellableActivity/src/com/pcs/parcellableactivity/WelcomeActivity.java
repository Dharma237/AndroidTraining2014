package com.pcs.parcellableactivity;

import Constants.Constants;
import EmployeeDetailsHelper.DetailsHelper;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
public class WelcomeActivity extends Activity{
	
	private TextView employeeDetailsTxt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//mapping xml to activity
		setContentView(R.layout.display_list);
		
		//mapping xml elements
		employeeDetailsTxt = (TextView)findViewById(R.id.employeeDetails_txt);
		
		DetailsHelper helper = getIntent().getParcelableExtra(Constants.IntentExtras.DETAILS);
		
		//text to the detailsTxt
		employeeDetailsTxt.setText(helper.toString());
		
		
		
	}
}
