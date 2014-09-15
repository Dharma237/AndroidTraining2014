package com.pcs.listviewactivity;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.pcs.customadapterlist.CustomAdapter;
import com.pcs.listuser.User;
import com.pcs.listviewexample.R;

public class ListViewActivity extends Activity{
	private ListView listview;
	private ArrayList<User> userslist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		listview = (ListView)findViewById(R.id.listview);
		ArrayList<User> userslist = new ArrayList<User>();
		User user = new User();
		user.setUserName("Dharma");
		user.setEmail("dharmasai.seerapu@gmail.com");
		user.setAddress("eCentric");
		user.setPhoneNumber("9985458500");
		userslist.add(user);
		user = new User();
		user.setUserName("Harish");
		user.setPhoneNumber("9492524524");
		user.setEmail("harishkumar.vanka@gmail.com");
		user.setAddress("Paradigm Creatives");
		userslist.add(user);
		user = new User();
		user.setUserName("Anitha");
		user.setPhoneNumber("9876543210");
		user.setEmail("anitha.gorli@gmail.com");
		user.setAddress("eGram IT");
		userslist.add(user);
		CustomAdapter adapter = new CustomAdapter(ListViewActivity.this,userslist);
		listview.setAdapter(adapter);
	}

}
