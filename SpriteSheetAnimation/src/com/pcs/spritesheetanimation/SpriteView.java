package com.pcs.spritesheetanimation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class SpriteView extends View{
	
	//Constructor for the SpriteView
	public SpriteView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}

	public Context context;
	public int frameWidth;
	public int frameHeight;

	Bitmap spriteSheet = BitmapFactory.decodeResource(getResources(), R.drawable.spritesheet);

	Rect source = new Rect();
	Rect destination = new Rect();

	public int x,y;
	public boolean go;
	public SpriteThread spriteThread;

	//	public SpriteView(Context context) {
	//		super(context);
	//		this.context = context;
	//		init();
	//	}

	private void init() {
		
		//framewidth,frameHeigh,source and destination of the image
		frameWidth = spriteSheet.getWidth()/6;
		frameHeight = spriteSheet.getHeight()/5;
		destination.left = destination.top = 0;
		destination.right = frameWidth;
		destination.bottom = frameHeight;
	}

	public void startAnimation() {
		
		//When user clicks on StartAnimation() Button Animation will run
		go = true;
		
		//Initiatlizing Thread
		spriteThread = new SpriteThread();
		
		//starting spriteThread
		spriteThread.start();

	}

	public void stopAnimation() {
		
		//When user clicks on StopAnimation() Button Animation will stop
		go=false;
		
		try{
			spriteThread.join();
		}
		
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawBitmap(spriteSheet, source, destination, null);
	}

	public class SpriteThread extends Thread{

		public int screenWidth,screenHeight;
		
		//speed of the animation
		int speed=5;

		@Override
		public void run() {
			screenWidth = getWidth();
			screenHeight = getHeight();

			while(go) {
				
				for(int rows=0;rows<5;rows++) {
					
					for(int columns=0;columns<6;columns++) {
						
						//animation will start at the first Frame in the SpriteSheet
						source.left = columns*frameWidth;
						source.top = rows*frameHeight;
						source.right = source.left+frameWidth;
						source.bottom = source.top+frameHeight;

						destination.left = 0;
						destination.top = 0;
						destination.right = destination.left+frameWidth;
						destination.bottom = destination.top+frameHeight;
						
						postInvalidate();
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}// end of inner for()
				}// end of outer for()
			}// end of while()
		}// end of run()
	}
}
