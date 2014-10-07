package com.pcs.examapp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pcs.WeatherModel.WeatherDetails;
import com.pcs.adapters.WeatherAdapter;

public class WeatherActivity extends Activity{

	public ListView listView;
	public ArrayList<WeatherDetails> weather;

	public EditText cityEdt;
	public TextView displayTxt;
	public Button downloadBtn;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather);

		cityEdt = (EditText)findViewById(R.id.city_edt);
		listView = (ListView)findViewById(R.id.display_list);
		downloadBtn = (Button)findViewById(R.id.download_btn);
		downloadBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				new Download(WeatherActivity.this,listView).execute();
			}
		});

	}

	public  class Download extends AsyncTask<String, Integer, String>{
		private Context context;
		private ListView listview;
		public ProgressDialog progressDialog;
		private StringBuilder stringBuilder;
		private InputStream inputStream; 
		private InputStreamReader streamReader;
		private BufferedReader bufferedReader;
		
		
		public Download(WeatherActivity cityActivity, ListView listView) {
			context = cityActivity;
			this.listview=listView;
		}


		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog= new ProgressDialog(context);
			progressDialog.setMessage("Downloading. Please wait...");
			progressDialog.setIndeterminate(false);
			progressDialog.setMax(100);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}


		@Override
		protected String doInBackground(String... params) {


			try {

				//Assigning URL
				URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+cityEdt.getText().toString()+"&mode=json");
				
				
				//Open Connection
				URLConnection connection = url.openConnection();
				
				//connects to the URL
				connection.connect();
				
				//Storing ByteStream from URL
				inputStream = connection.getInputStream();
				
				//converting ByteStream into Charecter Stream
				streamReader = new InputStreamReader(inputStream);
				
				//converting Charecter stream to String
				bufferedReader = new BufferedReader(streamReader);
				
				stringBuilder= new StringBuilder();

				String line;
				
				
				//Assigning retried information to the String
				while ((line = bufferedReader.readLine()) != null) {

					stringBuilder.append(line);

					publishProgress(50);

				}
				

			} catch (Exception e) {
				Log.e("Error: ", e.getMessage());
			}
			
			//returning String value stored in Result
			return stringBuilder.toString();
		}
		
		//progressBar updation
		@Override
		protected void onProgressUpdate(Integer... progress) {
			super.onProgressUpdate(progress);
			progressDialog.setProgress(progress[0]);
		}
		
		/***
		 * Params are result;
		 * result contains all the data retrieved from the server
		 * result should not be null
		 */
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			//dismissing progressDialog
			progressDialog.dismiss();

			Toast.makeText(getApplicationContext(),getResources().getString(R.string.download_complete), Toast.LENGTH_LONG).show();

			try{

				weather = new ArrayList<WeatherDetails>();
				
				 WeatherAdapter adapter = new WeatherAdapter(WeatherActivity.this, weather);
				
				 //weather dat
				WeatherDetails weatherData = new WeatherDetails();

				//Getting result to JSON object
				JSONObject obj = new JSONObject(result);

				//obj has access to the retrieved data
				if((obj).has("name")){
					weatherData.setCity("City : "+obj.getString("name"));
				}

				//obj checks for all the required fileds in the Object "name"
				JSONObject object = obj.getJSONObject("main");
				if((object).has("temp")){

					weatherData.setTemp(getResources().getString(R.string.temperature)+object.getString("temp"));

				}
				if((object).has("humidity")){

					weatherData.setHumidity(getResources().getString(R.string.humidity)+object.getString("humidity"));
				}
				if((object).has("temp_min")){

					weatherData.setMin_temp(getResources().getString(R.string.min)+object.getString("temp_min"));
				}
				if((object).has("temp_max")){

					weatherData.setMax_temp(getResources().getString(R.string.max)+object.getString("temp_max"));
				}
				
				//adding weatherData to the ArrayList<WeatherData>
				weather.add(weatherData);
				
				//assigning adapter to the listview
				listview.setAdapter(adapter);				

			}
			catch (Exception e) {
				e.printStackTrace();
			}



		}
	}
}
