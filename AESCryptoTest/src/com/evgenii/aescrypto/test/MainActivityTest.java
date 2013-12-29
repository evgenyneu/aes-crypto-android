package com.evgenii.aescrypto.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.evgenii.aescrypto.MainActivity;
import com.evgenii.aescrypto.R;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {
	private MainActivity mMainActivity;
	private TextView mMyFirstTextView;

	public MainActivityTest() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mMainActivity = getActivity();
		mMyFirstTextView = (TextView) mMainActivity
				.findViewById(R.id.my_first_text_view);
	}

	public void testPreconditions() {
		assertNotNull("mMainActivity is null", mMainActivity);
		assertNotNull("mMyFirstTextView is null", mMyFirstTextView);
	}

	public void testMyFirstTextView_labelText() {
		final String expected = mMainActivity.getString(R.string.hello_world);
		final String actual = mMyFirstTextView.getText().toString();
		assertEquals(expected, actual);
	}
}
