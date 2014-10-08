package com.pcs.welcomescreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity{
	
	private EditText mailEdt,pwdEdt;
	private Button loginBtn;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//layout file
		setContentView(R.layout.activity_main);
		loginBtn = (Button)findViewById(R.id.login_btn);
		
		
		//onclick listener to the loginBtn
		loginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//referencing to layout elements
				mailEdt = (EditText)findViewById(R.id.mail_edt);
				pwdEdt = (EditText)findViewById(R.id.pwd_edt);
				
				
				//taking entered values and storing in strings
				String mail = mailEdt.getText().toString().trim();
				String pwd = pwdEdt.getText().toString().trim();
				
				//checks entered details are null or not
				boolean mMail = TextUtils.isEmpty(mail);
				boolean mPwd = TextUtils.isEmpty(pwd);
				
				String userMail = "pcs@paradigmcreatives.com".trim();
				String userPwd = "pcs@123".trim();
				
				
				String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
				
				/***
				 * Params are mail and password
				 * mail and password should not be null
				 * mail should be pcs@paradigmcreavies.com
				 * password should be pcs@123
				 * 
				if(mail.matches(emailPattern) && pwd.matches(emailPattern))
				{
				if(!mMail && mPwd)
				{
				 */
				
				
					if(mail.equals(userMail)&& 
							pwd.equals(userPwd))
					{
						//if entered details are correct then it will be navigate to another screen WelcomeScree.java
						Intent intent = new Intent(MainActivity.this,WelcomeScreen.class);
						
						//starting another activity through intent
						startActivity(intent);
					}
					else
						Toast.makeText(MainActivity.this, getResources().getString(R.string.wrong_credentials),
								Toast.LENGTH_LONG).show();
				}

		/***
		 * 
		 * 	else
					Toast.makeText(MainActivity.this, getResources().getString(R.string.empty_credentials),
							Toast.LENGTH_LONG).show();
		
		 */
		
		});
		
	}

}
