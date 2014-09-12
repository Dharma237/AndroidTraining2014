package com.pcs.customadapterlist;

import java.util.ArrayList;

import com.pcs.listuser.User;
import com.pcs.listviewactivity.ListViewActivity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{
	private Context mcontext;
	private ArrayList<User> users;
	public CustomAdapter(ListViewActivity listViewActivity,
			ArrayList<User> userslist) {
		mcontext = listViewActivity;
		users = userslist;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return users.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return users.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView,ViewGroup parent) {
		TextView tv = new TextView(mcontext);
		tv.setText(users.get(position).getUserName()+ "\n " + users.get(position).getEmail() + "\n" + users.get(position).getPhoneNumber() + "\n" + users.get(position).getAddress());
		tv.setPadding(10, 5, 7, 6);
		return tv;
	}

}
