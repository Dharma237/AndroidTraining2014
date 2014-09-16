package com.pcs.listviewactivity;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.pcs.constants.Constants;
import com.pcs.customadapterlist.CustomAdapter;
import com.pcs.listuser.User;
import com.pcs.listviewexample.R;

public class ListViewActivity extends Activity{
	private ListView ls;
	private static final int RC_A=101;
	private ArrayList<User> users;
	private User user;
	private Button addBtn;
	private CustomAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		ls = (ListView)findViewById(R.id.list_view);
		addBtn = (Button)findViewById(R.id.add);
		users = new ArrayList<User>();
		user = new User();
		adapter = new CustomAdapter(ListViewActivity.this, users);
		addBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ListViewActivity.this,DetailsList.class);
				startActivityForResult(intent,RC_A);

			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(data!=null)

		{
			User user = new User();
			String name = data.getStringExtra(Constants.IntentExtras.NAME);
			String mail = data.getStringExtra(Constants.IntentExtras.MAIL);
			String phone = data.getStringExtra(Constants.IntentExtras.PHONE);
			String address = data.getStringExtra(Constants.IntentExtras.ADDRESS);
			user.setUserName(name);
			user.setEmail(mail);
			user.setPhoneNumber(phone);
			user.setAddress(address);
			users.add(user);
			ls.setAdapter(adapter);
		}
	}
}

