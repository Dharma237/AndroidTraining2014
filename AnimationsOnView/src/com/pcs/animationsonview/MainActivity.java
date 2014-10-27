package com.pcs.animationsonview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener{

	private Button fadeInOut_Btn,zoomInOut_Btn,rotate_Btn,
	shake_Btn,topToBottom_Btn,pushUp_Btn;
	private ImageView animate_Img;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.activity_main);

		fadeInOut_Btn = (Button)findViewById(R.id.fadeInOut_btn);
		zoomInOut_Btn = (Button)findViewById(R.id.zoomInOut_btn);
		rotate_Btn = (Button)findViewById(R.id.rotate_btn);
		shake_Btn = (Button)findViewById(R.id.shake_btn);
		topToBottom_Btn = (Button)findViewById(R.id.topToBottm_btn);
		pushUp_Btn = (Button)findViewById(R.id.pushUp_btn);
		animate_Img = (ImageView)findViewById(R.id.animate_img);

		//onClick Listeners to the Buttons
		fadeInOut_Btn.setOnClickListener(this);
		zoomInOut_Btn.setOnClickListener(this);
		rotate_Btn.setOnClickListener(this);
		shake_Btn.setOnClickListener(this);
		topToBottom_Btn.setOnClickListener(this);
		pushUp_Btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		/***
		 * FadeInOut Animation
		 * 
		 */
		case R.id.fadeInOut_btn:

			Animation fade_animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_animation);
			animate_Img.startAnimation(fade_animation);
			break;

			/***
			 * ZoomInOut Animation
			 * 
			 */	
		case R.id.zoomInOut_btn:

			Animation zoom_animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_animation);
			animate_Img.startAnimation(zoom_animation);
			break;
			
			//Rotate Animation
		case R.id.rotate_btn:

			Animation rotate_animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_animation);
			animate_Img.startAnimation(rotate_animation);
			break;
			
			//Shake Animation
		case R.id.shake_btn:

			Animation shake_animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation);
			animate_Img.startAnimation(shake_animation);
			break;
			
			//Top to Bottom Animation
		case R.id.topToBottm_btn:

			Animation topToBottom_animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.top_to_bottom_animation);
			animate_Img.startAnimation(topToBottom_animation);
			break;
			
			//Push Up Animation
		case R.id.pushUp_btn:

			Animation pushUp_animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pushup_animation);
			animate_Img.startAnimation(pushUp_animation);
			break;


		}

	}
}