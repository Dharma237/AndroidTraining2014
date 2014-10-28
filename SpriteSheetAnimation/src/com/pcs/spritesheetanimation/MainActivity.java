package com.pcs.spritesheetanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	
	public boolean go;;
	public SpriteView spriteView;
	public Button startAnimation_Btn,stopAnimation_Btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_main);
		
		//referencing xml attributes to the Java file
		startAnimation_Btn = (Button)findViewById(R.id.startAnimation_btn);
		stopAnimation_Btn = (Button)findViewById(R.id.stopAnimation_btn);
		spriteView = (SpriteView)findViewById(R.id.spriteView);
		
		
		//onClick Listeners to the Buttons
		startAnimation_Btn.setOnClickListener(this);
		stopAnimation_Btn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		//startAnimation Button
		case R.id.startAnimation_btn:
			
			//starts the Sprite Animation
			spriteView.startAnimation();
			break;
			
		//stopAnimation Button
		case R.id.stopAnimation_btn:
			//stops the Sprite Animation
			spriteView.stopAnimation();
			break;

		}
	}

}
