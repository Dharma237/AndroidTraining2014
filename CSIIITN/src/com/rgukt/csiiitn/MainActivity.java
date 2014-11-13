package com.rgukt.csiiitn;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.AsyncFacebookRunner.RequestListener;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


@SuppressWarnings("deprecation")
public class MainActivity extends Activity implements OnClickListener{

	private ImageView login;
	private Facebook facebook;
	SharedPreferences preferences;
	private Button profileInfromationBtn;
	private AsyncFacebookRunner mAsyncRunner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

			setContentView(R.layout.activity_main);
	
			login = (ImageView) findViewById(R.id.login);
			
			profileInfromationBtn = (Button)findViewById(R.id.profileInformation_btn);
	
			String APP_ID = getResources().getString(R.string.app_id);
			
			mAsyncRunner = new AsyncFacebookRunner(facebook);
	
			
	
			facebook = new Facebook(APP_ID);
	
			login.setOnClickListener(this);
			
			profileInfromationBtn.setOnClickListener(this);
	
			upDateButtonImage();
	}


	private void upDateButtonImage() {
		if(facebook.isSessionValid())
		{
			login.setImageResource(R.drawable.logout);
		}
		else
			login.setImageResource(R.drawable.login);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.login:
				loginToFacebook();
			break;
			
		case R.id.profileInformation_btn:
				profileInformation();

		default:
			break;
		}
		

	}


	private void profileInformation() {
		
		mAsyncRunner.request("me", new RequestListener() {
			@Override
			public void onComplete(String response, Object state) {
				Log.d("Profile", response);
				String json = response;
				try {
					// Facebook Profile JSON data
					JSONObject profile = new JSONObject(json);
					
					// getting name of the user
					final String name = profile.getString("name");
					
					// getting email of the user
					final String email = profile.getString("email");
					
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(getApplicationContext(), "Name: " + name + "\nEmail: " + email, Toast.LENGTH_LONG).show();
						}

					});

					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onIOException(IOException e, Object state) {
			}

			@Override
			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {
			}

			@Override
			public void onMalformedURLException(MalformedURLException e,
					Object state) {
			}

			@Override
			public void onFacebookError(FacebookError e, Object state) {
			}
		});
		
		
	}


	private void loginToFacebook() {
		
		
		preferences = getPreferences(MODE_PRIVATE);
		
		String access_token = preferences.getString("access_token", null);

		long expires = preferences.getLong("access_expries", 0);
		
		if(access_token != null)
		{
			facebook.setAccessToken(access_token);
		}

		if(expires!=0)
		{
			facebook.setAccessExpires(expires);
		}
		
		
		if(facebook.isSessionValid())
		{
			try {

				facebook.logout(getApplicationContext());
				
				upDateButtonImage();

			}
			catch (MalformedURLException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}

			Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_SHORT).show();
		}

		else
		{

			facebook.authorize(MainActivity.this,new String[] {"email"},  new Facebook.DialogListener() {

				@Override
				public void onFacebookError(FacebookError e) {

					Toast.makeText(getApplicationContext(), "On Facebook Error", Toast.LENGTH_SHORT).show();

				}

				@Override
				public void onError(DialogError e) {

					Toast.makeText(getApplicationContext(), "On Error", Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onComplete(Bundle values) {

					Editor editor = preferences.edit();
					editor.putString("access_token",facebook.getAccessToken() );
					editor.putLong("access_expires", facebook.getAccessExpires());
					editor.commit();
					upDateButtonImage();
				}

				@Override
				public void onCancel() {
					Toast.makeText(getApplicationContext(), "On Cancel", Toast.LENGTH_SHORT).show();
				}

			});


			Toast.makeText(getApplicationContext(), "Logged IN", Toast.LENGTH_SHORT).show();
		}
	}


	/***
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 * data should not be null
	 * 
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		facebook.authorizeCallback(requestCode, resultCode, data);
	}

}
