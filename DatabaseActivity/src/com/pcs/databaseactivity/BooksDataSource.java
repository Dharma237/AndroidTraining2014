package com.pcs.databaseactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

public class BooksDataSource {

	private MySQLiteHelper databaseHelper;
	private SQLiteDatabase database;
	private ArrayAdapter<Book> adapter;
	private Book book;
	private Context context;

	//storing books table columns
	private String[] BOOK_TABLE_COLUMNS = { MySQLiteHelper.BOOK_NAME, 
			MySQLiteHelper.BOOK_ID, MySQLiteHelper.BOOK_AUTHOR };


	//constructor for BooksDataSource
	public BooksDataSource(MainActivity mainActivity) {
		context = mainActivity;
		databaseHelper = new MySQLiteHelper(mainActivity);
	}

	//opening and giving write permissions to database
	public void open() throws SQLException{
		database = databaseHelper.getWritableDatabase();
	}

	//creating NewBook
	public Book createBook(Book book) {

		ContentValues values = new ContentValues();

		values.put(MySQLiteHelper.BOOK_NAME, book.getBookName());
		values.put(MySQLiteHelper.BOOK_ID, book.getBookId());
		values.put(MySQLiteHelper.BOOK_AUTHOR, book.getBookAuthor());

		//Storing Book_details in database
		database.insert(MySQLiteHelper.TABLE_NAME, null, values);

		return book;

	}

	/****
	 * This code is for ArrayAdapter<>
	 * 
	 * 
	public cursor showAllBooks() {


		ArrayList<Book> books = new ArrayList<Book>();

		//cursor for retrieving all the book table columns
		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
		        BOOK_TABLE_COLUMNS, null, null, null, null, null);

		//pointing to first of list if cursor is not null
		if(cursor!=null && cursor.moveToFirst())
		{
			do{
				//creating object Book
				book = new Book();

				//adding book_details to the book object
				book.setBookName(cursor.getString(0));
				book.setBookId(cursor.getString(1));
				book.setBookAuthor(cursor.getString(2));

				//adding book object to the arrayList
				books.add(book);



	 * LayoutInflater layoutInflater = LayoutInflater.from(context);
	 * view = layoutInflater.inflate(R.layout.sample_list_item_l,null);

				TextView nameTxt= (TextView)view.findViewById(R.id.name_txt);
				TextView idTxt = (TextView)view.findViewById(R.id.id_txt);
				TextView authorTxt = (TextView)view.findViewById(R.id.author_txt);

				nameTxt.setText(MySQLiteHelper.BOOK_NAME);
				idTxt.setText(MySQLiteHelper.BOOK_ID);
				authorTxt.setText(MySQLiteHelper.BOOK_AUTHOR);
				return view;
				//iterating cursor till end of list
			while(cursor.moveToNext());

			cursor.close();
						return cursor;

	}

				}
	 */	


	/***
	 * Arguments are Table_Columns
	 * @return cursor object
	 */
	
	public Cursor getBooks() {

		//assigining table column values to the cursor 
		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
				BOOK_TABLE_COLUMNS, null, null, null, null, null);

		return cursor;
	}
	
	/***
	 * Deletes all records in the table
	 * @return true if records are present in tables else false
	 */
	
	public boolean deleteAllBooks() {

		int deleteBooks = 0;

		deleteBooks = database.delete(MySQLiteHelper.TABLE_NAME,null,null);

		return deleteBooks>0;

	}

}