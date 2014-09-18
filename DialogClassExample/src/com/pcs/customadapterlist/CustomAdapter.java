package com.pcs.customadapterlist;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pcs.user.User;
import com.pcs.dialogclassexample.DialogClassActivity;
import com.pcs.dialogclassexample.R;


public class CustomAdapter extends BaseAdapter{
	private Context dialogContext;
	private ArrayList<User> users;
	private LayoutInflater layoutInflater;
	

	

	public CustomAdapter(DialogClassActivity dialogClassActivity,
			ArrayList<User> userslist) {
		super();
		dialogContext = dialogClassActivity;
		users = userslist;
		layoutInflater = LayoutInflater.from(dialogContext);
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
			convertView = layoutInflater.inflate(R.layout.listviewdisplay, null);
			holder = new ViewHolder();
			holder.nameTxt = (TextView)convertView.findViewById(R.id.username_display_txt);
			holder.emailTxt = (TextView)convertView.findViewById(R.id.mail_display_txt);
			holder.phoneTxt = (TextView)convertView.findViewById(R.id.phone_display_txt);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder)convertView.getTag();
		}
			
		holder.nameTxt.setText("UserName:-\t"+userdetails.getUserName());
		holder.emailTxt.setText("\teMail:-\t"+userdetails.getEmail());
		holder.phoneTxt.setText("PhoneNumber:-\t"+userdetails.getPhoneNumber());
		holder.nameTxt.setTextSize(15);
		convertView.setPadding(5, 10, 5, 10);
		return convertView;
	}
	public class ViewHolder{
		public TextView nameTxt;
		public TextView emailTxt;
		public TextView phoneTxt;
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