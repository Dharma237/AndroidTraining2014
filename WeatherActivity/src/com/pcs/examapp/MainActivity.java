package com.pcs.examapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	protected EditText userNameEdt;
	protected EditText passwordEdt;
	
	protected Button loginBtn;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //referencing to the xml elements
        loginBtn =(Button)findViewById(R.id.login_btn);
        userNameEdt = (EditText)findViewById(R.id.username_edt);
        passwordEdt = (EditText)findViewById(R.id.password_edt);
        
        
        
        
        //onclick listener to the loginBtn		
        loginBtn.setOnClickListener(new OnClickListener() {
			
        	
        	//taking entered details
            String userName = userNameEdt.getText().toString();
            String passWord = passwordEdt.getText().toString();
            
			@Override
			public void onClick(View v) {
				
				//intent will pass to the next java file if username and password are not null
				if(userName!=null && passWord!=null){
					Intent intent_login = new Intent(MainActivity.this,WeatherActivity.class);
				    startActivity(intent_login);
				
				}
				//if Credentials are null then Toast will be printed
				else{
					Toast.makeText(MainActivity.this, getResources().getString(R.string.loginMsg), Toast.LENGTH_LONG).show();
				}
				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

   
}
