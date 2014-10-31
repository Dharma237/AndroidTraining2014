package com.pcs.dynamicheight;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	private Button b1,b2,b3,b4;
	private SlidingDrawer drawer;
	private LayoutInflater inflater;
	private TextView text;
	private LinearLayout linearLayout;
	private View view;
	private LinearLayout newContent,layout;
	private Animation slide_right_to_left;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		b1 = (Button)findViewById(R.id.b1);
		b2 = (Button)findViewById(R.id.b2);
		b3 = (Button)findViewById(R.id.b3);
		b4 = (Button)findViewById(R.id.b4);
		
		drawer = (SlidingDrawer)findViewById(R.id.sliding_drawer);
		
		text = (TextView)findViewById(R.id.text);
		newContent = (LinearLayout) findViewById(R.id.sliding_content);
		
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		
		inflater=LayoutInflater.from(getApplicationContext());
		slide_right_to_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);
	}
	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b1:
			drawer.open();
			text.setText("HI1");
			view = inflater.inflate(R.layout.base, newContent);
			Button fridge_Btn = (Button) view.findViewById(R.id.fridge_btn);
			break;
		case R.id.b2:
			drawer.open();
			text.setText("Hello2");
			break;
		case R.id.b3:
			drawer.open();
			text.setText("HI3");
			break;
		case R.id.b4:
			drawer.open();
			text.setText("HI4");
			break;
		}
	}

}
