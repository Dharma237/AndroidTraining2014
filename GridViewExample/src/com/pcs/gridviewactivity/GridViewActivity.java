package com.pcs.gridviewactivity;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.pcs.constants.Constants;
import com.pcs.customadapterlist.CustomAdapter;
import com.pcs.griduser.User;
import com.pcs.listviewexample.R;

public class GridViewActivity extends Activity{
	private GridView gv;
	private static final int RC_A=101;
	private ArrayList<User> users;
	private User user;
	private Button addBtn;
	private CustomAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid);
		gv = (GridView)findViewById(R.id.grid_view);
		addBtn = (Button)findViewById(R.id.add);
		users = new ArrayList<User>();
		user = new User();
		adapter = new CustomAdapter(GridViewActivity.this, users);
		addBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(GridViewActivity.this,DetailsGrid.class);
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
			gv.setAdapter(adapter);
		}
	}
}

