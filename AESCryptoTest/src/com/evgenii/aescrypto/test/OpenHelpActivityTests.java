package com.evgenii.aescrypto.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.evgenii.aescrypto.HelpActivity;
import com.evgenii.aescrypto.R;

public class OpenHelpActivityTests extends ActivityInstrumentationTestCase2<HelpActivity> {
	private HelpActivity mActivity;

	public OpenHelpActivityTests() {
		super(HelpActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mActivity = getActivity();
	}

	public void testHelpTextIsVisible() {
		final TextView helpTextView = (TextView) mActivity.findViewById(R.id.helpTextView);

		assertEquals("AES Crypto encrypts", helpTextView.getText().toString().substring(0, 19));
	}

}
