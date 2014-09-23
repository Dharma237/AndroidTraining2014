package com.pcs.jsonexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity{
	private Button jsonBtn;
	private TextView displayTxt;
	private ProgressDialog progressBar;
	private StringBuilder stringBuilder;
	private Context context;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		displayTxt = (TextView)findViewById(R.id.display_txt);
		jsonBtn = (Button)findViewById(R.id.json_button);
		jsonBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				progressBar = new ProgressDialog(MainActivity.this);
				progressBar.setTitle(getResources().getString(R.string.download));
				new DownloadFilePage(MainActivity.this).execute();				
			}
		});

	}


	private  class DownloadFilePage extends AsyncTask<String, Integer, String>
	{
		public DownloadFilePage(MainActivity mainActivity) {
			context = mainActivity;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... args)
		{
			URL url;
			try {
				url = new URL( "http://graph.facebook.com/12476");
				URLConnection con = url.openConnection();
				con.connect();
				InputStream inputStream = con.getInputStream();
				InputStreamReader isReader = new InputStreamReader(inputStream);
				BufferedReader reader = new BufferedReader(isReader);
				stringBuilder = new StringBuilder();
				String line = null;
				int i=1;
				while((line=reader.readLine())!=null)
				{
					progressBar.setProgress(i*100/10);
					stringBuilder.append(line);
				}

			} 
			catch (MalformedURLException e1) {
				e1.printStackTrace();
			}catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return stringBuilder.toString();

		}

		@Override
		protected void onProgressUpdate(Integer... values) {

			super.onProgressUpdate(values[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			JSONObject jsonObj;
			String obj = null;
			try {
				jsonObj = new JSONObject(result);
				if(jsonObj.has("first_name"))
				{
					 obj = jsonObj.getString("first_name");
				}
				if(jsonObj.has("gender"))
				{
					 obj = jsonObj.getString("gender");
				}
				if(jsonObj.has("last_name"))
				{
					 obj = jsonObj.getString("last_name");
				}
				if(jsonObj.has("link"))
				{
					 obj = jsonObj.getString("link");
				}
				if(jsonObj.has("name"))
				{
					 obj = jsonObj.getString("name");
				}
				if(jsonObj.has("user_name"))
				{
					 obj = jsonObj.getString("user_name");
				}
				displayTxt.setText(obj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}