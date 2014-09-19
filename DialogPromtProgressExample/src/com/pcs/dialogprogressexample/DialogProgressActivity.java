package com.pcs.dialogprogressexample;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.pcs.customadapterlist.CustomAdapter;
import com.pcs.dialogclassexample.R;
import com.pcs.user.User;

public class DialogProgressActivity extends Activity{
	private ListView listView;
	private Button clickBtn;
	private Button enterBtn;
	private Button cancelBtn;
	private EditText timeEdt;
	private EditText dateEdt;
	private EditText nameEdt;
	private EditText mailEdt;
	private EditText phoneEdt;
	private AlertDialog.Builder builder;
	private LayoutInflater inflater;
	private AlertDialog alertDialog;
	private ArrayList<User> users;
	private User user;
	private CustomAdapter adapter;
	private Button refreshBtn;
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		clickBtn = (Button)findViewById(R.id.click_btn);
		listView = (ListView)findViewById(R.id.list_txt);
		users = new ArrayList<User>();
		user = new User();

		/***
		 * Dialog will appear when user clicks on clickBtn
		 * Dialog Contains 2 EditTexts and 2 Buttons(enterBtn,cancelBtn)
		 */

		clickBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				inflater = LayoutInflater.from(DialogProgressActivity.this);

				View dialogView = inflater.inflate(R.layout.inputdetailsdialog, null);

				builder = new AlertDialog.Builder(DialogProgressActivity.this);
				adapter = new CustomAdapter(DialogProgressActivity.this, users);
				builder.setView(dialogView);

				nameEdt = (EditText)dialogView.findViewById(R.id.name_edt);
				mailEdt = (EditText)dialogView.findViewById(R.id.mail_edt);
				phoneEdt = (EditText)dialogView.findViewById(R.id.phone_edt);
				enterBtn = (Button)dialogView.findViewById(R.id.enter_btn);
				cancelBtn = (Button)dialogView.findViewById(R.id.cancel_btn);
				timeEdt = (EditText)dialogView.findViewById(R.id.time_edt);
				dateEdt = (EditText)dialogView.findViewById(R.id.date_edt);
				refreshBtn = (Button)dialogView.findViewById(R.id.refresh_btn);
				progressBar = new ProgressBar(DialogProgressActivity.this);
				progressBar = (ProgressBar)dialogView.findViewById(R.id.progress_bar );

				/***
				 * displays Date when User Clicks on dateEdt TextField 
				 * 
				 */
				nameEdt.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						refresh();
						
					}
				});
				mailEdt.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						refresh();
						
					}
				});
				phoneEdt.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						refresh();
						
					}
				});
				dateEdt.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Calendar currentDate = Calendar.getInstance();
						int Year = currentDate.get(Calendar.YEAR);
						int Month = currentDate.get(Calendar.MONTH);
						int Day = currentDate.get(Calendar.DAY_OF_MONTH);


						DatePickerDialog datePicker = new DatePickerDialog(DialogProgressActivity.this, new OnDateSetListener() {
							public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {

								selectedmonth = selectedmonth +1;
								dateEdt.setText(selectedday + "/" + selectedmonth+"/"+selectedyear);
							}
						}, Year, Month, Day);
						datePicker.setTitle(getResources().getString(R.string.date_title));
						datePicker.show();
						refresh();
					}
					
				});

				/***
				 * displays Time  when User Clicks on timeEdt TextField 
				 * 
				 */
				timeEdt.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						Calendar currentTime = Calendar.getInstance();
						int hour = currentTime.get(Calendar.HOUR_OF_DAY);
						int minute = currentTime.get(Calendar.MINUTE);
						TimePickerDialog timePicker = new TimePickerDialog(DialogProgressActivity.this, new TimePickerDialog.OnTimeSetListener() {
							@Override
							public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
								timeEdt.setText( selectedHour + ":" + selectedMinute);
							}
						}, hour, minute, true);
						timePicker.setTitle(getResources().getString(R.string.time_title));
						timePicker.show();
						refresh();
					}
				});
				/**
				 * when Clicks on refreshBtn progress bar will automatically set the progress
				 */
					
				/**
				 * Entered Details are stored in listView when user clicks on enterBtn
				 */
				enterBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						user = new User();
						user.setUserName(nameEdt.getText().toString());
						user.setEmail(mailEdt.getText().toString());
						user.setPhoneNumber(phoneEdt.getText().toString());
						user.setTimePicker(timeEdt.getText().toString());
						user.setDatePicker(dateEdt.getText().toString());
						users.add(user);
						listView.setAdapter(adapter);
						alertDialog.dismiss();

					}
				});

				/**
				 * Re-directed to Home Page when User clicks on CanceBtn
				 */
				cancelBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(DialogProgressActivity.this, R.string.none, Toast.LENGTH_LONG).show();
						alertDialog.dismiss();
					}
				});

				alertDialog = builder.create();
				alertDialog.show();

			}

		});
	}
	public void refresh() {
		String name = nameEdt.getText().toString();
		String mail = mailEdt.getText().toString();
		String phone = phoneEdt.getText().toString();
		String date = dateEdt.getText().toString();
		String time = timeEdt.getText().toString();
		Boolean nameBool = TextUtils.isEmpty(name);
		Boolean mailBool = TextUtils.isEmpty(mail);
		Boolean phoneBool = TextUtils.isEmpty(phone);
		Boolean dateBool = TextUtils.isEmpty(date);
		Boolean timeBool = TextUtils.isEmpty(time);
		int nameField=1,mailField=1,phoneField=1,dateField=1,timeField=1;
		if(nameBool)
			nameField=0;
		if(mailBool)
			mailField=0;
		if(phoneBool)
			phoneField=0;
		if(dateBool)
			dateField=0;
		if(timeBool)
			timeField=0;
		int result = nameField+mailField+phoneField+dateField+timeField;
		progressBar.setProgress(result*20);

	}
}