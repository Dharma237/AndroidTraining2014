package com.pcs.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

public class Reciever extends BroadcastReceiver{
    private static final int REQ_A=101;
 
	@Override
	public void onReceive(Context context, Intent intent) {
		
		//creating Notification Manager
		NotificationManager notificationManger = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		//creating Notification Builder
		Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.workspace_logo);
		
		NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context)

	     .setContentTitle("5 New mails from " + intent.toString())
	     .setContentText(context.getResources().getString(R.string.title))
	     .setSmallIcon(R.drawable.ic_launcher)
	     .setLargeIcon(icon);
		  NotificationCompat.InboxStyle inboxStyle =
		             new NotificationCompat.InboxStyle();

		      String[] events = new String[6];
		      events[0] = new String("This is first line....");
		      events[1] = new String("This is second line...");
		      events[2] = new String("This is third line...");
		      events[3] = new String("This is 4th line...");
		      events[4] = new String("This is 5th line...");
		      events[5] = new String("This is 6th line...");
		      
		      for (int i=0; i < events.length; i++) {

		          inboxStyle.addLine(events[i]);
		       }
		       notifyBuilder.setStyle(inboxStyle);
		      // Sets a title for the Inbox style big view
		      inboxStyle.setBigContentTitle("Big Title Details:");
		      
		      Intent resultIntent = new Intent(context,NotificationActivity.class);
		    
				PendingIntent pendingIntent = PendingIntent.getActivity(
				context, REQ_A, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
				
		notifyBuilder.setContentIntent(pendingIntent);
		//Returns to the MainActivity when we see our notification
		resultIntent.setFlags(resultIntent.FLAG_ACTIVITY_CLEAR_TASK|resultIntent.FLAG_ACTIVITY_NEW_TASK);
		//showing Notification
		notificationManger.notify(REQ_A,notifyBuilder.build());
		
	

		}
    }