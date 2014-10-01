package com.pcs.databaseactivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper{
	
	private static final String DATABASE_NAME = "books.db"; 	//database Name
	public static final String TABLE_NAME = "book_details";		//table_name
	private static final int VERSION = 1;						//version
	public static final String BOOK_NAME = "_name";				//Attributes of Books
	public static final String BOOK_ID = "_id";
	public static final String BOOK_AUTHOR = "_author";
	
	//creating table named book_details
	private static final String CREATE_TABLE = "CREATE TABLE " +  TABLE_NAME +
			"(" + BOOK_ID  + " integer primary key autoincrement," +
			    BOOK_NAME  + " text not null," +
			    BOOK_AUTHOR  + " text not null" + ");";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//executing table named book_details
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//tables drops if exists and helpful in future versions
		db.execSQL("DROP IF EXISTS" + TABLE_NAME);
		
		//executing table_named book_details
		onCreate(db);
		
	}
	
}
