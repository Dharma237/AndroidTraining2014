package com.pcs.jsonexample.test;

import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.TextView;

import com.pcs.jsonexample.MainActivity;

public class JsonExampleTest extends ActivityInstrumentationTestCase2<MainActivity>{


	private TextView firstName_Txt, lastName_Txt,gender_Txt,name_Txt;
	private Button download_Btn;
	public static final String USER_DATA ="{\"id\":\"591658927624314\",\"first_name\":\"Anitha\"," +
			"\"username\":\"anitha.honey784\",\"name\":\"Anitha Honey\",\"locale\":\"en_US\"," +
			"\"link\":\"https:\\/\\/www.facebook.com\\/anitha.honey784\"," +
			"\"last_name\":\"Honey\",\"gender\":\"female\"}";

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

	/***
	 * PreConditions for all the TextFields,Context,Buttons are present or not
	 * if Not then displays Error
	 */
	public void testPreConditions(){

		assertNotNull("Failed to access activity context",this.getActivity());
		assertNotNull("Can't find a Text view... Has Layout Changed?", name_Txt);
		assertNotNull("Can't find a Text view... Has Layout Changed?", firstName_Txt);
		assertNotNull("Can't find a Text view... Has Layout Changed?", lastName_Txt);
		assertNotNull("Can't find a Text view... Has Layout Changed?", gender_Txt);
		assertNotNull("Can't find a Button view... Has Layout Changed?", download_Btn);

	}
	/***
	 * checks for retrieved Json Data is null or not through DownloadButton
	 * if Null then it will shows error
	 */
	public void testButton()
	{
		TouchUtils.clickView(JsonExampleTest.this, download_Btn);
		//if jsonObj is null then it will prints gettingResponse is null
		JSONObject data = mContext.jsonObj;
		assertNotNull("getting Response is null", data);
	}

	/***
	 * Checks for Field(eg:FirstName of User) in the retrieved Data
	 * if Field is not there then it will shows Error
	 */

	public void testNoFieldName()
	{
		TouchUtils.clickView(JsonExampleTest.this, download_Btn);
		boolean expected = mContext.jsonObj.has("firstName");
		boolean actual = true;
		assertEquals(expected, actual);

	}

	/***
	 * Checks for Retrieved data and Actual Data
	 * if result of comparison is false then shows error
	 */
	public void testValidateData()
	{
		TouchUtils.clickView(JsonExampleTest.this, download_Btn);
		String actual = mContext.jsonObj.toString();
		String expected = USER_DATA;
		assertEquals(expected, actual);
	}

	/***
	 * Checks for gender field in the Retrieved Data to actual null 
	 * If result of comparison fails then shows TextFieldCheckError
	 */
	public void testTextFieldCheck()
	{
		TouchUtils.clickView(JsonExampleTest.this, download_Btn);
		String actual = gender_Txt.getText().toString();
		String expected = "";
		assertEquals(expected, actual);
	}
}