package com.pcs.jsonexample.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.TextView;

import com.pcs.jsonexample.MainActivity;

public class JsonExampleTest extends ActivityInstrumentationTestCase2<MainActivity>{


	private TextView firstName_Txt, lastName_Txt,gender_Txt,name_Txt;
	private Button download_Btn;
	private MainActivity mContext;

	@SuppressWarnings("deprecation")
	public JsonExampleTest() {
		super("com.pcs.jsonexample.MainActivity",MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		mContext = this.getActivity();
		assertNotNull(mContext);

		name_Txt = (TextView)mContext.findViewById(com.pcs.jsonexample.R.id.name_txt);
		firstName_Txt = (TextView)mContext.findViewById(com.pcs.jsonexample.R.id.firstName_txt);
		lastName_Txt = (TextView)mContext.findViewById(com.pcs.jsonexample.R.id.lastName_txt);
		gender_Txt = (TextView)mContext.findViewById(com.pcs.jsonexample.R.id.gender_txt);
		download_Btn = (Button)mContext.findViewById(com.pcs.jsonexample.R.id.json_button);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();

	}

	public void testPreConditions(){

		assertNotNull("Failed to access activity context",this.getActivity());
		assertNotNull("Can't find a Text view... Has Layout Changed?", name_Txt);
		assertNotNull("Can't find a Text view... Has Layout Changed?", firstName_Txt);
		assertNotNull("Can't find a Text view... Has Layout Changed?", lastName_Txt);
		assertNotNull("Can't find a Text view... Has Layout Changed?", gender_Txt);
		assertNotNull("Can't find a Button view... Has Layout Changed?", download_Btn);

	}
	public void testButton()
	{
		TouchUtils.clickView(JsonExampleTest.this, download_Btn);
	}
}
