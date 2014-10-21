package com.pcs.slidingdrawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity implements OnClickListener{

	
	private ImageView songRepeat_Img,songStop_Img,
						songPrevious_Img,songNext_Img,songShuffle_Img;
	
	private SlidingDrawer slidingDrawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//referencing layout file to the Javafile
		setContentView(R.layout.activity_main);
		
		//Referencing xml elements to java File elements
		slidingDrawer = (SlidingDrawer)findViewById(R.id.sliding_drawer);
		songRepeat_Img = (ImageView)findViewById(R.id.songRepeat_img);
		songPrevious_Img = (ImageView)findViewById(R.id.songPrevious_img);
		songStop_Img = (ImageView)findViewById(R.id.songStop_img);
		songNext_Img = (ImageView)findViewById(R.id.songNext_img);
		songShuffle_Img = (ImageView)findViewById(R.id.songShuffle_img);
		
		
		//OnClick Listeners to the Song Images
		songRepeat_Img.setOnClickListener(this);
		songPrevious_Img.setOnClickListener(this);
		songStop_Img.setOnClickListener(this);
		songNext_Img.setOnClickListener(this);
		songShuffle_Img.setOnClickListener(this);


	}
	
	/***
	 * @param are id of each image in xml
	 * Printing toast when clicks on image
	 */
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.songRepeat_img:

			Toast.makeText(getApplicationContext(), R.string.songRepeat, Toast.LENGTH_LONG).show();

			break;
		case R.id.songPrevious_img:

			Toast.makeText(getApplicationContext(), R.string.songPrevious, Toast.LENGTH_LONG).show();

			break;
		case R.id.songStop_img:

			Toast.makeText(getApplicationContext(), R.string.songStop, Toast.LENGTH_LONG).show();

			break;
		case R.id.songNext_img:

			Toast.makeText(getApplicationContext(), R.string.songNext, Toast.LENGTH_LONG).show();

			break;
		case R.id.songShuffle_img:

			Toast.makeText(getApplicationContext(), R.string.songShuffle, Toast.LENGTH_LONG).show();

			break;

		default:
			break;
		}

	}
	
	/***
	 * Closes Sliding Drawer when user clicks BackButton
	 * 
	 */
	public void onBackPressed()
	{
		/***
		 * Checks for is slidingDrawer is opened or not
		 * If opens it closes the sliding Drawer
		 */
		if(slidingDrawer.isOpened())
		{
			slidingDrawer.close();
		}

		//if sliding bar is not open then it will not close
		else
			super.onBackPressed();
		
	}

}
