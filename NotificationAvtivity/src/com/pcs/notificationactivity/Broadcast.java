package com.pcs.notificationactivity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.pcs.notificationavtivity.R;

public class Broadcast extends BroadcastReceiver{
	private static final int REQ_A=101;
	@Override
	public void onReceive(Context context, Intent intent) {
		NotificationManager notificationManger = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context)
		.setSmallIcon(R.drawable.ic_launcher)
		.setContentText("Hi New Message")
		.setContentTitle("New Message");
		
		Intent resultIntent = new Intent(context,MainActivity.class);
		
		
		PendingIntent pendingIntent = PendingIntent.getActivity(
				context, REQ_A, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		notifyBuilder.setContentIntent(pendingIntent);
		
		notifyBuilder.setAutoCancel(true);
		resultIntent.setFlags(resultIntent.FLAG_ACTIVITY_CLEAR_TASK|resultIntent.FLAG_ACTIVITY_NEW_TASK);
		notificationManger.notify(REQ_A,notifyBuilder.build());
	}

}
