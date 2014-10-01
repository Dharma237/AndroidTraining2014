package com.pcs.databaseactivity;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity{
	private BooksDataSource booksSource;
	private Book book;
	private ListView listView;
	private ArrayList<Book> books;
	private SimpleCursorAdapter simpleAdapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		listView = (ListView) findViewById(R.id.list_view);
		
		//reference to the BooksDataSource Class
		booksSource = new BooksDataSource(this);
		
		//opening Database in BooksDataSource
		booksSource.open();
		
	}
	
	/***
	 * Creating AddBook
	 * Arguments Book_names, Book_id, Book_author
	 * Arguments should not be null
	 * Books_id is primary key
	 * @param view
	 */
	public void addBook(View view)
	{

		//Referring EditTexts in the layout 
		EditText bookNameEdt = (EditText) findViewById(R.id.bookName_edt);
		EditText bookIdEdt = (EditText) findViewById(R.id.bookId_edt);
		EditText bookAuthorEdt = (EditText) findViewById(R.id.bookAuthor_edt);
		
		//Storing EditText Values in to the strings
		String nameEdt = bookNameEdt.getText().toString();
		String idEdt = bookIdEdt.getText().toString();
		String authorEdt = bookAuthorEdt.getText().toString();
		
		//Checking for EditTexts null or not
		boolean name = TextUtils.isEmpty(nameEdt);
		boolean id = TextUtils.isEmpty(idEdt);
		boolean author = TextUtils.isEmpty(authorEdt);
		
		if(!name && !id && !author)
		{
			//creating Book Object
			book = new Book();
			 
			//adding Attribute to the book Object
			book.setBookName(nameEdt);
			book.setBookId(idEdt);
			book.setBookAuthor(authorEdt);
			
			//calling CreateBook() method in BookDataSource
			booksSource.createBook(book);
			Toast.makeText(MainActivity.this, getResources().getString(R.string.book_inserted), Toast.LENGTH_LONG).show();
		}
		
		else
			Toast.makeText(MainActivity.this, getResources().getString(R.string.book_error), Toast.LENGTH_LONG).show();
	}
	
	public void showAllBooks(View view)
	{
		 	//cursor having all table values
			 Cursor cursor = booksSource.getBooks();
			 
			 if(cursor!=null)
			 {
				 //adding database values to the SimpleCursorAdapter
				 SimpleCursorAdapter simpleAdapter = new SimpleCursorAdapter(MainActivity.this, R.layout.list_display, cursor, 
					 new String[]{ "_name", "_id", "_author"}, 
					 new int[]{R.id.name_txt,R.id.id_txt,R.id.author_txt});
				 
				 //displaying in listview
				 listView.setAdapter(simpleAdapter);
			 }
			 
			 else
				 Toast.makeText(MainActivity.this, getResources().getString(R.string.nobooks), Toast.LENGTH_LONG).show();
		 
	}
	/**
	 * deletes all table
	 * 
	 * @param view
	 */
	public void deleteAllBooks(View view)
	{
		booksSource.deleteAllBooks();
		
		Toast.makeText(MainActivity.this, getResources().getString(R.string.books_deleted), Toast.LENGTH_LONG).show();
	}
}