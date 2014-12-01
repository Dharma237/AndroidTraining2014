package com.pcs.widgetDemo;

import com.pcs.utils.WidgetUtils;
import com.pcs.widgetDemo.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetIntentReceiver extends BroadcastReceiver {

	private static int clickCount = 0;
	private String msg[] = null;

	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(WidgetUtils.CHANGE_VERSION)){
			updateWidgetPictureAndButtonListener(context);
		}
	}

	private void updateWidgetPictureAndButtonListener(Context context) {
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_main);

		remoteViews.setTextViewText(R.id.android_title, context.getResources().getString(R.string.title));
		remoteViews.setTextViewText(R.id.android_versions, getVersionName(context));

		//REMEMBER TO ALWAYS REFRESH YOUR BUTTON CLICK LISTENERS!!!
		remoteViews.setOnClickPendingIntent(R.id.widget_button, MainActivity.buildButtonPendingIntent(context));

		MainActivity.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
	}

	private CharSequence getVersionName(Context context) {

		clickCount++;
		msg = context.getResources().getStringArray(R.array.android_versions);
		
		if(clickCount>=msg.length)
		{
			clickCount=0;
		}


		return msg[clickCount];
	}

}
