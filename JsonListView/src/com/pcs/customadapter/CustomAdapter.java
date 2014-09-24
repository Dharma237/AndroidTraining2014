package com.pcs.customadapter;

import java.util.ArrayList;

import com.pcs.jsonlistview.R;

import developerdetails_helper.DeveloperDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{
	private Context mcontext;
	private ArrayList<DeveloperDetails> developers;
	
	private LayoutInflater layoutInflater;

	public CustomAdapter(Context context, ArrayList<DeveloperDetails> android_developers) {
		mcontext = context;
		developers = android_developers;
		layoutInflater = LayoutInflater.from(mcontext);
	}
	
	//returns size of the developer object
	@Override
	public int getCount() {
		return developers.size();
	}

	//returns position of object
	@Override
	public Object getItem(int position) {
		return developers.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView,ViewGroup parent) {
		ViewHolder holder = null;
		DeveloperDetails userdetails = (DeveloperDetails) getItem(position);
		//When getView called convertView is null
		if(convertView==null)
		{
			convertView = layoutInflater.inflate(R.layout.list_display, null);
			holder = new ViewHolder();
			holder.image = (ImageView)convertView.findViewById(R.id.image_display);
			holder.nameTxt = (TextView)convertView.findViewById(R.id.user_name);
			holder.emailTxt = (TextView)convertView.findViewById(R.id.user_email);
			holder.phoneTxt = (TextView)convertView.findViewById(R.id.user_phone);
			//sets tag for convertView!=null
			convertView.setTag(holder);
		}
		else {
			//if convertView is not null then gets the Tag
			holder = (ViewHolder)convertView.getTag();
		}
		//setting data to the textfields
		holder.image.setBackgroundResource(R.drawable.user);
		holder.nameTxt.setText(userdetails.getUserName());
		holder.emailTxt.setText(userdetails.getEmail());
		holder.phoneTxt.setText(userdetails.getPhoneNumber());
		holder.nameTxt.setTextSize(15);
		convertView.setPadding(5, 10, 5, 10);
		return convertView;
	}
	public class ViewHolder{
		public TextView nameTxt;
		public TextView emailTxt;
		public TextView phoneTxt;
		public ImageView image;
	}
	/**
	 * userslist should not be null if so, throws IllegalArgument Exception
	 * takes argument as userslist
	 * @param userslist
	 * @throws IllegalArgumentException
	 */
	public void addUsers (ArrayList<DeveloperDetails> userslist) throws IllegalArgumentException
	{
		if(developers!=null)
		{
			userslist = new ArrayList<DeveloperDetails>();
		}
		if(developers!=null )
		{
			for (DeveloperDetails developer : userslist) {
				developers.add(developer);
				
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
	public void addUser(DeveloperDetails developer) throws IllegalArgumentException
	{
		if(developers!=null)
		{
			developers.add(developer);
			notifyDataSetChanged();
		}
		else
			throw new IllegalArgumentException("User Object should not be null");
	}
}

