package com.pcs.widgetandroid;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.pcs.util.WidgetUtil;


public class WidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

		remoteViews.setTextViewText(R.id.title, context.getResources().getString(R.string.title));
		remoteViews.setTextViewText(R.id.versions, context.getResources().getString(R.string.versions));
		remoteViews.setImageViewResource(R.id.version_image, R.drawable.version1);

		remoteViews.setOnClickPendingIntent(R.id.widget_button, buildButtonPendingIntent(context));
		
		pushWidgetUpdate(context, remoteViews);
	}

	public static PendingIntent buildButtonPendingIntent(Context context) {
		Intent intent = new Intent();
		intent.setAction(WidgetUtil.CHANGE_VERSION);
		return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}

	public static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {
		ComponentName myWidget = new ComponentName(context, WidgetProvider.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		manager.updateAppWidget(myWidget, remoteViews);		
	}
}
