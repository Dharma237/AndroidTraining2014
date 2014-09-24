package com.pcs.jsonlistview;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pcs.customadapter.CustomAdapter;

import developerdetails_helper.DeveloperDetails;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity{
private Button clickBtn;
private ListView listView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
			
			clickBtn = (Button)findViewById(R.id.button);
			listView = (ListView)findViewById(R.id.list);
			
			
			clickBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
				new DownloadFilePage(MainActivity.this, listView).execute();
				}
			});
			
		}
	}
	/***
	 * Takes 3 Arguments params,Progress and Result
	 * @author pcs02
	 *
	 */
	class DownloadFilePage extends AsyncTask<String, Integer, String>
	{
		
		private ProgressDialog progressBar;
		private Context context;
		private StringBuilder stringBuilder;
		private ListView listview;
		private ArrayList<DeveloperDetails> developers;
		private DeveloperDetails developer;
		private CustomAdapter adapter;
		
		//Constructor to take Context and listView
		public DownloadFilePage(Activity activity,ListView listView) {
			context = activity;
			progressBar = new ProgressDialog(context);
			listview = listView;
		}
		
		//ProgressBar starts Here
		@Override
		protected void onPreExecute() {
			progressBar.setMessage("DownLoading");
			progressBar.setMessage("Downloading Details from the server...");
			progressBar.show();
			super.onPreExecute();
		}
		
		@Override
		protected String doInBackground(String... args)
		{
			URL url;
			try {
				//Connect to the URL
				url = new URL("http://192.168.4.16/android");
				
				URLConnection con = url.openConnection();
				
				//Opens the URL Connection
				con.connect();
				
				//Converting ByteStream into Character Stream
				InputStream inputStream = con.getInputStream();
				InputStreamReader isReader = new InputStreamReader(inputStream);
				
				//changes character stream into string and stores in reader
				BufferedReader reader = new BufferedReader(isReader);
				
				stringBuilder = new StringBuilder();
				String line = null;
				
				//retrieving data from the Server
				while((line=reader.readLine())!=null)
				{
					stringBuilder.append(line);
				}
				
				
				
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(stringBuilder.toString()==null)
			{
				return null;
			}
			//Returning string value
			else
				return stringBuilder.toString();
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {

			super.onProgressUpdate(values[0]);
		}
		
		//Attaching the result with our xml files
		@Override
		protected void onPostExecute(String result) {
			try 
			{
				JSONObject jobj = new JSONObject(result);
				
				//creating jsonArray
				JSONArray jsonArray = jobj.optJSONArray("android_team");
				
				int size = jsonArray.length();
				
				developers = new ArrayList<DeveloperDetails>();
				
				adapter = new CustomAdapter(context, developers);
				
				
				//Stores Object into developer Object
				for(int i=0;i<size;i++)
				{
					developer = new DeveloperDetails();
					//creating JSONObject
					JSONObject obj = new JSONObject();
					obj=jsonArray.getJSONObject(i);
					if(obj.has("name"))
					{
						developer.setUserName(obj.getString("name"));
					}
					if(obj.has("mail"))
					{
						developer.setEmail(obj.getString("mail"));
					}
					if(obj.has("phone_number"))
					{
						developer.setPhoneNumber(obj.getString("phone_number"));
					}
					
					//adding developer details to developers
					developers.add(developer);
					
					//setting adapter to list 
					listview.setAdapter(adapter);
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}
			//disconnecting progressing Bar
			progressBar.dismiss();
		}

		}
