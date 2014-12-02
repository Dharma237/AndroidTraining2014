package com.pcs.widgetandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.pcs.util.WidgetUtil;
import com.pcs.widgetandroid.R;

public class WidgetIntentReceiver extends BroadcastReceiver {

	private static int versionNumber = 0;
	private static int versionName =0;
	private String versionNames[]=null;
	
	private int images[] = new int[]{
			R.drawable.version1,R.drawable.version2,R.drawable.version3,
			R.drawable.version4,R.drawable.version5,R.drawable.version6,
			R.drawable.version7,R.drawable.version8,R.drawable.version9,
			R.drawable.version10,R.drawable.version11,R.drawable.version12,
	};

	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(WidgetUtil.CHANGE_VERSION)){

		}
		updateWidgetPictureAndButtonListener(context);
	}

	private void updateWidgetPictureAndButtonListener(Context context) {
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

		remoteViews.setTextViewText(R.id.title, context.getResources().getString(R.string.title));
		remoteViews.setTextViewText(R.id.versions, getVersionName(context));
		remoteViews.setImageViewResource(R.id.version_image, getImageToSet(context));

		remoteViews.setOnClickPendingIntent(R.id.widget_button, WidgetProvider.buildButtonPendingIntent(context));

		WidgetProvider.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
	}

	private CharSequence getVersionName(Context context) {

		versionName++;

		versionNames = context.getResources().getStringArray(R.array.android_versions);

		if(versionName>=versionNames.length)
		{
			versionName =0;
		}

		return versionNames[versionName];
	}

	private int getImageToSet(Context context) {


		versionNumber++;

		Log.e("Clicks::", ""+versionNumber);

		if(versionNumber>=images.length)
		{
			versionNumber=0;
		}
		return images[versionNumber];
	}
}
