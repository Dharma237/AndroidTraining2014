package com.pcs.notificationactivity;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.pcs.notificationavtivity.R;

public class MainActivity extends Activity{
	private Button messageBtn;
	private Button downloadBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//sets layout to the con
		setContentView(R.layout.activity_main);
		messageBtn = (Button)findViewById(R.id.msg_btn);
		downloadBtn = (Button)findViewById(R.id.download_btn);
		/**
		 * onclick listener to the messageBtn
		 * When button clicks it will navigate to the BroadCast class
		 */ 
		messageBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,Broadcast.class);
				intent.setAction("com.pcs.BroadCast");
				sendBroadcast(intent);
			}
		});
		/**
		 * onclick listener to the downloadBtn
		 * When button clicks it will navigate to the Download class
		 */
		downloadBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,Downloader.class);
				intent.setAction("com.pcs.Download");
				sendBroadcast(intent);
			}
		});
	}

}