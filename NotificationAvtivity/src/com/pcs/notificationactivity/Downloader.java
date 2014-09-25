package com.pcs.notificationactivity;

import com.pcs.notificationavtivity.R;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class Downloader extends BroadcastReceiver{
	private static final int REQ_B=102;
	NotificationManager notificationManger;

	@Override
	public void onReceive(Context context, Intent intent) {
		notificationManger = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

		final NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context)
		.setSmallIcon(R.drawable.ic_launcher)
		.setContentText("Hi New Message")
		.setContentTitle("New Message");
		Intent resultIntent = new Intent(context,MainActivity.class);


		PendingIntent pendingIntent = PendingIntent.getActivity(context, REQ_B, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		notifyBuilder.setContentIntent(pendingIntent);

		notifyBuilder.setAutoCancel(true);
		resultIntent.setFlags(resultIntent.FLAG_ACTIVITY_CLEAR_TASK|resultIntent.FLAG_ACTIVITY_NEW_TASK);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int value;
				for(value=1;value<100;value=value+15)
				{
					notifyBuilder.setProgress(100, value, false);
					notificationManger.notify(REQ_B,notifyBuilder.build());
					try {
                        // Sleep for 5 seconds
                        Thread.sleep(5*1000);
                    } catch (InterruptedException e) {
                    }

				}
				 notifyBuilder.setContentText("Download complete")
		         // Removes the progress bar
		                 .setProgress(0,0,false);

				notificationManger.notify(REQ_B,notifyBuilder.build());	
			}
		}).start();
		
		
	}

}
