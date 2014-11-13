package com.pcs.instaspeak;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.pcs.constants.FacebookUserDetails;

public class APGovInstaSpeak extends Activity{
	
	private TextView fbUserNameTxt,fbUserName,fbMailTxt,fbMail,fbGenderTxt,fbGender,fbTokenTxt,fbToken,
					fbFirstNameTxt,fbFirstName,fbLastNameTxt,fbLastName,fbLocationTxt,fbLocation;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.loggedin);
		
		fbUserNameTxt = (TextView)findViewById(R.id.userName_txt);
		fbMailTxt = (TextView)findViewById(R.id.mail_txt);
		fbGenderTxt = (TextView)findViewById(R.id.gender_txt);
		fbTokenTxt = (TextView)findViewById(R.id.token_txt);
		fbFirstNameTxt = (TextView)findViewById(R.id.firstName_txt);
		fbLastNameTxt = (TextView)findViewById(R.id.lastName_txt);
		fbLocationTxt = (TextView)findViewById(R.id.location_txt);
		
		
		String userName = FacebookUserDetails.getUserName().toString();
		String mail = FacebookUserDetails.getEmail().toString();
		String token = FacebookUserDetails.getToken().toString();
		String gender = FacebookUserDetails.getGender().toString();
		String firstName = FacebookUserDetails.getFirstName().toString();
		String lastName = FacebookUserDetails.getLastName().toString();
		String location = FacebookUserDetails.getLocation().toString();
		
		if(userName!=null)
		{
			fbUserNameTxt.setText(userName);
			
		}
		if(mail!=null)
		{
			fbMailTxt.setText(mail);
			
		}
		if(firstName!=null)
		{
			fbFirstNameTxt.setText(firstName);
			
		}
		if(lastName!=null)
		{
			fbLastNameTxt.setText(lastName);
			
		}
		if(gender!=null)
		{
			fbGenderTxt.setText(gender);
			
		}
		if(userName!=null)
		{
			fbUserNameTxt.setText(userName);
			
		}
		if(token!=null)
		{
			fbTokenTxt.setText(token);
			
		}
		
		if(location!=null)
		{
			fbLocationTxt.setText(location);
			
		}
		
		
	}

}
