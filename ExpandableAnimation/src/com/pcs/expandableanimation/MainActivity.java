package com.pcs.expandableanimation;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener{

	private static final int DURATION = 1500;
	private ImageView redImg,blueImg,greenImg;
	private Animation zoom,slideUp,slideDown,pushUp,pushDown;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		redImg = (ImageView)findViewById(R.id.red_img);
		greenImg = (ImageView)findViewById(R.id.green_img);
		blueImg = (ImageView)findViewById(R.id.blue_img);

		//Animations xmls for zoom,slideUp,slideDown,pushUp,pushDown
		zoom = AnimationUtils.loadAnimation(this, R.anim.zoom_animation);
		slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up_animation);
		slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down_animation);

		//onClickListeners to the images
		redImg.setOnClickListener(this);
		greenImg.setOnClickListener(this);
		blueImg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		//animation effects to the blue_img
		case R.id.blue_img:
			blueImg.startAnimation(zoom);
			redImg.startAnimation(slideDown);
			greenImg.startAnimation(slideDown);
			break;

		//animation effects to the green_img
		case R.id.green_img:
			greenImg.startAnimation(zoom);
			blueImg.startAnimation(slideUp);
			redImg.startAnimation(slideDown);
			break;
		//animation effects to the red_img
		case R.id.red_img:
			redImg.startAnimation(zoom);
			blueImg.startAnimation(slideUp);
			greenImg.startAnimation(slideUp);
			break;
		default:
			break;
		}

	}
}
