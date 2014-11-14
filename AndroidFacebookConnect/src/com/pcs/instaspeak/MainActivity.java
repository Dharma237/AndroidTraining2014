package com.pcs.instaspeak;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.pcs.constants.FacebookUserDetails;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity implements OnClickListener {

	// Facebook APP ID
	private static String APP_ID = "587075374731392";

	private Facebook facebook = new Facebook(APP_ID);
	private AsyncFacebookRunner mAsyncRunner;
	String FILENAME = "AndroidSSO_data";
	private SharedPreferences mPrefs;
	private boolean facebook_installed = true;

	// Buttons
	Button FacebookLoginBtn, FacebookProfileBtn,logOutBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/***
		 * Layout for the MainActivity
		 */
		setContentView(R.layout.activity_main);

		boolean instalelled = appInstalledOrNot("com.facebook.katana");

		if (instalelled) {
			Intent launchIntent = getPackageManager()
					.getLaunchIntentForPackage("com.facebook.katana");
			startActivity(launchIntent);

		} else {

			facebook_installed = false;
		}

		// btnShowAccessTokens = (Button)
		// findViewById(R.id.btn_show_access_tokens);
		FacebookLoginBtn = (Button) findViewById(R.id.btn_fblogin);

		mAsyncRunner = new AsyncFacebookRunner(facebook);

		FacebookLoginBtn.setOnClickListener(this);

	}

	private boolean appInstalledOrNot(String uri) {

		PackageManager manager = getPackageManager();

		boolean app_installed = false;

		try {

			manager.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
			app_installed = true;

		} catch (NameNotFoundException e) {

			app_installed = false;
			e.printStackTrace();
		}

		return app_installed;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btn_fblogin:

			loginToFacebook();

			break;


		default:
			break;

		}

	}

	public void loginToFacebook() {

		if (facebook_installed == false) {
			Toast.makeText(getApplicationContext(),
					getResources().getString(R.string.fb_not_installed),
					Toast.LENGTH_SHORT).show();
		}

		mPrefs = getPreferences(MODE_PRIVATE);
		String access_token = mPrefs.getString("access_token", null);
		long expires = mPrefs.getLong("access_expires", 0);

		if (access_token != null) {

			facebook.setAccessToken(access_token);
			getProfileInformation();
			/*Intent intent = new Intent(this, APGovInstaSpeak.class);

			startActivity(intent);*/

		}

		if (expires != 0) {

			facebook.setAccessExpires(expires);
		}

		if (!facebook.isSessionValid()) {

			facebook.authorize(this,
					new String[] { "email", "publish_stream" },
					new DialogListener() {

				@Override
				public void onCancel() {

					Toast.makeText(
							getApplicationContext(),
							getResources().getString(R.string.app_name),
							Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onComplete(Bundle values) {

					SharedPreferences.Editor editor = mPrefs.edit();
					editor.putString("access_token",
							facebook.getAccessToken());
					editor.putLong("access_expires",
							facebook.getAccessExpires());
					editor.commit();

					getProfileInformation();

				}

				@Override
				public void onError(DialogError error) {

				}

				@Override
				public void onFacebookError(FacebookError fberror) {

				}

			});
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		facebook.authorizeCallback(requestCode, resultCode, data);



		Intent intent = new Intent(this, APGovInstaSpeak.class);
		startActivity(intent);

	}

	private void getProfileInformation() {

		mAsyncRunner.request("me", new RequestListener() {
			@Override
			public void onComplete(String response, Object state) {

				String json = response;

				Log.d("Profile", response);

				try {

					JSONObject profile = new JSONObject(json);
					FacebookUserDetails user = new FacebookUserDetails();
					if(profile.has("name"))
					{

						String name = profile.getString("name");
						user.setUserName(name);
						Log.e("Name:", name);
					}

					if(profile.has("mail"))
					{

						String email = profile.getString("email");

						user.setEmail(email);
						Log.e("mail:", email);
					}

					if(profile.has("first_name"))
					{
						String first_name = profile.getString("first_name");
						user.setFirstName(first_name);
					}

					if(profile.has("last_name"))
					{
						String last_name = profile.getString("last_name");
						user.setLastName(last_name);
					}

					if(profile.has("gender"))
					{
						String gender = profile.getString("gender");
						user.setGender(gender);
					}


					if(profile.has("locale"))
					{
						String locale = profile.getString("locale");
						user.setLocation(locale);
					}

					if(profile.has("id"))
					{
						String token = profile.getString("id");
						user.setToken(token);
					}
					Intent intent = new Intent(MainActivity.this, APGovInstaSpeak.class);

					startActivity(intent);
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
}
