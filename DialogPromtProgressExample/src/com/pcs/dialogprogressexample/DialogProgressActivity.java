package com.pcs.dialogprogressexample;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
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
	private TextView nameListItemTxt;
	private TextView phoneListItemTxt;
	private Button phoneItemBtn;
	private Button messageItemBtn;
	private Button cancelItemBtn;

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


				nameEdt = (EditText)dialogView.findViewById(R.id.name_edt);
				mailEdt = (EditText)dialogView.findViewById(R.id.mail_edt);
				phoneEdt = (EditText)dialogView.findViewById(R.id.phone_edt);
				enterBtn = (Button)dialogView.findViewById(R.id.enter_btn);
				cancelBtn = (Button)dialogView.findViewById(R.id.cancel_btn);
				timeEdt = (EditText)dialogView.findViewById(R.id.time_edt);
				dateEdt = (EditText)dialogView.findViewById(R.id.date_edt);

				builder.setView(dialogView);

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
					}
				});
				
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

			        }
			    });
				ProgressBar progress = new ProgressBar(DialogProgressActivity.this);
				progress.setVisibility(View.GONE);
				progress.setMax(100);
					String name = nameEdt.getText().toString();
					if(name!=null)
					{
						progress.setProgress(20);
					}
					String mail = mailEdt.getText().toString();
					if(mail!=null)
					{
						progress.setProgress(40);
					}
					String phone = phoneEdt.getText().toString();
					if(phone!=null)
					{
						progress.setProgress(40);
					}
					String date = timeEdt.getText().toString();
					if(date!=null)
					{
						progress.setProgress(40);
					}
					String time = dateEdt.getText().toString();
					if(time!=null)
					{
						progress.setProgress(40);
					}
					
					
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
		
		
		
		
		/***
		 * List View contains name,email and phone Number of user
		 * Dialog will appear with 3 Buttons Phone,Message,Cancel when user clicks on ListView
		 */

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				inflater = LayoutInflater.from(DialogProgressActivity.this);

				View dialogphoneView = inflater.inflate(R.layout.listitemdialog, null);

				builder = new AlertDialog.Builder(DialogProgressActivity.this);

				nameListItemTxt = (TextView)dialogphoneView.findViewById(R.id.nameListItem_txt);
				phoneListItemTxt = (TextView)dialogphoneView.findViewById(R.id.phoneListItem_txt);
				phoneItemBtn = (Button)dialogphoneView.findViewById(R.id.phone_listItembtn);
				messageItemBtn = (Button)dialogphoneView.findViewById(R.id.message_listItembtn);
				cancelItemBtn = (Button)dialogphoneView.findViewById(R.id.cancel_listItembtn);

				builder.setView(dialogphoneView);

				nameListItemTxt.setText(getResources().getString(R.string.name_person)+":\t"+nameEdt.getText().toString());
				phoneListItemTxt.setText(getResources().getString(R.string.phone_person)+":\t"+phoneEdt.getText().toString());

				/**
				 * User can call to the entered number
				 */
				phoneItemBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phoneEdt.getText().toString()));

						Toast.makeText(DialogProgressActivity.this, phoneEdt.getText().toString(), Toast.LENGTH_LONG).show();
						startActivity(intent);
						alertDialog.dismiss();
					}
				});

				/***
				 * Message will sent to the entered number if message option is avilable in Emulator
				 * Otherwise it will alert Toast Message
				 */
				messageItemBtn.setOnClickListener(new OnClickListener() {
					String message= getResources().getString(R.string.message);
					String phoneNumber = phoneEdt.getText().toString();
					@Override
					public void onClick(View v) {	

						sendSMS(phoneNumber, message);
						Toast.makeText(DialogProgressActivity.this, getResources().getString(R.string.no_message), Toast.LENGTH_LONG).show();
						alertDialog.dismiss();
					}
				});

				/**
				 * User endUp with ListView
				 */
				cancelItemBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {	
						alertDialog.dismiss();
					}
				});
				alertDialog = builder.create();
				alertDialog.show();

			}
		});
	}
	/***
	 * phoneNumber should not be null
	 * message can be null but better to send text
	 * @param phoneNumber
	 * @param message
	 */
	private void sendSMS(String phoneNumber, String message)
	{
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, null, null);
	}


}