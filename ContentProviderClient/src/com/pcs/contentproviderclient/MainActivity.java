package com.pcs.contentproviderclient;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity{
	private ListView listView;
	private Button showAllPersonsBtn,deleteAllPersonsBtn;
	private ContentResolver contentResolver;
	private Uri uri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);



		listView = (ListView)findViewById(R.id.listview);

		showAllPersonsBtn = (Button)findViewById(R.id.allPersons_btn);
		deleteAllPersonsBtn = (Button)findViewById(R.id.deleteAllPersons_btn);



		showAllPersonsBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				contentResolver = getContentResolver();

				uri = Uri.parse("content://com.pcs.contentprovider.MyContentProvider/person");

				Cursor cursor = contentResolver.query(uri, null, null, null, null);

					SimpleCursorAdapter simpleAdapter = new SimpleCursorAdapter(
							MainActivity.this, R.layout.listdisplay, cursor,
							new String[]{"_id", "_name", "_mail","_phone","_address"},
							new int[]{R.id.id_txt,R.id.name_txt,R.id.mail_txt,R.id.phone_txt,R.id.address_txt});

					listView.setAdapter(simpleAdapter);
				}

		});
		
		
		deleteAllPersonsBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				contentResolver = getContentResolver();

				uri = Uri.parse("content://com.pcs.contentprovider.MyContentProvider/person");

				int count = contentResolver.delete(uri, null, null);

				if(count>0)
				{
					Toast.makeText(getBaseContext(), "All Contacts are Deleted", Toast.LENGTH_LONG).show();
				}
				else
					Toast.makeText(getBaseContext(), "Not contacts found", Toast.LENGTH_LONG).show();

			}
		});


	}


}
