package com.pcs.implicitbuttonactivity;
import com.pcs.helper.Helper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ImplicitButtonActivity extends Activity implements OnClickListener {
	private Button imageBtn;
	private Button textBtn;
	private Button browseBtn;
	private Button videoBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.button);
		imageBtn = (Button)findViewById(R.id.image_btn);
		textBtn = (Button)findViewById(R.id.text_btn);
		browseBtn = (Button)findViewById(R.id.browse_btn);
		videoBtn = (Button)findViewById(R.id.video_btn);
		imageBtn.setOnClickListener(this);
		textBtn.setOnClickListener(this);
		browseBtn.setOnClickListener(this);
		videoBtn.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.text_btn:
			intent = new Intent(Helper.HelperIntent.TEXT);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			startActivity(intent);
			break;
		case R.id.image_btn:
			intent = new Intent(Helper.HelperIntent.IMAGE);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			startActivity(intent);
			break;
		case R.id.browse_btn:
			intent = new Intent(Helper.HelperIntent.BROWSE);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			startActivity(intent);
			break;
		case R.id.video_btn:
			intent = new Intent(Helper.HelperIntent.VIDEO);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			startActivity(intent);
			break;
		default:
			break;
		}
		
	}

}
