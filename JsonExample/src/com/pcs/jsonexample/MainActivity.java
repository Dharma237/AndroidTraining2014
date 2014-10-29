package com.pcs.jsonexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
	private Button jsonBtn;
	private TextView idTxt;
	private TextView genderTxt;
	private TextView lastNameTxt;
	private TextView firstNameTxt;
	private TextView nameTxt;
	private StringBuilder stringBuilder;
	private Context context;
	public JSONObject jsonObj;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);


		idTxt = (TextView)findViewById(R.id.id_txt);
		nameTxt = (TextView)findViewById(R.id.name_txt);
		firstNameTxt = (TextView)findViewById(R.id.firstName_txt);
		lastNameTxt = (TextView)findViewById(R.id.lastName_txt);
		genderTxt = (TextView)findViewById(R.id.gender_txt);
		jsonBtn = (Button)findViewById(R.id.json_button);
		jsonBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DownloadFilePage(MainActivity.this).execute();				
			}
		});

	}

	/**
	 * Arguments Params, Progress and Result
	 * @author pcs02
	 *
	 */
	private  class DownloadFilePage extends AsyncTask<String, Integer, String>
	{
		private ProgressDialog progressBar;

		public DownloadFilePage(MainActivity mainActivity) {
			context = mainActivity;
			progressBar = new ProgressDialog(context);
			progressBar.setTitle(getResources().getString(R.string.json_download));


		}
		/***
		 * ProgressBar showing
		 */

		@Override
		protected void onPreExecute() {
			progressBar.setMessage(getResources().getString(R.string.download));
			progressBar.show();
			super.onPreExecute();
		}
		/***
		 * Retrieves Facebook User Details from Server
		 * URL should not be null
		 * URL should not be incorrect
		 * Throws MalformedURLException
		 * Throws ClientProtocolException
		 * Throws IOException
		 */
		@Override
		protected String doInBackground(String... args)
		{
			final String TAG = "MainActivity.class";
			URL url;
			try {

				url = new URL("http://graph.facebook.com/645261812238752");
				URLConnection con = url.openConnection();
				con.connect();

				//setting TimeOut for the connection
				HttpParams httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
				HttpConnectionParams.setSoTimeout(httpParams, 3000);

				//Retrieving Data from the Server using Connection Object
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
			}

			catch (ClientProtocolException e) {
				Log.e( TAG, "Http Error",e);
			}

			catch(SocketTimeoutException e){
				Log.e(TAG, "Time Out", e);
			}

			catch (IOException e) {
				e.printStackTrace();
			}


			if(stringBuilder.toString()==null)
			{
				progressBar.dismiss();
			}

			//returning the Retrieved Data
			return stringBuilder.toString();

		}

		@Override
		protected void onProgressUpdate(Integer... values) {

			super.onProgressUpdate(values[0]);
		}
		/***
		 * Assigning received Data
		 * Throws JSONException
		 */
		@Override
		protected void onPostExecute(String result) {
			String obj = null;
			try {
				jsonObj = new JSONObject(result);

				if(jsonObj==null)
				{
					Toast.makeText(context, "Json Data is Null", Toast.LENGTH_LONG).show();
				}
				else
				{

					if(jsonObj.has("first_name"))
					{
						obj = jsonObj.getString("first_name");
						firstNameTxt.setText(getResources().getString(R.string.first_name)+"\t"+ obj);
					}

					if(jsonObj.has("gender"))
					{
						obj = jsonObj.getString("gender");
						genderTxt.setText((getResources().getString(R.string.gender)+"\t\t"+obj));
					}

					if(jsonObj.has("last_name"))
					{
						obj = jsonObj.getString("last_name");
						lastNameTxt.setText((getResources().getString(R.string.last_name)+"\t"+obj));
					}

					if(jsonObj.has("name"))
					{
						obj = jsonObj.getString("name");
						nameTxt.setText((getResources().getString(R.string.name)+"\t\t"+obj));
					}

					if(jsonObj.has("id"))
					{
						obj = jsonObj.getString("id");
						idTxt.setText((getResources().getString(R.string.id)+""+obj));
					}

				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
			progressBar.dismiss();
		}
	}
}