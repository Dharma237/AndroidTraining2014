package com.pcs.notificationactivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.pcs.notificationavtivity.R;

public class MainActivity extends Activity{
	private Button notificationBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		notificationBtn = (Button)findViewById(R.id.notification_btn);
		notificationBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setAction("com.pcs.BroadCast");
				sendBroadcast(intent);
			}
		});
	}
	
	class Broadcast extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "HI", Toast.LENGTH_LONG).show();
	}

}
}