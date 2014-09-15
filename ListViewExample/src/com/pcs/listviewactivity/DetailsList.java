package com.pcs.listviewactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


import com.pcs.constants.Constants;
import com.pcs.listviewexample.R;

public class DetailsList extends Activity{
	private EditText nameEdt;
	private EditText phoneEdt;
	private EditText mailEdt;
	private EditText addressEdt;
	private Button doneBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		nameEdt = (EditText)findViewById(R.id.name_edt);
		mailEdt = (EditText)findViewById(R.id.mail_edt);
		phoneEdt = (EditText)findViewById(R.id.phone_edt);
		addressEdt = (EditText)findViewById(R.id.address_edt);
		doneBtn = (Button)findViewById(R.id.done); 
		doneBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DetailsList.this,ListViewActivity.class);
				intent.putExtra(Constants.IntentExtras.NAME, nameEdt.getText().toString());
				intent.putExtra(Constants.IntentExtras.MAIL, mailEdt.getText().toString());
				intent.putExtra(Constants.IntentExtras.PHONE,phoneEdt.getText().toString());
				intent.putExtra(Constants.IntentExtras.ADDRESS, addressEdt.getText().toString());
				setResult(RESULT_OK, intent);			
				finish();
			}
		});
		
		
	}
}
