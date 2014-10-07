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
		
		//creating Notification Manager
		NotificationManager notificationManger = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		
		/***
		 * creating Notification Builder
		 * and assigning icon, contentTitle,and Text
		 * 
		 */
		NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context)
		.setSmallIcon(R.drawable.ic_launcher)
		.setContentTitle(context.getResources().getString(R.string.msg_title))
		.setContentText(context.getResources().getString(R.string.msg_txt));
		
		//Intent for navigating from this class to ManiActivity
		Intent resultIntent = new Intent(context,MainActivity.class);
		
		//pending Intent is used for to put notification in the notification drawer until we tap on it
		PendingIntent pendingIntent = PendingIntent.getActivity(
				context, REQ_A, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		//setting PendingIntent to the NotifyBuilder
		notifyBuilder.setContentIntent(pendingIntent);
		
		//Returns to the MainActivity when we see our notification
		notifyBuilder.setAutoCancel(true);
		
		resultIntent.setFlags(resultIntent.FLAG_ACTIVITY_CLEAR_TASK|resultIntent.FLAG_ACTIVITY_NEW_TASK);
		
		//showing Notification
		notificationManger.notify(REQ_A,notifyBuilder.build());
	}

}
