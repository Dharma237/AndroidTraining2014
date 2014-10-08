package com.pcs.contentprovider;


import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pcs.contentprovider_insertrow.R;

public class MainActivity extends Activity{
	
	private EditText nameEdt,mailEdt,addressEdt,phoneEdt,idEdt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		//referencing layout elements
		nameEdt = (EditText)findViewById(R.id.name_edt);
		mailEdt = (EditText)findViewById(R.id.mail_edt);
		addressEdt = (EditText)findViewById(R.id.address_edt);
		phoneEdt = (EditText)findViewById(R.id.phone_edt);
		idEdt = (EditText)findViewById(R.id.id_edt);
	}
	
	public void addInformation(View view)
	{
		
		String id = idEdt.getText().toString();
		String name = nameEdt.getText().toString();
		String address = addressEdt.getText().toString();
		String phone = phoneEdt.getText().toString();
		String mail = mailEdt.getText().toString();
		
		ContentValues values = new ContentValues();
		values.put(MyContentProvider.ID, id);
		values.put(MyContentProvider.NAME, name);
		values.put(MyContentProvider.ADDRESS, address);
		values.put(MyContentProvider.PHONE, phone);
		values.put(MyContentProvider.MAIL, mail);
		
		Uri uri = getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
		
		Toast.makeText(getBaseContext(), "New Person Information is added", Toast.LENGTH_LONG).show();
	}

}
