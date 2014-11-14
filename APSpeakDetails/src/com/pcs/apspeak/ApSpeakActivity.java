package com.pcs.apspeak;

import com.pcs.constants.FacebookUserDetails;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ApSpeakActivity extends Activity{
	
	private TextView fbUserNameTxt,fbIdTxt,fbLocaleTxt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.loggedin);
		
		fbUserNameTxt = (TextView)findViewById(R.id.userName_txt);
		fbIdTxt = (TextView)findViewById(R.id.id_txt);
		fbLocaleTxt = (TextView)findViewById(R.id.locale_txt);
		
		
		fbUserNameTxt.setText(FacebookUserDetails.getUserName().toString());
		fbIdTxt.setText(FacebookUserDetails.getToken().toString());
		fbLocaleTxt.setText(FacebookUserDetails.getLocation().toString());
	}

}
