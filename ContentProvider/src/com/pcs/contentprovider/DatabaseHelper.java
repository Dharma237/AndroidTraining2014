package com.pcs.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	
	public static final String DATABASE_NAME ="contactDetails.db";
	public static final String TABLE_NAME = "contactDetails";
	public static final String ID = "_id";
	public static final String NAME = "_name";
	public static final String ADDRESS ="_address";
	public static final String PHONE ="_phone";
	public static final String MAIL = "_mail";
	public static final int DATABASE_VERSION = 1;
	
	public static final String CREATE_TABLE = "CREATE TABLE  " + TABLE_NAME +
			"(" +
				  ID + " 	integer primary key autoincrement," +
				NAME +    " text not null, " +
				ADDRESS + " text not null," +
				PHONE +   " text not null," +
				MAIL +    "  text not null" +
				
			");";
	

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
		
	}

}

