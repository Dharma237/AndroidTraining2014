package com.pcs.dialogclassexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DialogClassActivity extends Activity{
	private TextView printTxt;
	private Button clickBtn;
	private Button enterBtn;
	private Button cancelBtn;
	private EditText nameEdt;
	private EditText mailEdt;
	private AlertDialog.Builder builder;
	private LayoutInflater inflater;
	private AlertDialog alertDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		clickBtn = (Button)findViewById(R.id.click_btn);
		printTxt = (TextView)findViewById(R.id.print_txt);
		clickBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				inflater = LayoutInflater.from(DialogClassActivity.this);
				
				View dialogView = inflater.inflate(R.layout.dialogxml, null);
				
				 builder = new AlertDialog.Builder(DialogClassActivity.this);
				
				builder.setView(dialogView);
				
				
				
				nameEdt = (EditText)dialogView.findViewById(R.id.name_edt);
				mailEdt = (EditText)dialogView.findViewById(R.id.mail_edt);
				enterBtn = (Button)dialogView.findViewById(R.id.enter_btn);
				cancelBtn = (Button)dialogView.findViewById(R.id.cancel_btn);
				enterBtn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
					  printTxt.setText("UserName:"+nameEdt.getText().toString()+"\n eMail:"+mailEdt.getText().toString());
					  Toast.makeText(DialogClassActivity.this, "UserName:"+nameEdt.getText().toString()+"\n eMail:"+mailEdt.getText().toString(), Toast.LENGTH_LONG).show();
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
		
	}

}
