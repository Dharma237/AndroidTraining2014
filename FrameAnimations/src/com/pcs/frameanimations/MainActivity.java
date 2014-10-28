package com.pcs.frameanimations;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity{
	
	private ImageView frameImage;
	private AnimationDrawable frameAnimation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		frameImage = (ImageView)findViewById(R.id.imageAnimation);
		
		frameImage.setBackgroundResource(R.drawable.frame_animation_list);
		
		frameAnimation = (AnimationDrawable) frameImage.getBackground();
		
		
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		
		if(hasFocus)
			frameAnimation.start();
		else
			frameAnimation.stop();
	}

}
