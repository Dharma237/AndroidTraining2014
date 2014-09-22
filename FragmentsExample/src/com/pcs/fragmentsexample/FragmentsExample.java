package com.pcs.fragmentsexample;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class FragmentsExample extends Activity implements OnClickListener{

	private Fragment fragments;
	private Button androidBtn;
	private Button iosBtn;
	private Button webBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		androidBtn = (Button)findViewById(R.id.android_btn);
		iosBtn = (Button)findViewById(R.id.ios_btn);
		webBtn = (Button)findViewById(R.id.web_btn);
		/***
		 * OnClickListeners for everyButton
		 */
		androidBtn.setOnClickListener(this);
		iosBtn.setOnClickListener(this);
		webBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		/***
		 * Reference to the AndroidFragment
		 */
		case R.id.android_btn:
			fragments = new FragmentAndroid();
			break;
		/***
		 * Reference to the iOSFragment
		 */
		case R.id.ios_btn:
			fragments = new FragmentiOS();
			break;
		/**
		 * Reference to the WebFragment
		 */
		case R.id.web_btn:
			fragments = new FragmentWeb();
			break;
		default:
			break;
		}
		/**
		 * Replacing same fragment for all buttons
		 */
		getFragmentManager().beginTransaction()
		.replace(R.id.fragment, fragments).commit();
	}
}