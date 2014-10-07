package com.pcs.notificationactivity;



import com.pcs.notificationavtivity.R;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class Downloader extends BroadcastReceiver implements Runnable{
	private static final int REQ_B=102;
	private NotificationCompat.Builder notifyBuilder;
	public NotificationManager notificationManger;

	@Override
	public void onReceive(Context context, Intent intent) {
		//creating Notification Manager
		notificationManger = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		//creating Notification Builder
		 notifyBuilder = new NotificationCompat.Builder(context)
		.setSmallIcon(R.drawable.download)
		.setContentTitle(context.getResources().getString(R.string.download_title))
		.setContentText(context.getResources().getString(R.string.download_txt));
		
		//Intent for navigating from this class to ManiActivity
		Intent resultIntent = new Intent(context,MainActivity.class);


		PendingIntent pendingIntent = PendingIntent.getActivity(context, REQ_B, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		//setting PendingIntent to the NotifyBuilder
		notifyBuilder.setContentIntent(pendingIntent);
		
		//Returns to the MainActivity when we see our notification
		notifyBuilder.setAutoCancel(true);
		resultIntent.setFlags(resultIntent.FLAG_ACTIVITY_CLEAR_TASK|resultIntent.FLAG_ACTIVITY_NEW_TASK);
		
		
		new Thread(this).start();
		
		
	}

	@Override
	public void run() {
				int value;
				for(value=1;value<100;value=value+15)
				{
					//sets Progress bar
					notifyBuilder.setProgress(100, value, false);
					
					notificationManger.notify(REQ_B,notifyBuilder.build());
					try {
                        // Sleep for 5 seconds
                        Thread.sleep(5*1000);
                    } catch (InterruptedException e) {
                    }

				}
				//if download completes then this text will be appended to notification
				 notifyBuilder.setContentText("Download complete")
		         // Removes the progress bar
		                 .setProgress(0,0,false);
				 
				//showing Notification
				notificationManger.notify(REQ_B,notifyBuilder.build());	
			}
		}
