package com.pcs.customadapterlist;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pcs.listuser.User;
import com.pcs.listviewactivity.ListViewActivity;
import com.pcs.listviewexample.R;

public class CustomAdapter extends BaseAdapter{
	private Context mcontext;
	private ArrayList<User> users;
	private LayoutInflater layoutInflater;
	public CustomAdapter(ListViewActivity listViewActivity,
			ArrayList<User> userslist) {
		mcontext = listViewActivity;
		users = userslist;
		layoutInflater = LayoutInflater.from(mcontext);
	}

	@Override
	public int getCount() {
		return users.size();
	}

	@Override
	public Object getItem(int position) {
		return users.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView,ViewGroup parent) {
		ViewHolder holder = null;
		User userdetails = (User) getItem(position);
		
		if(convertView==null)
		{
			convertView = layoutInflater.inflate(R.layout.display, null);
			holder = new ViewHolder();
			holder.image = (ImageView)convertView.findViewById(R.id.image_display);
			holder.nameTxt = (TextView)convertView.findViewById(R.id.user_name);
			holder.emailTxt = (TextView)convertView.findViewById(R.id.user_email);
			holder.phoneTxt = (TextView)convertView.findViewById(R.id.user_phone);
			holder.addressTxt = (TextView)convertView.findViewById(R.id.user_address);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder)convertView.getTag();
		}
			
		holder.image.setBackgroundResource(R.drawable.user);
		holder.nameTxt.setText(userdetails.getUserName());
		holder.emailTxt.setText(userdetails.getEmail());
		holder.phoneTxt.setText(userdetails.getPhoneNumber());
		holder.addressTxt.setText(userdetails.getAddress());
		holder.nameTxt.setTextSize(15);
		convertView.setPadding(5, 10, 5, 10);
		return convertView;
	}
	public class ViewHolder{
		public TextView nameTxt;
		public TextView emailTxt;
		public TextView phoneTxt;
		public TextView addressTxt;
		public ImageView image;
	}
	/**
	 * userslist should not be null if so, throws IllegalArgument Exception
	 * takes argument as userslist
	 * @param userslist
	 * @throws IllegalArgumentException
	 */
	public void addUsers (ArrayList<User> userslist) throws IllegalArgumentException
	{
		if(users!=null)
		{
			userslist = new ArrayList<User>();
		}
		if(userslist!=null )
		{
			for (User user : userslist) {
				users.add(user);
				
			}
			notifyDataSetChanged();
		}
		else 
			throw new IllegalArgumentException("UsersList should not be null");
	}
	/**
	 * takes user object as a argument
	 * if user is null then throws IllegalArgumentException
	 * @param user
	 * @throws IllegalArgumentException
	 */
	public void addUser(User user) throws IllegalArgumentException
	{
		if(users!=null)
		{
			users.add(user);
			notifyDataSetChanged();
		}
		else
			throw new IllegalArgumentException("User Object should not be null");
	}
}

