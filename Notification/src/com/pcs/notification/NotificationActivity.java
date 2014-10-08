package com.pcs.notification;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class NotificationActivity extends Activity {
 @Override
 public void onCreate(Bundle savedInstanceState) {
	 Intent intent = new Intent(NotificationActivity.this,Reciever.class);
	 intent.setAction("com.pcs.android.intent.action.BOOT_COMPLETED");
	 sendBroadcast(intent);
	
  super.onCreate(savedInstanceState);
  
  setContentView(R.layout.activity_main);
 }

 
}
