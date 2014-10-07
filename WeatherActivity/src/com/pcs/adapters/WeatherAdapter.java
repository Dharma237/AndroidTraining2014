package com.pcs.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pcs.WeatherModel.WeatherDetails;
import com.pcs.examapp.R;
public class WeatherAdapter extends BaseAdapter{
	
	private ArrayList<WeatherDetails> weather;
	
	public Context context;
	public LayoutInflater inflater;

	public WeatherAdapter() {
		// TODO Auto-generated constructor stub
	}
	
	public WeatherAdapter(Context context,ArrayList<WeatherDetails> weather){
		
		//assigning Context,weather
		this.context=context;
		this.weather= weather;
		inflater = LayoutInflater.from(context);
	}
	
	/***
	 * returns the size of the arrayList<>
	 */

	@Override
	public int getCount() {
		return weather.size();
	}

	/***
	 * params are position
	 * returns the weather Object of each city in ArrayList<weatherDetails>
	 */
	@Override
	public Object getItem(int position) {
		return weather.get(position);
	}
	
	
	/***
	 * params are position
	 * returns the position of weather details in the weather Object
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/***
	 * params are position,view,ViewGroup
	 * 
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//holder class
		ViewHolder holder = new ViewHolder();
		
		//inflating xml file to the view
		convertView = inflater.inflate(R.layout.display_list, null);
		
		//referencing the xml elements
		holder.cityTxt = (TextView)convertView.findViewById(R.id.city);
		holder.tempTxt = (TextView)convertView.findViewById(R.id.temp);
		holder.humidityTxt= (TextView)convertView.findViewById(R.id.humidity);
		holder.temp_maxTxt = (TextView)convertView.findViewById(R.id.temp_max);
		holder.temp_minTxt = (TextView)convertView.findViewById(R.id.temp_min);
		
		
		//setting texts to the textfields
		holder.cityTxt.setText(weather.get(position).getCity());
		holder.tempTxt.setText(weather.get(position).getTemp());
		holder.humidityTxt.setText(weather.get(position).getHumidity());
		holder.temp_maxTxt.setText(weather.get(position).getMax_temp());
		holder.temp_minTxt.setText(weather.get(position).getMin_temp());
		
		
		//returns the view
		return convertView;
	}
	public class ViewHolder{
		public TextView cityTxt,tempTxt,temp_minTxt,temp_maxTxt,humidityTxt;
	}
}
