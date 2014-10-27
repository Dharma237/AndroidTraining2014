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

	private Button fadeInOut_Btn,zoomInOut_Btn,rotate_Btn;
	private ImageView animate_Img;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.activity_main);

		fadeInOut_Btn = (Button)findViewById(R.id.fadeInOut_btn);
		zoomInOut_Btn = (Button)findViewById(R.id.zoomInOut_btn);
		rotate_Btn = (Button)findViewById(R.id.rotate_btn);
		animate_Img = (ImageView)findViewById(R.id.animate_img);

		//onClick Listeners to the Buttons
		fadeInOut_Btn.setOnClickListener(this);
		zoomInOut_Btn.setOnClickListener(this);
		rotate_Btn.setOnClickListener(this);
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

		case R.id.rotate_btn:

			Animation rotate_animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_animation);
			animate_Img.startAnimation(rotate_animation);
			break;
		}

	}
}