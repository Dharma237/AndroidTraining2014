package com.pcs.dialogclassexample;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pcs.customadapterlist.CustomAdapter;
import com.pcs.user.User;

public class DialogClassActivity extends Activity{
	private ListView listView;
	private Button clickBtn;
	private Button enterBtn;
	private Button cancelBtn;
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



		clickBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				inflater = LayoutInflater.from(DialogClassActivity.this);

				View dialogView = inflater.inflate(R.layout.inputdetailsdialog, null);

				builder = new AlertDialog.Builder(DialogClassActivity.this);



				adapter = new CustomAdapter(DialogClassActivity.this, users);


				nameEdt = (EditText)dialogView.findViewById(R.id.name_edt);
				mailEdt = (EditText)dialogView.findViewById(R.id.mail_edt);
				phoneEdt = (EditText)dialogView.findViewById(R.id.phone_edt);
				enterBtn = (Button)dialogView.findViewById(R.id.enter_btn);
				cancelBtn = (Button)dialogView.findViewById(R.id.cancel_btn);

				builder.setView(dialogView);
				enterBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						user = new User();
						user.setUserName(nameEdt.getText().toString());
						user.setEmail(mailEdt.getText().toString());
						user.setPhoneNumber(phoneEdt.getText().toString());
						users.add(user);
						listView.setAdapter(adapter);
						alertDialog.dismiss();

					}
				});
				cancelBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(DialogClassActivity.this, R.string.none, Toast.LENGTH_LONG).show();
						alertDialog.dismiss();
					}
				});

				alertDialog = builder.create();
				alertDialog.show();

			}

		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				inflater = LayoutInflater.from(DialogClassActivity.this);

				View dialogphoneView = inflater.inflate(R.layout.listitemdialog, null);

				builder = new AlertDialog.Builder(DialogClassActivity.this);
				nameListItemTxt = (TextView)dialogphoneView.findViewById(R.id.nameListItem_txt);
				phoneListItemTxt = (TextView)dialogphoneView.findViewById(R.id.phoneListItem_txt);
				phoneItemBtn = (Button)dialogphoneView.findViewById(R.id.phone_listItembtn);
				messageItemBtn = (Button)dialogphoneView.findViewById(R.id.message_listItembtn);
				cancelItemBtn = (Button)dialogphoneView.findViewById(R.id.cancel_listItembtn);
				builder.setView(dialogphoneView);
				nameListItemTxt.setText(getResources().getString(R.string.name_person)+":\t"+nameEdt.getText().toString());
				phoneListItemTxt.setText(getResources().getString(R.string.phone_person)+":\t"+phoneEdt.getText().toString());
				phoneItemBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phoneEdt.getText().toString()));
						
						Toast.makeText(DialogClassActivity.this, phoneEdt.getText().toString(), Toast.LENGTH_LONG).show();
						startActivity(intent);
						alertDialog.dismiss();
					}
				});
				
				messageItemBtn.setOnClickListener(new OnClickListener() {
					String message= getResources().getString(R.string.message);
					String phoneNumber = phoneEdt.getText().toString();
					@Override
					public void onClick(View v) {	
						 
				             sendSMS(phoneNumber, message);
				             Toast.makeText(DialogClassActivity.this, getResources().getString(R.string.no_message), Toast.LENGTH_LONG).show();
				             alertDialog.dismiss();
					}
				});
				
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
		 private void sendSMS(String phoneNumber, String message)
		   {
		       SmsManager sms = SmsManager.getDefault();
		       sms.sendTextMessage(phoneNumber, null, message, null, null);
		    }
	

}