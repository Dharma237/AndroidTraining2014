package com.pcs.apspeak;

import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
public class MainFragment extends Fragment {

	private UiLifecycleHelper uiHelper;

	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(final Session session, final SessionState state, final Exception exception) {
			onSessionStateChange(session, state, exception);
		}
	};


	private TextView userInfoTextView;
	private String fbAccessToken;
	private ImageView userProfileImg;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container,
			Bundle savedInstanceState) {


		View view = inflater.inflate(R.layout.activity_main, container, false);

		LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);

		authButton.setFragment(this);

		authButton.setReadPermissions(Arrays.asList("user_location", "user_birthday", "user_likes"));

		userInfoTextView = (TextView) view.findViewById(R.id.userInfoTextView);
		
		userProfileImg = (ImageView)view.findViewById(R.id.user_profile_img);

		return view;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		uiHelper = new UiLifecycleHelper(getActivity(), callback);

		uiHelper.onCreate(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();

		Session session = Session.getActiveSession();
		if (session != null &&
				(session.isOpened() || session.isClosed()) ) {

			onSessionStateChange(session, session.getState(), null);
		}

		uiHelper.onResume();

	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		uiHelper.onActivityResult(requestCode, resultCode, data);
	}


	@Override
	public void onPause() {
		super.onPause();

		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		
		uiHelper.onDestroy();


	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		uiHelper.onSaveInstanceState(outState);
	}

	@SuppressWarnings("deprecation")
	private void onSessionStateChange(final Session session, SessionState state, Exception exception) {


		if (state.isOpened()) {

			userInfoTextView.setVisibility(View.VISIBLE);

			
			// Request user data and show the results
			Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
				@Override
				public void onCompleted(GraphUser user, Response response) {

					if (user != null) {
						
						userInfoTextView.setText(buildUserInfoDisplay(user,fbAccessToken));
					}
				}

			});
		} 

		else if (state.isClosed()) {
			Toast.makeText(getActivity(), "Please Login", Toast.LENGTH_SHORT).show();
			userInfoTextView.setVisibility(View.GONE);
		}
	}
	private String buildUserInfoDisplay(GraphUser user,String token) {
		
		
		
		StringBuilder userInfo = new StringBuilder("");

		userInfo.append(String.format("Name: %s\n\n",
				user.getName()));


		userInfo.append(String.format("Birthday: %s\n\n",
				user.getBirthday()));


		userInfo.append(String.format("Id: %s\n\n",
				user.getId()));


		userInfo.append(String.format("Locale: %s\n\n",
				user.getProperty("locale")));

		userInfo.append(String.format("Link: %s\n\n",
				user.getLink()));


		userInfo.append(String.format("Gender:%s\n\n",user.asMap().get("gender").toString()));
		
		userInfo.append(String.format("Token:%s\n\n",token));
		
		return userInfo.toString();

	}

}