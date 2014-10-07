package com.pcs.parcellableactivity;

import Constants.Constants;
import EmployeeDetailsHelper.DetailsHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity{
	private EditText idEdt,nameEdt,designationEdt;
	private Button loginBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		//referencing to xml elements
		idEdt = (EditText)findViewById(R.id.employeeId_edt);
		nameEdt = (EditText)findViewById(R.id.name_edt);
		designationEdt = (EditText)findViewById(R.id.designation_Edt);
		
		loginBtn = (Button)findViewById(R.id.login_btn);
		
		//onClick listener to the loginBtn
		loginBtn.setOnClickListener(new OnClickListener() {
			
		
			@Override
			public void onClick(View v) {
				//starting intent
				Intent intent = new Intent(getApplicationContext(),WelcomeActivity.class);
				
				//calling DetailsHelper Class
				DetailsHelper detailsHelper= new DetailsHelper();
				
				//storing values into the detailsHelper
				detailsHelper.setEmployeeId(idEdt.getText().toString());
				detailsHelper.setEmployeeName(nameEdt.getText().toString());
				detailsHelper.setEmployeeDesignation(designationEdt.getText().toString());
				
				//storing detailsHelper into intent
				intent.putExtra(Constants.IntentExtras.DETAILS, detailsHelper);
				
				//starting Intent
				startActivity(intent);
			}
		});
		
		
		
	}
}
