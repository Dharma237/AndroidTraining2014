package com.rgukt.csiiitn;

import java.io.IOException;
import java.net.MalformedURLException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;

public class MainActivity extends Activity implements OnClickListener{

	private ImageView login;
	private Facebook fb;
	SharedPreferences preferences;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		login = (ImageView) findViewById(R.id.login);

		String APP_ID = getResources().getString(R.string.app_id);
		
		preferences = getPreferences(MODE_PRIVATE);
		
		String access_token = preferences.getString("access_token", null);
		
		long expires = preferences.getLong("access_expries", 0);
		
		if(access_token != null)
		{
			fb.setAccessToken(access_token);
		}
		
		if(expires!=0)
		{
			fb.setAccessExpires(expires);
		}

		fb = new Facebook(APP_ID);

		login.setOnClickListener(this);

		upDateButtonImage();
	}


	@SuppressWarnings("deprecation")
	private void upDateButtonImage() {
		if(fb.isSessionValid())
		{
			login.setImageResource(R.drawable.logout);
		}
		else
			login.setImageResource(R.drawable.login);
	}


	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {

		if(fb.isSessionValid())
		{
			try {

				fb.logout(getApplicationContext());
				upDateButtonImage();

			}
			catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_SHORT).show();
		}

		else
		{

			fb.authorize(MainActivity.this,new String[] {"email"},  new Facebook.DialogListener() {

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
					editor.putString("access_token",fb.getAccessToken() );
					editor.putLong("access_expires", fb.getAccessExpires());
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
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		
		fb.authorizeCallback(requestCode, resultCode, data);
	}

}
