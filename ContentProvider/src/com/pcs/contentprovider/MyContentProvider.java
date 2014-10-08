package com.pcs.contentprovider;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class MyContentProvider extends ContentProvider{
	
	public static final String PROVIDER_NAME = "com.pcs.contentprovider.MyContentProvider";
	public static final String URL = "content://"+ PROVIDER_NAME + "/person";
	public static final Uri CONTENT_URI = Uri.parse(URL);
	public static final String ID = "_id";
	public static final String NAME = "_name";
	public static final String MAIL = "_mail";
	public static final String ADDRESS = "_address";
	public static final String PHONE = "_phone";
	
	public static final int URI_CODE = 1;
	public static UriMatcher uriMatcher=null;
	
	public static HashMap<String,String> values;
	public SQLiteDatabase db;
	public DatabaseHelper dbHelper;
	
	static{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(PROVIDER_NAME, "person", URI_CODE);
		uriMatcher.addURI(PROVIDER_NAME, "person/*", URI_CODE);
	}
	
	
	
	//onCreate Method
	@Override
	public boolean onCreate() {
		
		Context context = getContext();
		dbHelper = new DatabaseHelper(context);
		db = dbHelper.getWritableDatabase();
		
		if(db!=null)
		{
			return true;
		}
		else
			return false;
	}

	
	
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(DatabaseHelper.TABLE_NAME);
		
		
		switch (uriMatcher.match(uri)) {
		
		case URI_CODE:
			
			queryBuilder.setProjectionMap(values);
			
			break;

		default:
			
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		
		//sortOrder of rows in the table
		if(sortOrder == null || sortOrder ==" ")
		{
			sortOrder = DatabaseHelper.NAME;
		}
		
		//Cursor value
		
		Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		
		ContentResolver contentResolver = getContext().getContentResolver();
		
		cursor.setNotificationUri(contentResolver, uri);
		
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		
		switch (uriMatcher.match(uri)) {
		case URI_CODE:
			     
			return "vnd.android.cursor.dir/cte";
			
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		long rowId = db.insert(dbHelper.TABLE_NAME, "", values);
		
		if(rowId>0)
		{
			Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(_uri, null);
			return _uri;
		}
		
		throw new IllegalArgumentException("Failed to Add Record " + uri);
		
		
	}

	/***
	 * params are uri,selection,selectionArgs
	 * deletes whole table
	 * 
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {

		int count = 0;
		
		switch (uriMatcher.match(uri)) {
		
		case URI_CODE:
			
			 count = db.delete(dbHelper.TABLE_NAME, selection, selectionArgs);
			
			break;

		default:
			
			throw new IllegalArgumentException("Unknow URI " + uri);
		}
		
		getContext().getContentResolver().notifyChange(uri, null);
		
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		
		int count=0;
		
		switch (uriMatcher.match(uri)) {
		case URI_CODE:
			
			count = db.update(DatabaseHelper.TABLE_NAME, values, selection, selectionArgs);
			
			break;

		default:
			
			throw new IllegalArgumentException("Unknow URI " + uri);
		}
		
		getContext().getContentResolver().notifyChange(uri,null);
		
		return count;
	}
	
	
}
